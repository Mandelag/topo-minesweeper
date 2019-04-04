package com.mandelag.topoms;

import com.mandelag.topoms.graph.IIndexedGraph;
import com.mandelag.topoms.graph.impl.IndexedGraph;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class IMinePlacerTest {

  final IMinePlacer genericPlacer = MinePlacers.random;
  final IMinePlacer randomPlacer = MinePlacers.random;

  /**
   * Build the com.mandelag.topoms.graph.
   * This represent simple 3 x 3 grid with the following indexes:
   *
   *  0 - 1 - 2
   *  3 - 4 - 5
   *  6 - 7 - 8
   *
   *  each of them connected to their surroundings (maximum of 8).
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
  final List<Integer> nodes = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
  final IIndexedGraph<Integer> map = new IndexedGraph<>(connectivity, nodes);

  @Test
  public void defaultRandomPlacerTest() {
    final IIndexedGraph<Integer> mapWithMines = randomPlacer.placeMines(map, 3);

    // validate by checking non negative nodes, that their surrounding have N mines.
    for(int i=0; i<mapWithMines.size(); i++) {
      int value = mapWithMines.get(i);
      int[] surroundingIndex = mapWithMines.getAdjacentNodeIndexes(i);
      int actualNumberOfSurroundingMines = 0;

      if (value < 0) continue;

      for(int j=0; j<surroundingIndex.length; j++) {
        if ( mapWithMines.get(surroundingIndex[j]) < 0) {
          actualNumberOfSurroundingMines += 1;
        }
      }
      assertEquals(value, actualNumberOfSurroundingMines);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void mineShouldBeLessThanNodes() {
    final IIndexedGraph<Integer> mapWithMines = randomPlacer.placeMines(map, 12);
  }
}
