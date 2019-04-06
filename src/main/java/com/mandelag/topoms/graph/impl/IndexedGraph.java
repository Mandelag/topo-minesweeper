package com.mandelag.topoms.graph.impl;

import com.mandelag.topoms.graph.IConnectivityIndex;
import com.mandelag.topoms.graph.IIndexedGraph;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of the IndexedGraph interface.
 * @param <T> node type.
 */
public class IndexedGraph<T> implements IIndexedGraph<T> {

  private final IConnectivityIndex CONNECTIVITY_MATRIX;
  private final List<T> nodes;

  public IndexedGraph(IConnectivityIndex connectivityMatrix, List<T> initialNodes) {
    if (connectivityMatrix.size() != initialNodes.size()) {
      throw new IllegalStateException("connectivityMatrix size should be equal with intialNodes size.");
    }
    this.CONNECTIVITY_MATRIX = connectivityMatrix;
    this.nodes = Collections.unmodifiableList(initialNodes);
  }
  public IndexedGraph(int[][] connectivityMatrix, List<T> initialNodes) {
    this(new ConnectivityIndex(connectivityMatrix), initialNodes);
  }

  public Set<Integer> getAdjacentNodeIndexes(int index) {
    return CONNECTIVITY_MATRIX.getAdjacentNodeIndexes(index);
  }

  public T get(int index) {
    return this.nodes.get(index);
  }

  public int size() {
    return this.nodes.size();
  }

  public IIndexedGraph<T> setNodes(List<T> newNodes) {
    return new IndexedGraph<T>(this.CONNECTIVITY_MATRIX, newNodes);
  }

}
