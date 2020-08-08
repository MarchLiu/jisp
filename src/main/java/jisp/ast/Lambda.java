package jisp.ast;

import jisp.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:49
 */
public interface Lambda {
    default List<Object> prepare(Env env, List<Object> args) throws ParserException {
        List<Object> result = new ArrayList<>();
        for(Object param: args) {
            if (param instanceof Element) {
                result.add(((Element) param).eval(env));
            } else {
                result.add(param);
            }
        }
        return result;
    }

    default boolean isList(Object element) {
        return (element instanceof List) ||
                ((element instanceof Quote) && ((Quote) element).getValue() instanceof Expression);
    }

    default List<Object> elements(Object value) throws ParserException {
        if(value instanceof List) {
            return ((List)value);
        }
        if(value instanceof Quote && (((Quote) value).getValue()) instanceof Expression) {
            return ((Expression)((Quote) value).getValue()).getElements();
        }
        throw new ParserException(String.format("%s is't a list", value));
    }

    Object apply(Env env, List<Object> args) throws ParserException;
}
