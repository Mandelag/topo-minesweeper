package minesweeper;

import graph.IIndexedGraph;
import graph.impl.IndexedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maps {

  /**
   * Messy algorithm
   * @param width
   * @param height
   * @return
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
      // TODO change the IIndexedGraph in the future to use List<List<Integer>>
      int[] con = new int[connectivity.size()];
      for(int j=0; j<con.length; j++) {
        con[j] = connectivity.get(j);
      }
      Arrays.sort(con);
      result[i] = con;
    }
    return result;
  }
}
