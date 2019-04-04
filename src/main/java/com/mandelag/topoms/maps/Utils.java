package com.mandelag.topoms.maps;

import java.util.*;

import com.mandelag.topoms.topojson.IPolygon;

public class Utils {

  /**
   * Extract connectivity matrix from list of Polygons object.
   *
   * @param polygons list of polygons object
   * @return connectivity matrix of the polygons who shared the same boundary
   */
  public static int[][] getConnectivityMatrix(List<IPolygon> polygons) {
    int[][] result = new int[polygons.size()][];
    Map<Integer, Set<Integer>> arcs = new HashMap<>(); // mapping between a boundary and their polygons
    List<Set<Integer>> connectivityMatrix = new ArrayList<>();

    for (int i=0; i<polygons.size(); i++) {
      IPolygon polygon = polygons.get(i);
      int[] outerRingArcIndexes = polygon.getArcs()[0];
      for(int j=0; j<outerRingArcIndexes.length; j++) {
        int normalizedArcIndex = outerRingArcIndexes[j] >= 0 ? outerRingArcIndexes[j] : ~outerRingArcIndexes[j];
        Set<Integer> neighboringPolygons = arcs.getOrDefault(normalizedArcIndex, new HashSet<>());
        neighboringPolygons.add(i);
        arcs.put(normalizedArcIndex, neighboringPolygons);
      }
    }

    // append all the connected polygons obtained from shared polygons arcs
    for (int i=0; i<polygons.size(); i++) {
      IPolygon polygon = polygons.get(i);
      int[] outerRingArcIndexes = polygon.getArcs()[0];
      Set<Integer> neighboorIndexes = new HashSet<>();
      for(int j=0; j<outerRingArcIndexes.length; j++) {
        int normalizedArcIndex = outerRingArcIndexes[j] >= 0 ? outerRingArcIndexes[j] : ~outerRingArcIndexes[j];
        Set<Integer> connectedPolygons = arcs.get(normalizedArcIndex);
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


  /**
   * Generate connectivity matrix for rectangular maps (like the original com.mandelag.topoms.core.minesweeper).
   *
   * @param width the width
   * @param height the height
   * @return connectivity matrix representing rectangular maps
   */
  public static int[][] rectangularConnectivity(int width, int height) {
    List<Integer> connectivity = new ArrayList<>();
    int[][] result = new int[width*height][];

    for(int i=0; i<width*height; i++) {
      connectivity.clear();
      boolean left = i%width == 0;
      boolean right = (i+1) %width == 0;
      boolean top = i < width;
      boolean bottom = i + width >= width*height;

      if(!left) {
        connectivity.add(i-1);
      }
      if (!right) {
        connectivity.add(i+1);
      }

      if(!top) {
        connectivity.add(i - width);
        if(!left) {
          connectivity.add(i-width-1);
        }
        if(!right) {
          connectivity.add(i-width+1);
        }
      }

      if (!bottom){
        connectivity.add(i + width);
        if(!left) {
          connectivity.add(i+width-1);
        }
        if(!right) {
          connectivity.add(i+width+1);
        }
      }
      // TODO change the IIndexedGraph in the future to use List<List<Integer>>, maybe?
      int[] con = new int[connectivity.size()];
      for(int j=0; j<con.length; j++) {
        con[j] = connectivity.get(j);
      }
      result[i] = con;
    }
    return result;
  }
}
