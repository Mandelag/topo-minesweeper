package com.mandelag.topoms.graph;

import java.util.Set;

/**
 * A graph like structure to store connectivity between nodes in an indexed list.
 * For example, given a list of 9 nodes, the index would be represented by:
 *
 * [
 *  {1, 3, 4},
 *  {0, 2, 3, 4, 5},
 *  {1, 4, 5},
 *  {0, 1, 4, 6, 7},
 *  {0, 1, 2, 3, 5, 6, 7, 8},
 *  {1, 2, 4, 7, 8},
 *  {3, 4, 7},
 *  {3, 4, 5, 6, 8},
 *  {4, 5, 7}
 * ]
 *
 * the followings could be inferred from the index:
 *  - node 0 is connected to node 1, 3, and 4.
 *  - node 1 is connected to node 0, 2, 3, 4, and 5.
 *  - ...
 *  - node 8 is connected to node 4, 5, and 7.
 *
 * A node could NOT be connected to itself.
 *
 * @version 6 April 2019
 * @author Keenan Gebze
 */
public interface IConnectivityIndex {
  /**
   * To obtain the connectivity of node with given index.
   *
   * @param index the index of the node.
   * @return set of indexes which are connected to the given node.
   */
  Set<Integer> getAdjacentNodeIndexes(int index);

  /**
   * Get the size of the indexes.
   *
   * @return the size of the connectivity index (or the number of node.)
   */
  int size();
}
