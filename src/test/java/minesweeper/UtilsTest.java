package minesweeper;

import org.junit.Test;
import static org.junit.Assert.*;

import topojson.IPolygon;
import topojson.Polygon;

import java.util.Arrays;
import java.util.List;

public class UtilsTest {

  /**
   * Suppose we want to create a graph for following shared boundaries:
   * Note: Letters represent polygons, Number represent shared boundaries.
   *
   *        B
   *       /
   *      0
   *     /
   *    A--1--D--5--F
   *    |     |
   *    2     4
   *    |     |
   *    C--3--E
   *
   *  We would expect to gather these connectivity matrix:
   *
   *  [
   *    [1, 2, 3], // A connected to B (1), C (2), and D (3).
   *    [0],       // B connected to A (0).
   *    [0, 4],    // ...
   *    [0, 4, 5],
   *    [2, 3],
   *    [3]        // E connected to D (3).
   *  ]
   */
  @Test
  public void getConnectivityMatrixTest() {
    List<IPolygon> polygons = Arrays.asList(new IPolygon[]{
        new Polygon(0, new int[][]{{0, 1, 2}}),  // A
        new Polygon(1, new int[][]{{0}}),  // B
        new Polygon(2, new int[][]{{2, 3}}),  // C
        new Polygon(3, new int[][]{{1, 4, 5}}),     // D
        new Polygon(4, new int[][]{{3, 4}}),    // E
        new Polygon(5, new int[][]{{5}}) // F
    });

    int[][] connectivityMatrix = Utils.getConnectivityMatrix(polygons);
    assertArrayEquals(new int[][] {
        {1, 2, 3},
        {0},
        {0, 4},
        {0, 4, 5},
        {2, 3},
        {3},
    }, connectivityMatrix);
  }

  /**
   * We also have to handle arc indexes with negative value according to the TopoJSON spec article 2.1.4:
   * https://github.com/topojson/topojson-specification
   *
   * This test is basically the same as getConnectivityMatrixTest() test.
   */
  @Test
  public void getConnectivityMatrixWithNegativeIndexesTest() {
    List<IPolygon> polygons = Arrays.asList(new IPolygon[]{
        new Polygon(0, new int[][]{ new int[]{0, 1, -3}}),  // A
        new Polygon(1, new int[][]{ new int[]{0}}),  // B
        new Polygon(2, new int[][]{ new int[]{2, -4}}),  // C
        new Polygon(3, new int[][]{ new int[]{1, -5, 5}}),     // D
        new Polygon(4, new int[][]{ new int[]{3, 4}}),    // E
        new Polygon(5, new int[][]{ new int[]{5}}) // F
    });

    int[][] connectivityMatrix = Utils.getConnectivityMatrix(polygons);

    assertArrayEquals(new int[][] {
        new int[]{1, 2, 3},
        new int[]{0},
        new int[]{0, 4},
        new int[]{0, 4, 5},
        new int[]{2, 3},
        new int[]{3},
    }, connectivityMatrix);
  }
}
