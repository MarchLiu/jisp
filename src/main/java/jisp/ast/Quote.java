package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 19:35
 */
public class Quote implements Element {
    private final Object value;

    public Quote(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public Object eval(Env env) throws ParserException {
        return value;
    }
}
