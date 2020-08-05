package jisp.ast;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 21:18
 */
public class RecurExpression {
    private final List<Object> parameters;

    public RecurExpression(List<Object> parameters) {
        this.parameters = parameters;
    }

    public List<Object> getParameters() {
        return parameters;
    }
}
