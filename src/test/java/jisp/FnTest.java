package jisp;

import jisp.ast.*;
import jisp.parsers.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 16:18
 */
public class FnTest {
    private final Jisp jisp = new Jisp();

    @Test
    public void testDefine() throws EOFException, ParserException {
        jisp.parse("(def add (fn (x y) (+ x y)))");

        Assert.assertEquals(9.42, jisp.parse("(add 3.14 6.28)"));
    }

    @Test
    public void testRecur() throws EOFException, ParserException {
        jisp.parse("(def increment (fn (x) (if (< x 10) (recur (* 2 x)) x)))");

        Assert.assertEquals(16.0, jisp.parse("(increment 2)"));
    }

    @Test
    public void testStatic() throws EOFException, ParserException {
        jisp.parse("(def add (fn (x y) (+ x y)))");

        jisp.parse("(def step6 (fn (x) (if (< x 10) (recur (add 6 x)) x)))");

        Assert.assertEquals(14.0, jisp.parse("(step6 2)"));
    }
}
