package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:53
 */
public class Product implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        double result = 1;
        for(Object item : prepare(env, args)){
            result *= ((Number)env.eval(item)).doubleValue();
        }
        return result;
    }
}
