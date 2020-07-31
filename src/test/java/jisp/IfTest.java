package jisp;

import jaskell.parsec.common.TxtState;
import jisp.ast.*;
import jisp.parsers.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/30 19:10
 */
public class IfTest {
    private final Env env;
    private final Parser parser = new Parser();

    public IfTest() {
        env = new Env();
        env.put("def", new Def());
        env.put("if", new If());
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
        Assert.assertEquals(1.0d, ((Element)parser.parse("(if (== 5 5) 1 0)")).eval(env));
        Assert.assertEquals(0d, ((Element)parser.parse("(if (== 5.1 5) 1 0)")).eval(env));
    }

    @Test
    public void testGreat() throws EOFException, ParserException {
        Assert.assertEquals(1.0d, ((Element)parser.parse("(if (> 5.1 5) 1 0)")).eval(env));
        Assert.assertEquals(0d, ((Element)parser.parse("(if (> 5.1 5.1) 1 0)")).eval(env));
        Assert.assertEquals(0d, ((Element)parser.parse("(if (> 5 5.1) 1 0)")).eval(env));
    }

    @Test
    public void testLess() throws EOFException, ParserException {
        Assert.assertEquals(1.0d, ((Element)parser.parse("(if (< 3.14 3.15) 1 0)")).eval(env));
        Assert.assertEquals(0d, ((Element)parser.parse("(if (< 3.14 3.14) 1 0)")).eval(env));
        Assert.assertEquals(0d, ((Element)parser.parse("(if (> 5 5.1) 1 0)")).eval(env));
    }
}
