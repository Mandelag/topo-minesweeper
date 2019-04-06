package com.mandelag.topoms;

import com.mandelag.topoms.graph.IIndexedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class DefaultFieldOpeners implements IFieldOpener {
  public int[] open(IIndexedGraph<Integer> graph, int index) {
    int value = graph.get(index);

    if ( value < 0 ) {  // mine opened
      int[] result = new int[graph.size()];
      for(int i=0; i<graph.size(); i++) {
        result[i] = i;
      }
      return result;
    } else { // non-mine opened

      int[] visited = new int[graph.size()];
      openRecursively(visited, graph, index);

      //convert the visited index (array of 0's and 1's) into index array
      List<Integer> tempResult = new ArrayList<>();

      for(int i=0; i<visited.length; i++) {
        if(visited[i] == 1) {
          tempResult.add(i);
        }
      }

      int[] result = new int[tempResult.size()];
      for(int i=0; i<tempResult.size(); i++) {
        result[i] = tempResult.get(i);
      }

      return result;
    }
  }

  private void openRecursively(int[] visitedNodes, IIndexedGraph<Integer> graph, int index) {
    if(visitedNodes[index] == 1) return;
    int value = graph.get(index);
    visitedNodes[index] = 1;

    if( value != 0 ) return;

    Set<Integer> surroundingNodes = graph.getAdjacentNodeIndexes(index);
    for(int i : surroundingNodes) {
      openRecursively(visitedNodes, graph, i);
    }
  }
}
