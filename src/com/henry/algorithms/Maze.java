package com.henry.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二维矩阵代表一个迷宫，迷宫里面有通道，也有墙壁，通道由数字 0 表示，而墙壁由 -1 表示，有墙壁的地方不能通过
 */
public class Maze {
    public static class Grid {
        int row;
        int col;

        public Grid(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "[" + row + ", " + col + "]";
        }
    }

    int[][] maze;

    public Maze(int[][] maze){
        this.maze = maze;
    }

    public boolean isConnected(Grid current, Grid dest) {
        int[][] visited = new int[maze.length][maze.length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == -1){
                    visited[i][j] = -1;
                }
            }
        }
        return isConnectedDFS(maze,current,dest,visited);
    }

    private boolean isConnectedDFS(int[][] maze, Grid current, Grid dest, int[][]visited) {
        // 检查是否满足条件
        if (current.row == dest.row && current.col == dest.col){
            return true;
        }

        //更新visited 表
        visited[current.row][current.col] = 1;

        List<Grid> list = new ArrayList<>();
        list.add(new Grid(current.row-1, current.col));
        list.add(new Grid(current.row+1, current.col));
        list.add(new Grid(current.row, current.col-1));
        list.add(new Grid(current.row, current.col+1));

        //从四周邻居依次尝试
        for (Grid grid:list){
            //检查邻居是否合法
            if (grid.row < maze.length && grid.col < maze.length && grid.row>=0 && grid.col>=0){
                if (visited[grid.row][grid.col]==0){
                    if(isConnectedDFS(maze, grid, dest, visited)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //用于表示从A到B的一条路径，且这条路径的长度
    public static class ShortestPath {
        int length;
        List<Grid> path;

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            if (path!=null){
                for (Grid grid: path){
                    sb.append(grid.toString()).append("->");
                }
                sb.append("END");
            }
            return "ShortestPath{" +
                    "length=" + length +
                    ", path=" + sb.toString() +
                    '}';
        }
    }

    public List<ShortestPath> findShortestPath(Grid gridA, Grid gridB){
        //record the shortest path from A to each grid
        ShortestPath[][] paths = new ShortestPath[maze.length][maze.length];

        //set wall data and initialize valid grid to Integer.MAX_VALUE
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                paths[i][j] = new ShortestPath();
                if (maze[i][j] == -1){
                    paths[i][j].length = -1;
                }else {
                    paths[i][j].length = Integer.MAX_VALUE;
                }
            }
        }
        //set start grid in paths as 0, and path as empty array
        paths[gridA.row][gridA.col].length = 0;
        paths[gridA.row][gridA.col].path = new ArrayList<>();
        paths[gridA.row][gridA.col].path.add(gridA);

        List<ShortestPath> initialResult = new ArrayList<>();
        //Use DFS to find shortest paths
        findShortestPathDFS(maze,gridA,gridB,paths,initialResult);

        //最后再做筛选
        int minLength = Integer.MAX_VALUE;
        for (ShortestPath shortestPath:initialResult) {
            if (shortestPath.length<minLength){
                minLength = shortestPath.length;
            }
        }
        List<ShortestPath> result = new ArrayList<>();
        for (ShortestPath shortestPath:initialResult) {
            if (shortestPath.length == minLength){
                result.add(shortestPath);
            }
        }

        return result;
    }

    private void findShortestPathDFS(int[][] maze, Grid current, Grid dest, ShortestPath[][] paths, List<ShortestPath> result) {
        //1. check current grid is the dest grid, if yes add it into result
        if (current.row == dest.row && current.col==dest.col) {
            result.add(paths[current.row][current.col]);
            return;
        }

        //2. if not reached to dest, try its neighbours
        List<Grid> list = new ArrayList<>();
        list.add(new Grid(current.row-1, current.col));
        list.add(new Grid(current.row+1, current.col));
        list.add(new Grid(current.row, current.col-1));
        list.add(new Grid(current.row, current.col+1));

        for (Grid grid: list){
            //3. 检查邻居是否合法
            if (grid.row < maze.length && grid.col < maze.length && grid.row>=0 && grid.col>=0){
                // check if this grid is wall
                if (maze[grid.row][grid.col] != -1){
                    ShortestPath shortestPath = new ShortestPath();
                    shortestPath.length = paths[current.row][current.col].length + 1;
                    shortestPath.path = new ArrayList<>(paths[current.row][current.col].path);
                    shortestPath.path.add(grid);
                    //check if the path from gridA to this grid is the smallest
                    if (shortestPath.length <= paths[grid.row][grid.col].length){
                        //4. update shortest paths table
                        paths[grid.row][grid.col] = shortestPath;
                        //check if the path from gridA to this grid is smaller than current destination's shortest path
                        if (shortestPath.length <= paths[dest.row][dest.col].length){
                            //5. continue searching
                            findShortestPathDFS(maze, grid, dest, paths, result);
                        }
                    }
                }
            }
        }
    }

    public ShortestPath findOneShortestPath(Grid gridA, Grid gridB) {
        ShortestPath[][] paths = new ShortestPath[maze.length][maze.length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                ShortestPath path = new ShortestPath();
                if (maze[i][j]==-1){
                    path.length = -1;
                }else {
                    path.length = Integer.MAX_VALUE;
                }
                paths[i][j]=path;
            }
        }
        paths[gridA.row][gridA.col].length = 0;
        paths[gridA.row][gridA.col].path = new ArrayList<>();
        paths[gridA.row][gridA.col].path.add(gridA);

        return findOneShortestPathBFS(maze,gridA,gridB,paths);
    }

    private ShortestPath findOneShortestPathBFS(int[][] maze, Grid start, Grid dest, ShortestPath[][] paths) {
        ArrayDeque<Grid> queue = new ArrayDeque<>();
        queue.push(start);

        while (!queue.isEmpty()){
            Grid current = queue.poll();

            //analyze its neighbours
            List<Grid> list = new ArrayList<>();
            list.add(new Grid(current.row-1, current.col));
            list.add(new Grid(current.row+1, current.col));
            list.add(new Grid(current.row, current.col-1));
            list.add(new Grid(current.row, current.col+1));

            for (Grid grid:list){
                //valid the grid
                if (grid.row>=0&&grid.row<maze.length&&grid.col>=0&&grid.col<maze.length){
                    //check if the grid is a wall
                    if (maze[grid.row][grid.col]!=-1){
                        ShortestPath gridPath = new ShortestPath();
                        gridPath.length = paths[current.row][current.col].length+1;
                        gridPath.path = new ArrayList<>(paths[current.row][current.col].path);
                        gridPath.path.add(grid);
                        //check if the grid is the current shortest path from gridA
                        if (gridPath.length < paths[grid.row][grid.col].length){
                            //update paths table
                            paths[grid.row][grid.col] = gridPath;
                            //add the grid into queue
                            queue.offer(grid);
                            //check if the grid is the destination, if yes return it.
                            if (grid.row==dest.row && grid.col==dest.col) {
                                return gridPath;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[6][6];
        matrix[1][2] = -1;
        matrix[2][2] = -1;
        matrix[3][2] = -1;
        matrix[4][1] = -1;
        matrix[5][5] = -1;
        matrix[3][3] = -1;

        Maze maze = new Maze(matrix);

        Grid gridA = new Grid(0,2);
        Grid gridB = new Grid(4,2);

        //连通问题 DFS
        //System.out.println(maze.isConnected(gridA, gridB));
        //最短路径问题：
        //a. 找出并打印所有最短路径 DFS
        //System.out.println(maze.findShortestPath(gridA,gridB));
        //b. 求最短路径的值，并打印一条最短路径 BFS
        System.out.println(maze.findOneShortestPath(gridA,gridB));

    }
}
