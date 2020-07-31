package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/31 16:28
 */
public class And implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        for (Object arg: args) {
            if(!IsTrue.isTrue(env.eval(arg))) {
                return false;
            }
        }
        return true;
    }
}
