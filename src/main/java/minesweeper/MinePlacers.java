package minesweeper;

import java.util.*;

public class MinePlacers {
  public static final IMinePlacer random = (graph, mines) -> {
    List<Integer> newNodes = new ArrayList<>(graph.size());
    List<Integer> list = new ArrayList<>(graph.size());
    List<Integer> nodesWithMine = new ArrayList<>(mines);

    for(int i=0; i<graph.size(); i++) {
      list.add(i);
      newNodes.add(0);
    }

    Random randomizer = new Random();
    for(int m=0; m<mines; m++) {
      int selectRandom = randomizer.nextInt(list.size());
      nodesWithMine.add(list.remove(selectRandom));
    }

    // populate the map with mines value (-99), and increase the surrounding nodes value.
    for(int nodeWithMine : nodesWithMine) {
      newNodes.set(nodeWithMine, -999);
      int[] neighboringNodesIndex = graph.getAdjacentNodeIndexes(nodeWithMine);
      for(int i=0; i<neighboringNodesIndex.length; i++) {
        int newValue = newNodes.get(neighboringNodesIndex[i]) + 1;
        newNodes.set(neighboringNodesIndex[i], newValue);
      }
    }

    return graph.setNodes(newNodes);
  };
}
