package jisp.ast;

import jisp.ParserException;

import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/31 16:29
 */
public class IsFalse implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        if (args.size() != 1) {
            throw new ParserException(String.format("false? function only accept single parameter but %d passed",
                    args.size()));
        }
        return !IsTrue.isTrue(env.eval(args.get(0)));
    }

}
