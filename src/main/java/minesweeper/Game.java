package minesweeper;

import graph.IIndexedGraph;

public class Game {
  private IIndexedGraph<Integer> minesweeperMap;

  public Game(IIndexedGraph<Integer> minesweeperMap) {
    this.minesweeperMap = minesweeperMap;
  }
}
