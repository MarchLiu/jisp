package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:50
 */
public class Add implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        double result = 0;
        for (Object item: args) {
            result += ((Number) env.eval(item)).doubleValue();
        }
        return result;
    }
}
