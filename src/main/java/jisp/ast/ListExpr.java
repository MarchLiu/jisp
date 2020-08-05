package jisp.ast;

import jisp.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 20:18
 */
public class ListExpr implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        List<Object> values = new ArrayList<>();
        for(Object arg: args){
            values.add(env.eval(arg));
        }
        return new Quote(values);
    }
}
