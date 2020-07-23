package jisp.ast;

import jisp.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * S 表达式对象
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:49
 */
public class Expression implements Element {
    private final List<Object> elements = new ArrayList<>();

    public Expression(List<Object> elements) {
        this.elements.addAll(elements);
    }

    @Override
    public double eval(Env env) throws ParserException {
        String funcName = elements.get(0).toString();
        Lambda func = (Lambda) env.get(funcName);
        List<Double> params = new ArrayList<>();
        for(int i = 1; i< elements.size(); i++){
            double arg = ((Element)elements.get(i)).eval(env);
            params.add(arg);
        }
        return func.apply(params.toArray());
    }
}
