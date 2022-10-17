package dijkstra.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {
    public void ShortestP(Vert sourceV) {
        sourceV.setDist(0);
        PriorityQueue<Vert> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceV);
        sourceV.setVisited(true);
        //System.out.println(sourceV.toString());
        while (!priorityQueue.isEmpty()) {
        	
            Vert actualVertex = priorityQueue.poll();
            //System.out.println(actualVertex.toString());
            for (Edge edge : actualVertex.getList()) {
                Vert v = edge.getTargetVert();
              //System.out.println("Đỉnh xét: " +  v.toString());
                if (!v.Visited()) {
                    double newDistance = actualVertex.getDist() + edge.getWeight();
                    if (newDistance < v.getDist()) {
                        priorityQueue.remove(v);
                        v.setDist(newDistance);
                        v.setPr(actualVertex);
                        priorityQueue.add(v);
                    }
                }
                //System.out.println("A - > " + v.toString() + ": " + v.getDist());
                //System.out.println(v.getPr() + " - > " + v.toString());
            }
            actualVertex.setVisited(true);
        }
    }

    public List<Vert> getShortestP(Vert targetVertex) {
        List<Vert> path = new ArrayList<>();
        for (Vert vertex = targetVertex; vertex != null; vertex = vertex.getPr()) {
            path.add(vertex);
        }
        Collections.reverse(path);
        if (path.size() == 1) System.out.print("không có đường đi từ ");
        return path;
    }

}
