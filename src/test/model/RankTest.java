package model;

import org.junit.jupiter.api.Test;

import static model.Rank.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankTest {
    private final Rank excellent = EXCELLENT;
    private final Rank good = GOOD;
    private final Rank adequate = ADEQUATE;
    private final Rank insufficient = INSUFFICIENT;
    private final Rank unacceptable = UNACCEPTABLE;

    @Test
    public void testRankToString() {
        assertEquals("excellent", excellent.toString());
        assertEquals("good", good.toString());
        assertEquals("adequate", adequate.toString());
        assertEquals("insufficient", insufficient.toString());
        assertEquals("unacceptable", unacceptable.toString());
    }
}
