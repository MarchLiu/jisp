package jisp.ast;

import jisp.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 21:18
 */
public class Recur implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        List<Object> values = new ArrayList<>();
        for (Object element: args) {
            values.add(env.eval(element));
        }
        return new RecurExpression(values);
    }
}
