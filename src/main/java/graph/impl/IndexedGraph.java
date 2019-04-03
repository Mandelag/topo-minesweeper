package graph.impl;

import graph.IIndexedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation of the IndexedGraph interface.
 * @param <T> node type.
 */
public class IndexedGraph<T> implements IIndexedGraph<T> {

  private final int[][] CONNECTIVITY_MATRIX;
  private final List<T> nodes;

  public IndexedGraph(int[][] connectivityMatrix, List<T> initialNodes) {
    if (connectivityMatrix.length != initialNodes.size()) {
      throw new IllegalStateException("connectivityMatrix size should be equal with intialNodes size.");
    }
    // create the immutable copy
    int[][] immutableCopy = new int[connectivityMatrix.length][];
    for(int i=0; i<connectivityMatrix.length; i++) {
      int[] copy = new int[connectivityMatrix[i].length];
      System.arraycopy(connectivityMatrix[i], 0, copy, 0, connectivityMatrix[i].length);
      immutableCopy[i] = copy;
    }

    this.CONNECTIVITY_MATRIX = immutableCopy;
    this.nodes = Collections.unmodifiableList(initialNodes);
  }

  public int[] getAdjancentNodeIndexes(int index) {
    return this.CONNECTIVITY_MATRIX[index];
  }

  public List<T> getAdjancentNodes(int index) {
    int[] neighboringNodeIndexes = this.getAdjancentNodeIndexes(index);
    List<T> result = new ArrayList<>();
    for(int i=0; i<neighboringNodeIndexes.length; i++) {
      result.add(i, this.get(neighboringNodeIndexes[i]));
    }
    return result;
  }

  public T get(int index) {
    return this.nodes.get(index);
  }

  public void set(int index, T node) {
    this.nodes.set(index, node);
  }

}
