package com.mandelag.topoms;

import com.mandelag.topoms.graph.IConnectivityIndex;
import com.mandelag.topoms.graph.impl.ConnectivityIndex;
import org.junit.Test;
import static org.junit.Assert.*;

public class IConnectivityIndexTest {
  @Test
  public void hashCodeTest() {
    IConnectivityIndex c1 = new ConnectivityIndex(new int[][]{
        {1, 3, 4}, // 0
        {0, 2, 3, 4, 5},
        {1, 4, 5},
        {0, 1, 4, 6, 7},
        {0, 1, 2, 3, 5, 6, 7, 8},
        {1, 2, 4, 7, 8},
        {3, 4, 7},
        {3, 4, 5, 6, 8},
        {4, 5, 7}     // 8
    });

    IConnectivityIndex c2 = new ConnectivityIndex(new int[][]{
        {1, 4, 3}, // different ordering
        {0, 5, 2, 4, 3},
        {1, 4, 5},
        {0, 1, 4, 6, 7},
        {0, 2, 1, 3, 5, 6, 7, 8},
        {1, 2, 4, 7, 8, 8},  // contain duplicate
        {4, 3, 7},
        {3, 4, 5, 6, 8},
        {4, 5, 7}
    });

//    System.out.println(c1.hashCode());
    assertEquals(c1.hashCode(), c2.hashCode());
    assertEquals(c1, c2);
  }

  @Test
  public void hashCodeDifferentTest() {
    IConnectivityIndex c1 = new ConnectivityIndex(new int[][]{
        {1, 3, 4}, // 0
        {0, 2, 3, 4, 5},
        {1, 4, 5},
        {0, 1, 4, 6, 7},
        {0, 1, 2, 3, 5, 6, 7, 8},
        {1, 2, 4, 7, 8},
        {3, 4, 7},
        {3, 4, 5, 6, 8},
        {4, 5, 7}     // 8
    });

    IConnectivityIndex c2 = new ConnectivityIndex(new int[][]{
        {0, 2, 3, 4, 5},
        {1, 3, 4}, //different index
        {1, 4, 5},
        {0, 1, 4, 6, 7},
        {0, 1, 2, 3, 5, 6, 7, 8},
        {1, 2, 4, 7, 8},
        {3, 4, 7},
        {3, 4, 5, 6, 8},
        {4, 5, 7}     // 8
    });

    assertNotEquals(c1.hashCode(), c2.hashCode());
    assertNotEquals(c1, c2);
  }
}
