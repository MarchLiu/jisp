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
 * @since 2020/07/30 19:10
 */
public class CondTest {
    private final Env env;
    private final Parser parser = new Parser();

    public CondTest() {
        env = new Env();
        env.put("def", new Def());
        env.put("cond", new Cond());
        env.put("==", new Eq());
        env.put("!=", new NotEq());
        env.put(">", new Great());
        env.put("<", new Less());
        env.put(">=", new GreatOrEquals());
        env.put("<=", new LessOrEquals());

        env.put("+", new Add());
        env.put("-", new Sub());
        env.put("*", new Product());
        env.put("/", new Divide());
    }

    @Test
    public void testEquals() throws EOFException, ParserException {
        Assert.assertEquals(1.0d, ((Element)parser.parse("(cond (== 5 5) 1 0)")).eval(env));
        Assert.assertEquals(6.28d,
                env.eval(parser.parse("(cond (== 5.1 5) 1 (!= 3.14 3.14) 2 (* 2 3.14))")));
    }

    @Test
    public void testGreat() throws EOFException, ParserException {
        Assert.assertEquals(25.0d, ((Element)parser.parse("(cond (> 5.1 5) (* 5 5) 0)")).eval(env));
        Assert.assertEquals(25.5d,
                env.eval(parser.parse("(cond (> 5.1 5.1) (* 5.1 5.1) (> 5.1 5) (* 5.1 5) 0)")));
        Assert.assertEquals(0d, ((Element)parser.parse("(cond (> 5 5.1) 1 (== 5 5) 0)")).eval(env));
    }

}
