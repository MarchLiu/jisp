package jisp.ast;

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
    public double apply(List<Object> args) {
        double result = (Double) args.get(0);
        for (Object item: args.subList(1, args.size())) {
            result -= (Double) item;
        }
        return result;
    }
}
