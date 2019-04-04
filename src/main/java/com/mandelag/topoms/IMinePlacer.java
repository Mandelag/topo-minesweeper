package com.mandelag.topoms;

import com.mandelag.topoms.graph.IIndexedGraph;

public interface IMinePlacer {
  IIndexedGraph<Integer> placeMines(IIndexedGraph<Integer> minesweeperMap, int numberOfMines);
}
