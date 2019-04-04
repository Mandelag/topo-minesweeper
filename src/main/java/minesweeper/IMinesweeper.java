package minesweeper;

/**
 * Provides the interface of Minesweeper game, but WITHOUT its logic (doesn't know about losing or winning).
 */
public interface IMinesweeper<Integer> {
  void reset(int numberOfMines);
  int[] open(int index);
}
