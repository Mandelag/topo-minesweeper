package minesweeper;

import graph.IIndexedGraph;

public class Minesweeper {

  private IIndexedGraph<Integer> map;
  private IMinePlacer minePlacer;
  private IFieldOpener fieldOpener;

  // TODO provide a list of ready to use maps.

  public Minesweeper(IIndexedGraph<Integer> map, int numberOfMines) {
    this(map, numberOfMines, MinePlacers.random, new DefaultFieldOpeners());
  }

  public Minesweeper(IIndexedGraph<Integer> map, int numberOfMines, IMinePlacer minePlacerAlgorithm, IFieldOpener fieldOpenerAlgorithm) {
    this.map = minePlacerAlgorithm.placeMines(map, numberOfMines);
    this.minePlacer = minePlacerAlgorithm;
    this.fieldOpener = fieldOpenerAlgorithm;
  }

  public void reset(int numberOfMines) {
    minePlacer.placeMines(map, numberOfMines);
  }

  int[] open(int index){
    return fieldOpener.open(map, index);
  }
}
