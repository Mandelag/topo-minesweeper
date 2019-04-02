package minesweeper;

import java.util.*;

import topojson.IPolygon;

public class Utils {
  public static int[][] getConnectivityMatrix(List<IPolygon> polygons) {
    int[][] result = new int[polygons.size()][];
    Map<Integer, Set<Integer>> arcs = new HashMap<>(); // mapping between a boundary and their polygons
    List<Set<Integer>> connectivityMatrix = new ArrayList<>();

    for (int i=0; i<polygons.size(); i++) {
      IPolygon polygon = polygons.get(i);
      int[] outerRingArcIndexes = polygon.getArcs()[0];
      for(int j=0; j<outerRingArcIndexes.length; j++) {
        Set<Integer> neighboringPolygons = arcs.getOrDefault(outerRingArcIndexes[j], new HashSet<>());
        neighboringPolygons.add(i);
        arcs.put(outerRingArcIndexes[j], neighboringPolygons);
      }
    }

    // append all the connected polygons obtained from shared polygons arcs
    for (int i=0; i<polygons.size(); i++) {
      IPolygon polygon = polygons.get(i);
      int[] outerRingArcIndexes = polygon.getArcs()[0];
      Set<Integer> neighboorIndexes = new HashSet<>();
      for(int j=0; j<outerRingArcIndexes.length; j++) {
        Set<Integer> connectedPolygons = arcs.get(outerRingArcIndexes[j]);
        neighboorIndexes.addAll(connectedPolygons);
      }
      neighboorIndexes.remove(i);
      connectivityMatrix.add(neighboorIndexes);
    }

    // convert to array
    for(int i=0; i<result.length; i++) {
      Set<Integer> neighboorIndexes = connectivityMatrix.get(i);
      int[] converted = new int[neighboorIndexes.size()];
      Iterator<Integer> iter = neighboorIndexes.iterator();
      for(int j=0;j<converted.length; j++) {
        converted[j] = iter.next();
      }
      result[i] = converted;
    }
    return result;
  }
}
