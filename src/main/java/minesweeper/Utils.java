package minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import topojson.IPolygon;

public class Utils {
  public static void polygonsToGraph(List<IPolygon>) {
    Map<String, Integer> externalToInternalIdMapping = new HashMap<>(polygons.size()); // differentiate between Id in the input poylgons and id represented in graph
    Map<Integer, Map<String, Object>> arcs = new HashMap<>(); // mapping between a boundary and their polygons

    for ( Map<String, Object> polygon : polygons ) {
      String externalId = String.valueOf(polygon.get("id"));
      List<List<Integer>> polygonRings = (List<List<Integer>>) polygon.get("arcs");
      List<Integer> outerRingArcIndexes = polygonRings.get(0);
      if (externalId == null) {
        throw new NullPointerException("Polygon objects must have a valid \"id\" attribute.");
      }
      if(outerRingArcIndexes == null){
        throw new NullPointerException("Polygon object must have a valid \"arcs\" attribute.");
      }

      for( Integer boundaryIndex : outerRingArcIndexes) {
        List<Object> neighboringPolygons = arcs.getOrDefault(boundaryIndex, new ArrayList<Object>());
        neighboringPolygons.add(polygons);
      }
    }
  }
}
