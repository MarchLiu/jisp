package jisp.ast;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:55
 */
public class Divide implements Lambda {
    @Override
    public double apply(Object... args) {
        double result = (Double) args[0];
        for(int i = 1; i<args.length; i++){
            result /= (Double)args[i];
        }
        return result;
    }
}
