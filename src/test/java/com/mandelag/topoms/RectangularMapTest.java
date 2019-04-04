package com.mandelag.topoms;

import static org.junit.Assert.*;

import com.mandelag.topoms.maps.Utils;
import org.junit.Test;

import java.util.Arrays;

public class RectangularMapTest {
  @Test
  public void rectangularMapConnectivityTest() {
    int[][] rectangularConnectivity = Utils.rectangularConnectivity(3, 3);

    // sort the map to fit into test data
    for(int i=0; i<rectangularConnectivity.length; i++) {
      Arrays.sort(rectangularConnectivity[i]);
    }

    // connectivity of 3 x 3 grid
    int[][] connectivity = new int[][] {
        {1, 3, 4}, // 0
        {0, 2, 3, 4, 5},
        {1, 4, 5},
        {0, 1, 4, 6, 7},
        {0, 1, 2, 3, 5, 6, 7, 8},
        {1, 2, 4, 7, 8},
        {3, 4, 7},
        {3, 4, 5, 6, 8},
        {4, 5, 7}     // 8
    };

    assertArrayEquals(connectivity, rectangularConnectivity);
  }

  /**
   *  0,  1,  2,  3,  4,
   *  5,  6,  7,  8,  9,
   * 10, 11, 12, 13, 14,
   * 15, 16, 17, 18, 19
   */
  @Test
  public void rectangular20x4MapConnectivityTest() {

    int[][] rectangularConnectivity = Utils.rectangularConnectivity(5, 4);

    // sort the map to fit into test data
    for(int i=0; i<rectangularConnectivity.length; i++) {
      Arrays.sort(rectangularConnectivity[i]);
    }

    // connectivity of 5 x 4 grid
    int[][] connectivity = new int[][] {
        {1, 5, 6}, // 0
        {0, 2, 5, 6, 7},
        {1, 3, 6, 7, 8},
        {2, 4, 7, 8, 9},
        {3, 8, 9},
        {0, 1, 6, 10, 11},
        {0, 1, 2, 5, 7, 10, 11, 12},
        {1, 2, 3, 6, 8, 11, 12, 13},
        {2, 3, 4, 7, 9, 12, 13, 14},
        {3, 4, 8, 13, 14},
        {5, 6, 11, 15, 16},
        {5, 6, 7, 10, 12, 15, 16, 17},
        {6, 7, 8, 11, 13, 16, 17, 18},
        {7, 8, 9, 12, 14, 17, 18, 19},
        {8, 9, 13, 18, 19},
        {10, 11, 16},
        {10, 11, 12, 15, 17},
        {11, 12, 13, 16, 18},
        {12, 13, 14, 17, 19},
        {13, 14, 18}
    };

    assertArrayEquals(connectivity, rectangularConnectivity);
  }
}
