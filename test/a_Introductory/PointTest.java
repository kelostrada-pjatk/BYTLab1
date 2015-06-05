package a_Introductory;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class PointTest {

    Point p1, p2, p3;

    @Before
    public void setUp() throws Exception {
        p1 = new Point(7, 9);
        p2 = new Point(-3, -30);
        p3 = new Point(-10, 3);
    }

    @Test
    public void testAdd() {
        Point res1 = p1.add(p2);
        Point res2 = p1.add(p3);

        assertEquals(4, (long) res1.x);
        assertEquals(-21, (long) res1.y);
        assertEquals(-3, (long) res2.x);
        assertEquals(12, (long) res2.y);
    }

    @Test
    public void testSub() {
        Point res1 = p1.sub(p2);
        Point res2 = p1.sub(p3);

        assertEquals(10, (long) res1.x);
        assertEquals(39, (long) res1.y);
        assertEquals(17, (long) res2.x);
        assertEquals(6, (long) res2.y);
    }

}
