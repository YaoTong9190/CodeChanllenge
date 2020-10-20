package com.henry.java;

import com.sun.javafx.geom.Edge;

import java.util.*;

public class Graph {
    public static class Vertex{
        int data;
        EdgeNode edgeNodes;
        public Vertex(int data){
            this.data = data;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "data=" + data +
                    '}';
        }
    }

    public static class EdgeNode {
        int index;
        EdgeNode next;
        int weight;
        public EdgeNode(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
    }

    List<Vertex> list = new ArrayList<>();
    boolean isDirected;

    public Graph(boolean isDirected){
        this.isDirected = isDirected;
    }

    public void addVertex(int data){
        list.add(new Vertex(data));
    }

    public boolean addEdge(int fromIndex, int toIndex, int weight) {
        if (list.size()<fromIndex || list.size()<toIndex || fromIndex < 0 || toIndex <0){
            System.out.println("Your input is incorrect");
            return false;
        }
        Vertex v = list.get(fromIndex);
        EdgeNode node = new EdgeNode(toIndex, weight);
        if (v.edgeNodes == null){
            v.edgeNodes = node;
        }else {
            EdgeNode p = v.edgeNodes;
            while (v.edgeNodes.next!=null){
                //if the edge has already exist, return true but do nothing.
                if (p.index == node.index){
                    return true;
                }
                p = p.next;
            }
            p.next = node;
        }

        //Undirected graph should have related edge too.
        if (!isDirected){
            Vertex v2 = list.get(toIndex);
            EdgeNode node2 = new EdgeNode(fromIndex, weight);
            if (v2.edgeNodes == null){
                v2.edgeNodes = node2;
            }else {
                EdgeNode p = v2.edgeNodes;
                while (p.next != null){
                    if(p.index == node2.index){
                        return true;
                    }
                    p = p.next;
                }
                p.next = node2;
            }
        }
        return true;
    }

    public List<Vertex> DFS(){
        if (list.isEmpty()){
            return new ArrayList<Vertex>();
        }
        Map<Integer, Boolean> visitedIndex = new HashMap<>();//key is index, value is boolean
        return DFS(0, visitedIndex);
    }

    private List<Vertex> DFS(int index, Map<Integer, Boolean> visitedIndex) {
        List<Vertex> res = new ArrayList<>();
        if (visitedIndex.get(index)!=null && visitedIndex.get(index) == true){
            return res;
        }
        Vertex v = list.get(index);
        visitedIndex.put(index, true);
        res.add(v);
        EdgeNode p = v.edgeNodes;
        while (p!=null){
            res.addAll(DFS(p.index,visitedIndex));
            p = p.next;
        }
        return res;
    }

    public List<Vertex> BFS(){
        if (list.isEmpty()){
            return new ArrayList<Vertex>();
        }
        Map<Integer,Boolean> visitedIndex = new HashMap<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();//save index
        List<Vertex> res = new ArrayList<>();
        queue.offer(0);
        while (!queue.isEmpty()){
            int index = queue.poll();
            if (visitedIndex.get(index)==null || visitedIndex.get(index)==false){
                visitedIndex.put(index,true);
                Vertex v = list.get(index);
                res.add(v);
                EdgeNode p = v.edgeNodes;
                while (p != null){
                    queue.offer(p.index);
                    p = p.next;
                }
            }
        }
        return  res;
    }

    public static class Path {
        int length=Integer.MAX_VALUE;
        List<Integer> vertexes = new ArrayList<>();

        @Override
        public String toString() {
            return getString(vertexes, length);
        }

