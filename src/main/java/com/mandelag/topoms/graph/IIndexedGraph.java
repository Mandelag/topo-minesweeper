package com.mandelag.topoms.graph;

import java.util.List;
import java.util.Set;

/**
 * Main interface for data structure used in this game.
 * @param <T> Graph node's type.
 */
public interface IIndexedGraph<T> {
  Set<Integer> getAdjacentNodeIndexes(int index);
  T get(int index);
  int size();
  IIndexedGraph<T> setNodes(List<T> newNodes);
}
