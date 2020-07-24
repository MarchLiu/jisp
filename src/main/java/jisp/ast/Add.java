package jisp.ast;

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
    public double apply(List<Object> args) {
        double result = 0;
        for (Object item: args) {
            result += (Double) item;
        }
        return result;
    }
}