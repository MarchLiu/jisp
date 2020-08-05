package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 21:20
 */
public interface Recurable extends Lambda {
    Object invoke(Env env, List<Object> args) throws ParserException;

    default Object apply(Env env, List<Object> args) throws ParserException {
        Object result = invoke(env, args);
        while (result instanceof RecurExpression) {
            var expr = (RecurExpression) result;
            result = invoke(env, expr.getParameters());
        }
        return result;
    }
}
