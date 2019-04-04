package minesweeper;

import graph.IIndexedGraph;
import graph.impl.IndexedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maps {
  public static IIndexedGraph<Integer> NINE_BY_NINE = new IndexedGraph<>(
      rectangularConnectivity(9, 9),
      new ArrayList<>()
  );

  /**
   * Messy algorithm
   * @param width
   * @param height
   * @return
   */
  private static int[][] rectangularConnectivity(int width, int height) {
    List<Integer> connectivity = new ArrayList<>();
    int[][] result = new int[width*height][];

    for(int i=0; i<width*height; i++) {
      connectivity.clear();
      boolean notLeft = i%width != 0;
      boolean notRight = i%width != width-1;
      boolean notTop = i >= width;
      boolean notBottom = i + width < width*height;

      if(notLeft) {
        connectivity.add(i-1);
      } else if (notRight) {
        connectivity.add(i+1);
      }

      if(notTop) {
        connectivity.add(i - width);
        if(notLeft) {
          connectivity.add(i-width-1);
        }
        if(notRight) {
          connectivity.add(i-width+1);
        }

      } else if (notBottom){
        connectivity.add(i + width);
        if(notLeft) {
          connectivity.add(i+width-1);
        }
        if(notRight) {
          connectivity.add(i+width+1);
        }
      }
      // TODO change the IIndexedGraph in the future to use List<List<Integer>>
      int[] con = new int[connectivity.size()];
      for(int j=0; j<con.length; j++) {
        con[j] = connectivity.get(i);
      }
      result[i] = con;
    }
    return result;
  }
}
