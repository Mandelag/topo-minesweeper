package com.mandelag.topoms.topojson;

/**
 * IPolygon basic implementation.
 */
public class Polygon implements IPolygon {
  private final long id;
  private final int[][] arcs;

  public Polygon(long id, int[][] arcs) {
    this.id = id;
    this.arcs = arcs;
  }

  public long getId() {
    return this.id;
  }

  public int[][] getArcs() {
    // copy the arcs because this class is immutable
    int[][] copy = new int[this.arcs.length][];

    for(int i=0; i<copy.length;i++) {
      int[] copy2 = new int[this.arcs[i].length];
      System.arraycopy(this.arcs[i], 0, copy2, 0, this.arcs[i].length);
      copy[i] = copy2;
    }

    return copy;
  }
}
