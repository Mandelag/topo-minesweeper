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

  public int[] getAdjacentNodeIndexes(int index) {
    int[] result = new int[this.CONNECTIVITY_MATRIX[index].length];
    System.arraycopy(this.CONNECTIVITY_MATRIX[index], 0, result, 0, this.CONNECTIVITY_MATRIX[index].length);
    return result;
  }

  public List<T> getAdjacentNodes(int index) {
    int[] neighboringNodeIndexes = this.getAdjacentNodeIndexes(index);
    List<T> result = new ArrayList<>();
    for(int i=0; i<neighboringNodeIndexes.length; i++) {
      result.add(i, this.get(neighboringNodeIndexes[i]));
    }
    return result;
  }

  public T get(int index) {
    return this.nodes.get(index);
  }

  public int size() {
    return this.nodes.size();
  }

  public List<T> getNodes() {
    return this.nodes;
  }

  public IIndexedGraph<T> setNodes(List<T> newNodes) {
    return new IndexedGraph<>(this.CONNECTIVITY_MATRIX, newNodes);
  }

}
