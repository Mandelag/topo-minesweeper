package com.mandelag.topoms.topojson;

/**
 * An (incomplete) specification of TopoJSON's Polygon Object in Java.
 * https://github.com/topojson/topojson-specification
 */
public interface IPolygon {
  int[][] getArcs();
  long getId();
}
