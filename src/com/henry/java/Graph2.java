package com.henry.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Graph2 {
    public static class Vertex {
        int data;
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

    List<Vertex> list;
    int[][] edgeGrid;
    boolean isDirected;
    public Graph2(List<Vertex> list, boolean isDirected){
        this.list = list;
        edgeGrid = new int[this.list.size()][this.list.size()];
        this.isDirected = isDirected;
    }

    public boolean addEdge(int tail, int head, int weight) {
        if (tail<0||tail>edgeGrid.length||head<0||head>edgeGrid.length||tail==head){
            System.out.println("Invalid Input");
           return false;
        }
        if (edgeGrid[tail][head]!=0){
            return false;
        }
        edgeGrid[tail][head] = weight;
        if (!isDirected){
            edgeGrid[head][tail] = weight;
        }
        return true;
    }

    public List<Vertex> DFS(Integer index, List<Integer> visitedIndex){
        List<Vertex> res = new ArrayList<>();
        if (visitedIndex.contains(index)){
            return res;
        }
        res.add(list.get(index));
        visitedIndex.add(index);
        for (int j=0; j<edgeGrid.length; j++){
            if(edgeGrid[index][j]!=0){
                res.addAll(DFS(j,visitedIndex));
            }
        }
        return res;
    }

    public List<Vertex> BFS(){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        List<Integer> visitedIndex = new ArrayList<>();
        List<Vertex> res = new ArrayList<>();
        while (!queue.isEmpty()){
            int index = queue.poll();
            if(!visitedIndex.contains(index)){
                res.add(list.get(index));
                visitedIndex.add(index);

                for (int j=0; j<edgeGrid.length;j++){
                    if (edgeGrid[index][j]!=0){
                        queue.offer(j);
                    }
                }
            }
        }

        return res;
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


    /**
     * 单源最短路径算法，用于计算一个节点到其他!!所有节点!!的最短路径
     */
    public Path Dijkstra(int from, int to){
        if (from<0||from>list.size()||to<0||to>list.size()||from==to){
            System.out.println("Invalid Input");
            return null;
        }
        //1. create a distance table and add the source into it
        Path[] dist = new Path[list.size()];
        for (int i=0; i<dist.length;i++){
            dist[i] = new Path();
        }
        dist[from] = new Path();
        dist[from].length=0;
        dist[from].vertexes.add(from);

        //2. create a table to record if the shortest path has been confirm from source to each other vertexes
        int[] book = new int[list.size()];

        while (true) {
            //3. Find a unvisited vertex whose distance value is the smallest.
            int index = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < dist.length; i++) {
                if (book[i] == 0 && dist[i].length < min) {
                    min = dist[i].length;
                    index = i;
                }
            }
            //If cannot find a unvisited vertex, that means:
            //a. All vertexes has been visited
            //b. Some left vertexes cannot be reached from source.
            if (index==-1){
                break;
            }

            //4. mark the vertex as booked
            book[index] = 1;
            //5. adjust the path length from source to its children
            for (int j=0; j<edgeGrid.length;j++){
                if (edgeGrid[index][j]!=0) {
                    int length = dist[index].length + edgeGrid[index][j];
                    if (length < dist[j].length) {
                        Path path = new Path();
                        path.length = length;
                        path.vertexes.addAll(dist[index].vertexes);
                        path.vertexes.add(j);
                        dist[j] = path;
                    }
                }
            }
        }

        //get the result
        return dist[to];
    }

    public boolean hasCircle(){
        int[] checked = new int[list.size()];//save already checked status
        for (int i=0; i<list.size();i++){
            int[] visited = new int[list.size()];
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            while (!queue.isEmpty()){
                int index = queue.poll();
                if (visited[index]!=0){
                    if (index==i) {
                        System.out.println("repeated vertex:" + index);
                        return true;
                    }else {
                        continue;
                    }
                }
                visited[index] = 1;
                for (int j=0;j<edgeGrid.length;j++){
                    if (edgeGrid[index][j]>0){
                        queue.offer(j);
                    }
                }
            }
        }
        return false;
    }

    public List<Vertex> topologicalSort(){
        if (!isDirected && hasCircle()){
            return null;
        }

        List<Vertex> res = new ArrayList<>();

        //1. Create an array to save the indegree of each vertex
        int[] indegree = new int[list.size()];
        for (int i=0; i<list.size(); i++){
            int count = 0;
            for (int row = 0; row<list.size();row++){
                if (edgeGrid[row][i]>0){
                    count++;
                }
            }
            indegree[i] = count;
        }

        int[] visited = new int[list.size()];
        while (true) {
            //2. Find a vertex whose indegree is 0
            int index = -1;
            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0 && visited[i]==0) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {//if all nodes are visited, end the loop
                break;
            }

            //3. add this vertex into result list
            res.add(list.get(index));
            visited[index]=1;
            //4. reduce the indegree for its related vertexes
            for (int i=0; i<edgeGrid.length;i++){
                if (edgeGrid[index][i]>0){
                    indegree[i]--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Vertex> list = new ArrayList<>();
        list.add(new Vertex(0));
        list.add(new Vertex(1));
        list.add(new Vertex(2));
        list.add(new Vertex(3));
        list.add(new Vertex(4));
        Graph2 graph = new Graph2(list,true);
        graph.addEdge(0,1, 3);
        graph.addEdge(0,3, 2);
        graph.addEdge(1,3, 5);
        graph.addEdge(1,2, 1);
        graph.addEdge(2,4, 6);
        graph.addEdge(3,4, 7);

        System.out.println(graph.DFS(0,new ArrayList<>()));
        System.out.println(graph.BFS());

        System.out.println(graph.Dijkstra(0,4));

        System.out.println(graph.hasCircle());

        System.out.println(graph.topologicalSort());
    }
}
