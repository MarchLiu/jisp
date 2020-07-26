package jisp.ast;

import jisp.ParserException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/26 16:59
 */
public class Name implements Element {
    private final String name;

    public Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object eval(Env env) throws ParserException {
        return env.get(name);
    }
}
