package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/26 17:04
 */
public class Def implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        var name = ((Name)args.get(0)).getName();
        var value = extractValue(env, args.get(1));
        env.put(name, value);
        return value;
    }

}
