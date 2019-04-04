package minesweeper;

import java.util.List;

/**
 * Provides the interface of Minesweeper game, but WITHOUT its logic (doesn't know about losing or winning).
 */
public interface IMinesweeper<T> {
  void init(int numberOfMines);
  List<T> open(int index);
}
