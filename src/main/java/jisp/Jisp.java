package jisp;

import jisp.ast.*;
import jisp.parsers.Parser;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 18:06
 */
public class Jisp extends Env {
    private final Parser parser = new Parser();

    public Jisp() {
        this.put("def", new Def());
        this.put("if", new If());

        this.put("fn", new Fn());
        this.put("recur", new Recur());

        this.put("and", new And());
        this.put("or", new Or());
        this.put("==", new Eq());
        this.put("!=", new NotEq());
        this.put(">", new Great());
        this.put("<", new Less());
        this.put(">=", new GreatOrEquals());
        this.put("<=", new LessOrEquals());

        this.put("+", new Add());
        this.put("-", new Sub());
        this.put("*", new Product());
        this.put("/", new Divide());

        this.put("list", new ListExpr());
        this.put("first", new First());
        this.put("last", new Last());
        this.put("rest", new Rest());
    }

    public Parser getParser() {
        return parser;
    }

    public Object read(String source) throws EOFException {
        return parser.parse(source);
    }

    public Object parse(String source) throws EOFException, ParserException {
        Object element = read(source);
        return this.eval(element);
    }
}
