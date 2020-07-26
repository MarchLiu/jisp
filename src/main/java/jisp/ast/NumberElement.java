package jisp.ast;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 16:02
 */
public class NumberElement implements Element {
    private final Double value;

    public NumberElement(Double value) {
        this.value = value;
    }

    @Override
    public Object eval(Env env) {
        return value;
    }
}
