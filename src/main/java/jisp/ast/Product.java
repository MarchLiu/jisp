package jisp.ast;

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
    public double apply(List<Object> args) {
        double result = 1;
        for(Object item : args){
            result *= (Double) item;
        }
        return result;
    }
}