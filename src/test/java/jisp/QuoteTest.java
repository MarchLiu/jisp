package jisp;

import jisp.ast.Expression;
import jisp.ast.NumberElement;
import jisp.ast.Quote;
import org.junit.Assert;
import org.junit.Test;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 19:40
 */
public class QuoteTest {
    private final Jisp jisp = new Jisp();
    @Test
    public void testQuote() throws EOFException, ParserException {
        Assert.assertTrue(jisp.read("'(+ 2 3)") instanceof Quote);
        Assert.assertTrue(jisp.parse("'(+ 2 3)") instanceof Expression);
        Assert.assertEquals(5.0, jisp.eval(jisp.parse("'(+ 2 3)")));

        Assert.assertTrue(jisp.read("'3.14") instanceof Quote);
        Assert.assertTrue(jisp.parse("'3.14") instanceof NumberElement);
        Assert.assertEquals(3.14, jisp.eval(jisp.parse("'3.14")));


    }
}
