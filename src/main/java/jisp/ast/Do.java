package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/03 14:53
 */
public class Do implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        var environment = new Env();
        environment.setGlobal(env);

        for (Object arg : args.subList(0, args.size() - 1)) {
            environment.eval(arg);
        }
        return environment.eval(args.get(args.size() - 1));
    }
}
