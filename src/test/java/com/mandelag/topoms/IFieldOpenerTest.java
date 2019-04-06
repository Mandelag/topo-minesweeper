package com.mandelag.topoms;

import com.mandelag.topoms.graph.IIndexedGraph;
import com.mandelag.topoms.graph.impl.ConnectivityIndex;
import com.mandelag.topoms.graph.impl.IndexedGraph;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class IFieldOpenerTest {

  /**
   * Build the com.mandelag.topoms.graph.
   * This represent simple 3 x 3 grid with the following indexes:
   *
   *  0 - 1 - 2
   *  3 - 4 - 5
   *  6 - 7 - 8
   *
   *  each of them connected to their surroundings (maximum of 8).
   *  with nodes:
   *
   *  -999 1  0
   *    1  1  0
   *    0  0  0
   *
   *   note:
   *    - negative value represent mines
   */
  final int[][] connectivity = new int[][] {
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
  final List<Integer> nodes = Arrays.asList(-999, 1, 0, 1, 1, 0, 0, 0, 0);
  final IIndexedGraph<Integer> mapWithMines = new IndexedGraph<>(connectivity, nodes);
  IFieldOpener opener = new DefaultFieldOpeners();

  @Test
  public void mineOpened() {
    int[] indexesToOpen = opener.open(mapWithMines, 0);
    assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, indexesToOpen);
  }

  @Test
  public void nonZeroValueOpened() {
    int[] indexesToOpen = opener.open(mapWithMines, 4);
    assertArrayEquals(new int[]{4}, indexesToOpen);
  }

  @Test
  public void zeroValueOpened() {
    int[] indexesToOpen = opener.open(mapWithMines, 5);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, indexesToOpen);
  }
}
