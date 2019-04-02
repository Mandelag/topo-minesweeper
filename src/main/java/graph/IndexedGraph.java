package graph;

/**
 * Main interface for data structure used in this game.
 * @param <T> Graph node's type.
 */
public interface IndexedGraph<T> {
  int[] getAdjancentNodesIndex();
  T[] getAdjancentNodes();
  T get(int index);
  void set(int index, T node);
}
