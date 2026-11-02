import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    static Graph g=new Graph();

    public static void main(String[] args) throws Exception {

        g.addFlight("Delhi","Mumbai",3500);
        g.addFlight("Delhi","Hyderabad",4200);
        g.addFlight("Delhi","Kolkata",3800);
        g.addFlight("Delhi","Jaipur",1200);
        g.addFlight("Delhi","Amritsar",1800);
        g.addFlight("Mumbai","Bengaluru",2800);
        g.addFlight("Mumbai","Hyderabad",1900);
        g.addFlight("Mumbai","Goa",1800);
        g.addFlight("Mumbai","Pune",900);
        g.addFlight("Mumbai","Ahmedabad",1500);
        g.addFlight("Hyderabad","Bengaluru",3100);
        g.addFlight("Hyderabad","Chennai",2600);
        g.addFlight("Bengaluru","Chennai",2200);
        g.addFlight("Bengaluru","Goa",2100);
        g.addFlight("Bengaluru","Kochi",2400);
        g.addFlight("Kolkata","Chennai",4100);
        g.addFlight("Kolkata","Bhubaneswar",1600);
        g.addFlight("Chennai","Kochi",2000);
        g.addFlight("Jaipur","Ahmedabad",1300);
        g.addFlight("Pune","Goa",1400);

        int port = Integer.parseInt(
            System.getenv().getOrDefault("PORT", "8080")
        );

        HttpServer server = HttpServer.create(
            new InetSocketAddress(port), 0
        );
        server.createContext("/",e->serveFile(e,"index.html","text/html"));
        server.createContext("/cities",Main::handleCities);
        server.createContext("/search",Main::handleSearch);
        server.start();

        System.out.println("====================================");
        System.out.println("  RouteX running!");
        System.out.println("  RouteX server started on port " + port);
        System.out.println("==================================== ");
    }

    static void handleCities(HttpExchange e) throws IOException {
        sendRes(e,g.getCities());
    }

    static void handleSearch(HttpExchange e) throws IOException {
        String query=e.getRequestURI().getQuery();
        String from="",to="";
        for(String part:query.split("&")){
            String[] kv=part.split("=");
            if(kv[0].equals("from")) from=URLDecoder.decode(kv[1],"UTF-8");
            if(kv[0].equals("to"))   to=URLDecoder.decode(kv[1],"UTF-8");
        }
        sendRes(e,g.findCheapest(from,to));
    }

    static void serveFile(HttpExchange e,String path,String mime) throws IOException {
        byte[] bytes=Files.readAllBytes(Paths.get(path));
        e.getResponseHeaders().set("Content-Type",mime);
        e.sendResponseHeaders(200,bytes.length);
        e.getResponseBody().write(bytes);
        e.getResponseBody().close();
    }

    static void sendRes(HttpExchange e,String body) throws IOException {
        e.getResponseHeaders().set("Access-Control-Allow-Origin","*");
        byte[] bytes=body.getBytes("UTF-8");
        e.sendResponseHeaders(200,bytes.length);
        e.getResponseBody().write(bytes);
        e.getResponseBody().close();
    }
}
