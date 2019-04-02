package minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import topojson.IPolygon;

public class Utils {
  public static void polygonsToGraph(List<IPolygon> polygons) {
    Map<String, Integer> externalToInternalIdMapping = new HashMap<>(polygons.size()); // differentiate between Id in the input poylgons and id represented in graph
    Map<Integer, List<IPolygon>> arcs = new HashMap<>(); // mapping between a boundary and their polygons

    for (IPolygon polygon : polygons) {
      int[] outerRingArcIndexes = polygon.getArcs()[0];
      for(int i=0; i<outerRingArcIndexes.length; i++) {
        List<IPolygon> neighboringPolygons = arcs.getOrDefault(outerRingArcIndexes[i], new ArrayList<>());
        neighboringPolygons.add(polygon);
      }
    }


  }
}
