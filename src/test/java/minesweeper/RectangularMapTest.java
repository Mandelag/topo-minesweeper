package minesweeper;

import static org.junit.Assert.*;
import org.junit.Test;

public class RectangularMapTest {
  @Test
  public void rectangularMapConnectivityTest() {
    int[][] rectangularConnectivity = Maps.rectangularConnectivity(3, 3);

    // connectivity of 3 x 3 grid
    int[][] connectivity = new int[][] {
        new int[]{1, 3, 4}, // 0
        new int[]{0, 2, 3, 4, 5},
        new int[]{1, 4, 5},
        new int[]{0, 1, 4, 6, 7},
        new int[]{0, 1, 2, 3, 5, 6, 7, 8},
        new int[]{1, 2, 4, 7, 8},
        new int[]{3, 4, 7},
        new int[]{3, 4, 5, 6, 8},
        new int[]{4, 5, 7}     // 8
    };

    for(int i=0; i<rectangularConnectivity.length; i++) {
      for(int j=0; j<rectangularConnectivity[i].length; j++) {
        System.out.print(rectangularConnectivity[i][j] +" ");
      }
      System.out.println("");
    }

    assertArrayEquals(connectivity, rectangularConnectivity);
  }
}
