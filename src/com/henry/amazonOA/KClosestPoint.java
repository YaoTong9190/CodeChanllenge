package com.henry.amazonOA;
import java.util.*;
public class KClosestPoint {
        public int[][] kClosest(int[][] points, int K) {
            class Point{
                int x;
                int y;
                public Point(int x, int y){
                    this.x = x;
                    this.y = y;
                }

                public int distance(){
                    return x*x+y*y;
                }
            }
            PriorityQueue<Point> queue = new PriorityQueue<>(
                    (p1, p2) -> {
                        return p1.distance() - p2.distance();
                    }
            );
            for(int i=0; i<points.length;i++){
                int x = points[i][0];
                int y = points[i][1];
                queue.offer(new Point(x,y));
            }

            int[][] res = new int[K][2];
            for(int i=0; i<K;i++){
                Point point = queue.poll();
                res[i][0] = point.x;
                res[i][1] = point.y;
            }
            return res;
        }
}
