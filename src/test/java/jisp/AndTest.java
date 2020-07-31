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
 * @since 2020/07/31 17:17
 */
public class AndTest {
    private final Env env;
    private final Parser parser = new Parser();

    public AndTest() {
        env = new Env();
        env.put("def", new Def());
        env.put("if", new If());

        env.put("and", new And());
        env.put("or", new Or());
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
    public void testXAndY() throws EOFException, ParserException {
        Assert.assertEquals(true, env.eval(parser.parse("(and (> 1 0) (> 2 1))")));
        Assert.assertEquals(false, env.eval(parser.parse("(and (> 1 0) (< 2 1))")));
        Assert.assertEquals(false, env.eval(parser.parse("(and (> 0 0) (> 2 1))")));

    }
}
