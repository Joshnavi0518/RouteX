import java.util.*;

public class Graph {

    ArrayList<ArrayList<int[]>> adj;
    Map<String,Integer> cityToId;
    Map<Integer,String> idToCity;
    int n;

    public Graph(){
        adj=new ArrayList<>();
        cityToId=new HashMap<>();
        idToCity=new HashMap<>();
        n=0;
    }

    void addCity(String city){
        if(!cityToId.containsKey(city)){
            cityToId.put(city,n);
            idToCity.put(n,city);
            adj.add(new ArrayList<>());
            n++;
        }
    }

    void addFlight(String from,String to,int cost){
        addCity(from);
        addCity(to);
        int u=cityToId.get(from);
        int v=cityToId.get(to);
        adj.get(u).add(new int[]{v,cost});
        adj.get(v).add(new int[]{u,cost});
    }

    String findCheapest(String src,String dst){
        if(!cityToId.containsKey(src)||!cityToId.containsKey(dst))
            return "NOTFOUND";

        int s=cityToId.get(src);
        int d=cityToId.get(dst);

        int[] dist=new int[n];
        int[] prev=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(prev,-1);
        dist[s]=0;

        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        pq.add(new int[]{0,s});

        while(!pq.isEmpty()){
            int[] curr=pq.poll();
            int cost=curr[0];
            int u=curr[1];

            if(cost>dist[u]) continue;

            for(int[] nei:adj.get(u)){
                int v=nei[0];
                int w=nei[1];
                if(dist[u]+w<dist[v]){
                    dist[v]=dist[u]+w;
                    prev[v]=u;
                    pq.add(new int[]{dist[v],v});
                }
            }
        }

        if(dist[d]==Integer.MAX_VALUE) return "NOROUTE";

        List<String> path=new ArrayList<>();
        for(int at=d;at!=-1;at=prev[at])
            path.add(idToCity.get(at));
        Collections.reverse(path);

        // format: city1,city2,city3|stops|cost
        return String.join(",",path)+"|"+(path.size()-2)+"|"+dist[d];
    }

    String getCities(){
        return String.join(",",cityToId.keySet());
    }
}
