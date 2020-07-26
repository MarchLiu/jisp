package jisp;

import jaskell.parsec.common.State;
import jaskell.parsec.common.TxtState;
import jisp.parsers.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/24 16:51
 */
public class StringTest {
    @Test
    public void testSimple() throws EOFException {
        var parser = new Parser();
        var state = new TxtState("\"this is a string\"");
        Assert.assertEquals(parser.parse(state), "this is a string");
    }

    @Test
    public void testEscape() throws EOFException {
        var parser = new Parser();
        var state = new TxtState("\"this is a string what is \\\"I'm a string\\\"\"");
        Assert.assertEquals(parser.parse(state), "this is a string what is \"I'm a string\"");
    }
}
