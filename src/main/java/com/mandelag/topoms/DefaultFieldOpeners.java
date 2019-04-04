package com.mandelag.topoms;

import com.mandelag.topoms.graph.IIndexedGraph;

import java.util.ArrayList;
import java.util.List;


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

  private int[] openRecursively(int[] visitedNodes, IIndexedGraph<Integer> graph, int index) {
    if(visitedNodes[index] == 1) return visitedNodes;

    int value = graph.get(index);
    visitedNodes[index] = 1;

    if( value == 0 ) { // open
      int[] surroundingNodes = graph.getAdjacentNodeIndexes(index);
      for(int i=0; i<surroundingNodes.length; i++) {
        openRecursively(visitedNodes, graph, surroundingNodes[i]);
      }
    }

    return visitedNodes;
  }
}