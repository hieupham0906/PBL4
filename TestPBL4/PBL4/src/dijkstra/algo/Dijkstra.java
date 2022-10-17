package dijkstra.algo;

public class Dijkstra {
    public static void main(String[] args) {

        Vert vA = new Vert("A");
        Vert vB = new Vert("B");
        Vert vC = new Vert("C");
        Vert vD = new Vert("D");
        Vert vE = new Vert("E");
        Vert vF = new Vert("F");

        vA.addNeighbour(new Edge(2, vA, vF));
        vA.addNeighbour(new Edge(4, vA, vB));
        vC.addNeighbour(new Edge(3, vC, vB));
        vC.addNeighbour(new Edge(2, vC, vD));
        vB.addNeighbour(new Edge(3, vB, vC));
        vB.addNeighbour(new Edge(4, vB, vA));
        vB.addNeighbour(new Edge(3, vB, vE));
        vD.addNeighbour(new Edge(2, vD, vC));
        vD.addNeighbour(new Edge(1, vD, vE));
        vE.addNeighbour(new Edge(1, vE, vD));
        vE.addNeighbour(new Edge(3, vE, vB));
        vE.addNeighbour(new Edge(3, vE, vF));
        vF.addNeighbour(new Edge(3, vF, vE));
        vF.addNeighbour(new Edge(2, vF, vA));

        PathFinder shortestPath = new PathFinder();
        shortestPath.ShortestP(vA);
        System.out.println("Khoảng cách tối thiểu từ:");
        System.out.println("A đến B: " + vB.getDist());
        System.out.println("A đến C: " + vC.getDist());
        System.out.println("A đến D: " + vD.getDist());
        System.out.println("A đến E: " + vE.getDist());
        System.out.println("A đến F: " + vF.getDist());
        
        System.out.println("Đường đi ngắn nhất từ:");
        System.out.println("A đến B: " + shortestPath.getShortestP(vB));
        System.out.println("A đến C: " + shortestPath.getShortestP(vC));
        System.out.println("A đến D: " + shortestPath.getShortestP(vD));
        System.out.println("A đến E: " + shortestPath.getShortestP(vE));
        System.out.println("A đến F: " + shortestPath.getShortestP(vF));

    }
}
