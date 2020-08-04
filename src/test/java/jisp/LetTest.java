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
 * @since 2020/08/03 23:51
 */
public class LetTest {
    private final Env env;
    private final Parser parser = new Parser();

    public LetTest() {
        env = new Env();
        env.put("def", new Def());
        env.put("do", new Do());
        env.put("let", new Let());

        env.put("+", new Add());
        env.put("-", new Sub());
        env.put("*", new Product());
        env.put("/", new Divide());
    }

    @Test
    public void testLet() throws EOFException, ParserException {
        Assert.assertEquals(6.28, env.eval(parser.parse("(let (pi 3.14 step 2) (* pi step))")));
    }
}
