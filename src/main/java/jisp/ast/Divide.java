package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:55
 */
public class Divide implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        double result = (Double) extractValue(env, args.get(0));
        for(Object item: prepare(env, args.subList(1, args.size()))){
            result /= (Double)item;
        }
        return result;
    }
}
