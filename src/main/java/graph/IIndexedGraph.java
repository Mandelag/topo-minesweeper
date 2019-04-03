package graph;

import java.util.List;

/**
 * Main interface for data structure used in this game.
 * @param <T> Graph node's type.
 */
public interface IIndexedGraph<T> {
  int[] getAdjacentNodeIndexes(int index);
  List<T> getAdjacentNodes(int index);
  T get(int index);
}
