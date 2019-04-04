package minesweeper;

import graph.IIndexedGraph;

public interface IMinePlacer {
  IIndexedGraph<Integer> placeMines(IIndexedGraph<Integer> minesweeperMap, int numberOfMines);
}
