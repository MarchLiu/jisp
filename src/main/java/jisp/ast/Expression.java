package jisp.ast;

import jisp.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * S 表达式对象
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:49
 */
public class Expression implements Element {
    private final List<Object> elements = new ArrayList<>();

    public List<Object> getElements() {
        return elements;
    }

    public Expression(List<Object> elements) {
        this.elements.addAll(elements);
    }

    @Override
    public Object eval(Env env) throws ParserException {
        Lambda func = (Lambda)((Element)elements.get(0)).eval(env);
        return func.apply(env, elements.subList(1, elements.size()));
    }
}
