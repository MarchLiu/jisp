package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:52
 */
public class Sub implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        double result = ((Number)env.eval(args.get(0))).doubleValue();
        for (Object item: args.subList(1, args.size())) {
            result -= ((Number) env.eval(item)).doubleValue();
        }
        return result;
    }
}
