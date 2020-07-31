package jisp.ast;

import jisp.ParserException;

import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/31 16:29
 */
public class IsTrue implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        if (args.size() != 1) {
            throw new ParserException(String.format("true? function only accept single parameter but %d passed",
                    args.size()));
        }
        return isTrue(env.eval(args.get(0)));
    }

    // isTrue not eval anything, just check it
    public static boolean isTrue(Object item) throws ParserException {
        if (item == null) {
            return false;
        }
        if (item instanceof Boolean) {
            return (Boolean) item;
        }
        if (item instanceof Number) {
            return !(((Number) item).doubleValue() == 0);
        }

        if(item instanceof String) {
            return !item.toString().isEmpty();
        }
        if (item instanceof Collection<?>) {
            return ((Collection<?>)item).isEmpty();
        }
        return true;
    }
}
