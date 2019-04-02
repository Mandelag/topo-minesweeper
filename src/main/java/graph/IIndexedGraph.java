package graph;

import java.util.List;

/**
 * Main interface for data structure used in this game.
 * @param <T> Graph node's type.
 */
public interface IIndexedGraph<T> {
  int[] getAdjancentNodeIndexes(int index);
  List<T> getAdjancentNodes(int index);
  T get(int index);
  void set(int index, T node);
}
