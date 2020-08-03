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
        for(Object arg: args.subList(0, args.size()-1)) {
            env.eval(arg);
        }
        return env.eval(args.get(args.size()-1));
    }
}
