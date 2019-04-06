package com.mandelag.topoms.graph.impl;

import com.mandelag.topoms.graph.IConnectivityIndex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Basic implementation of IConnectivityIndex interface.
 *
 * @version 6 April 2019
 * @author Keenan Gebze
 */
public class ConnectivityIndex implements IConnectivityIndex {

  private final Set<Integer>[] connectivityIndexes;

  public ConnectivityIndex(int[][] connectivityIndexes) {
    Set<Integer>[] indexes = new Set[connectivityIndexes.length];
    for(int i=0; i<connectivityIndexes.length; i++) {
      Set<Integer> con = new HashSet<>(connectivityIndexes[i].length);
      con.remove(i);
      for(int j=0; j<connectivityIndexes[i].length; j++) {
        con.add(connectivityIndexes[i][j]);
      }
      indexes[i] = con;
    }
    this.connectivityIndexes = indexes;
  }

  public ConnectivityIndex(Set<Integer> ...connectivityIndexes) {
    Set<Integer>[] indexes = new Set[connectivityIndexes.length];
    for(int i=0; i<connectivityIndexes.length; i++) {
      indexes[i] = new HashSet<>(connectivityIndexes[i]);
      indexes[i].remove(i);
    }
    this.connectivityIndexes = indexes;
  }

  @Override
  public Set<Integer> getAdjacentNodeIndexes(int index) {
    return connectivityIndexes[index];
  }

  @Override
  public int size() {
    return connectivityIndexes.length;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(connectivityIndexes);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ConnectivityIndex) {
      ConnectivityIndex c2 = (ConnectivityIndex) o;
      return this.hashCode() == c2.hashCode();
    }
    return false;
  }
}