        static String getString(List<Integer> vertexes, int length) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i< vertexes.size(); i++){
                if (i== vertexes.size()-1){
                    sb.append(vertexes.get(i));
                }else {
                    sb.append(vertexes.get(i) + "->");
                }
            }
            return "Path{" +
                    "length=" + length + "," +
                    "vertexes=" + sb.toString() +
                    '}';
        }
    }

    public Path Dijkstra(int fromIndex, int toIndex) {

        if (list.size()<fromIndex || list.size()<toIndex || fromIndex < 0 || toIndex <0){
            System.out.println("Your input is invalid");
            return null;
        }

        //Initialize
        int[] book = new int[list.size()]; book[fromIndex] = 1;
        Path[] dist = new Path[list.size()];
        for (int i = 0; i<list.size();i++){
            dist[i] = new Path();
        }
        dist[fromIndex].length = 0;
        dist[fromIndex].vertexes.add(fromIndex);

        //Start from the source, until all elements in book array equals 1; After this loop, all shorted path from source to all the other nodes will be calculated.
        int index = fromIndex;
        while (!isAllBooked(book)) {
            Vertex v = list.get(index);
            EdgeNode p = v.edgeNodes;
            //1. traverse the node neighbours and calculate the distance from source to the node
            while (p != null) {
                //Calculate a new path
                Path current = dist[index];
                Path path = new Path();

                path.length = current.length + p.weight;
                List<Integer> list = new ArrayList<>();

                list.addAll(current.vertexes);
                list.add(p.index);
                path.vertexes = list;
                //if the new path is smaller than current record, replace with it.
                if (path.length<dist[p.index].length){
                    dist[p.index] = path;
                }

                p = p.next;
            }
            //2. Traverse the unbooked nodes to find the min dist node's index
            int minIndex = index;
            int min = Integer.MAX_VALUE;
            for (int i=0; i<book.length;i++){
                if (book[i] ==0){
                    if (dist[i].length<min){
                        min = dist.length;
                        minIndex = i;
                    }
                }
            }
            //set this index as booked and use this index in next loop
            book[minIndex] = 1;
            index = minIndex;

        }
        //find the shortest path according to "toIndex"
        return dist[toIndex];
    }

    private boolean isAllBooked(int[] book){
        for (int i : book){
            if (i==0){
                return false;
            }
        }
        return true;
    }

    public boolean hasCircle(){
        List<Integer> visitedIndexList = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        while (!queue.isEmpty()){
            int index = queue.poll();
            if (list.contains(index)){
                System.out.println("重复的顶点是："+list.get(index));
                return true;
            }
            visitedIndexList.add(index);

            Vertex v = list.get(index);
            EdgeNode p = v.edgeNodes;
            while (p != null){
                queue.offer(p.index);
                p = p.next;
            }
        }
        return false;
    }

    public List<Vertex> topologicalSort(){
        //Create a list to save all indgree information
        List<Integer> inDegreeList = new ArrayList<>();
        for (int i =0 ; i< list.size();i++){
            inDegreeList.add(getInDegree(i));
        }
        //find a vertex with 0 inDegree
        int index = -1;
        for (int i=0; i<inDegreeList.size();i++){
            if (inDegreeList.get(i)==0){
                index = i;
                break;
            }
        }
        //If doesn't find a vertex, that means there's a circle in graph
        if (index == -1){
            return null;
        }

        List<Vertex> res = new ArrayList<>();
        ArrayDeque<Vertex> queue = new ArrayDeque<>(); //save indegree equals 0 nodes
        queue.offer(list.get(index));
        while (!queue.isEmpty()){
            Vertex v = queue.poll();
            res.add(v);
            //remove the edges related with v
            EdgeNode p = v.edgeNodes;
            while (p!=null){
                inDegreeList.set(p.index, inDegreeList.get(p.index)-1);
                if (inDegreeList.get(p.index)<0){
                    return null;
                }else if (inDegreeList.get(p.index)==0){
                    queue.offer(list.get(p.index));
                }
                p = p.next;
            }
        }

        return res;
    }

    public int getInDegree(int index){
        int count =0 ;
        for (int j=0; j<list.size();j++){
            if (j!=index){
                EdgeNode p = list.get(j).edgeNodes;
                while (p!=null){
                    if (p.index == index){
                        count++;
                        break;
                    }
                    p = p.next;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //Directed graph
        Graph graph = new Graph(true);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        graph.addEdge(0,1, 13);
        graph.addEdge(0,2, 1);
        graph.addEdge(1,3, 3);
        graph.addEdge(1,4, 4);
        graph.addEdge(2,1, 5);
        graph.addEdge(2,4, 6);
        graph.addEdge(3,5, 12);
        graph.addEdge(4,5, 16);

        System.out.println(graph.DFS());
        System.out.println(graph.BFS());

        Path path = graph.Dijkstra(0,5);
        //System.out.println(path);

        System.out.println(graph.hasCircle());

        System.out.println(graph.topologicalSort());
    }
}
