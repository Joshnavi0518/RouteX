# RouteX ✈️
### Cheapest Flight Route Finder using Dijkstra's Algorithm

A full-stack Java project that finds the cheapest flight route between Indian cities using Dijkstra's Algorithm. Built with pure Java - no frameworks.

---

## 🚀 Live Demo
> Run locally and open http://localhost:8080

---

## 💡 About The Project

RouteX models Indian cities as graph nodes and flight routes as weighted edges. Given a source and destination, it finds the cheapest path using Dijkstra's Algorithm with a Priority Queue.

The result is displayed on an interactive India map with the route animated in real time.

---

## 🧠 How It Works

User selects From -> To city
        ↓
Frontend sends request to Java server
(http://localhost:8080/search?from=Delhi&to=Chennai)
        ↓
Graph.java runs Dijkstra's Algorithm
        ↓
Returns cheapest path + cost + stopovers
        ↓
Route lights up on India map

---

## 🗂️ Project Structure

RouteX/
├── Graph.java       -> Graph + Dijkstra's Algorithm (core DSA logic)
├── Main.java        -> Built-in Java HTTP Server + API endpoints
└── index.html       -> Frontend UI with India SVG map

---

## ⚙️ Data Structures Used

ArrayList<ArrayList<int[]>>  ->  Adjacency list to store graph
PriorityQueue<int[]>         ->  Min-heap for Dijkstra's
HashMap<String, Integer>     ->  City name -> node ID mapping
int[] dist                   ->  Shortest distance array
int[] prev                   ->  Path reconstruction array

---

## 📊 Algorithm

Dijkstra's Algorithm with Priority Queue

Time Complexity  : O((V + E) log V)
Space Complexity : O(V + E)
V = number of cities, E = number of flight routes

---

## 🗺️ Cities Covered

Delhi      -> Mumbai, Hyderabad, Kolkata, Jaipur, Amritsar
Mumbai     -> Bengaluru, Hyderabad, Goa, Pune, Ahmedabad
Bengaluru  -> Chennai, Goa, Hyderabad, Kochi
Hyderabad  -> Chennai, Bengaluru, Mumbai
Chennai    -> Kochi, Kolkata, Bengaluru
Kolkata    -> Chennai, Bhubaneswar

---

## ▶️ How To Run

Step 1 - Clone the repo
  git clone https://github.com/yourusername/RouteX.git
  cd RouteX

Step 2 - Compile
  mkdir out
  javac -d out Graph.java Main.java

Step 3 - Run
  java -cp out Main

Step 4 - Open browser
  http://localhost:8080

---

## 🖥️ Features

✅ Find cheapest route between 15+ Indian cities
✅ Animated route on India map
✅ Shows total cost and number of stopovers
✅ Source city highlighted in blue, stopovers in orange
✅ Zero external dependencies - pure Java

---

## 🛠️ Tech Stack

Language  : Java
Algorithm : Dijkstra's Algorithm
Server    : Java Built-in HttpServer
Frontend  : HTML, CSS, JavaScript

---

## 📄 API Endpoints

GET /                     -> Serves the UI
GET /cities               -> Returns all city names
GET /search?from=X&to=Y   -> Finds cheapest route

