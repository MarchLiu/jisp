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
 * @since 2020/08/03 16:50
 */
public class DoTest {
    private final Env env;
    private final Parser parser = new Parser();

    public DoTest() {
        env = new Env();
        env.put("def", new Def());
        env.put("do", new Do());

        env.put("+", new Add());
        env.put("-", new Sub());
        env.put("*", new Product());
        env.put("/", new Divide());
    }

    @Test
    public void testDo() throws EOFException, ParserException {
        Assert.assertEquals(2.0, env.eval(parser.parse("(do (+ 7 5) (- 3 1))")));
        Assert.assertEquals(2.14, env.eval(parser.parse("(do (def pi 3.14) (+ 7 5) (- pi 1))")));
    }
}
