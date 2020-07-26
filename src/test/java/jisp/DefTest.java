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
 * @since 2020/07/26 17:29
 */
public class DefTest {
    private final Env env = new Env();

    public DefTest() {
        env.put("def", new Def());
        env.put("+", new Add());
        env.put("-", new Sub());
        env.put("*", new Product());
        env.put("/", new Divide());
    }

    private final Parser parser = new Parser();

    @Test
    public void testBasic() throws EOFException, ParserException {
        parse("(def pi 3.14)");
        Object result = parse("(* pi 2)");
        Assert.assertEquals(6.28d, result);
    }

    public Object parse(String source) throws EOFException, ParserException {
        Object ast =  parser.parse(source);
        if (ast instanceof Element){
            return ((Element) ast).eval(env);
        } else {
            return ast;
        }
    }
}
