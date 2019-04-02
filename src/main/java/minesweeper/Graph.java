package minesweeper;

import java.util.List;

public interface Graph<T> {
  List<Node<T>> getNodes();
}

interface Node<T> {
  T getData();
}
