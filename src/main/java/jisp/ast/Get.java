package jisp.ast;

import jisp.ParserException;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 20:43
 */
public class Get implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        if (args.size() < 2 || args.size() > 3) {
            throw new ParserException(String.format("get require 2 or 3 parameters but [%s]", args));
        }

        if (args.size() != 1) {
            throw new ParserException(String.format("head function only accept one parameter but [%s]", args));
        }
        var param = env.eval(args.get(0));
        var key = env.eval(args.get(1));
        if (param instanceof Expression && key instanceof Integer) {
            var elems = ((Expression) param).getElements();
            var idx = (Integer) key;
            if (elems.size() > idx) {
                return elems.get(idx);
            } else {
                return null;
            }
        }
        if (param instanceof List && key instanceof Integer) {
            var elems = (List) param;
            var idx = (Integer) key;
            if (elems.size() > idx) {
                return elems.get(idx);
            } else {
                return null;
            }
        }

        if (param instanceof Map) {
            var map = (Map) param;
            if(map.containsKey(key)) {
                return map.get(key);
            } else {
                return defaultValue(env, args);
            }
        }
        throw new ParserException(String.format("(head %s) unsupported", param));
    }

    public Object defaultValue(Env env, List<Object> args) throws ParserException {
        if (args.size() == 2) {
            return null;
        }
        return env.eval(args.get(2));
    }
}
