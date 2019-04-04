package com.mandelag.topoms;

import com.mandelag.topoms.graph.IIndexedGraph;

public interface IFieldOpener {
  /**
   * Return indexes that could be opened according to game logic and with free to implement algorithm.
   * @param index
   * @return
   */
  int[] open(IIndexedGraph<Integer> graph, int index);
}
