package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 15:08
 */
public class Cond implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        List<Object> dispatch;
        var isEven = args.size() % 2 == 0;
        if (isEven) {
            dispatch = args;
        } else {
            dispatch = args.subList(0, args.size() - 1);
        }
        for (var idx = 0; idx < dispatch.size(); idx += 2) {
            var cond = dispatch.get(idx);
            if (IsTrue.isTrue(env.eval(cond))) {
                return env.eval(dispatch.get(idx + 1));
            }
        }
        if (isEven) {
            return null;
        } else {
            return env.eval(args.get(args.size() - 1));
        }
    }
}
