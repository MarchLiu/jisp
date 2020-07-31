package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 16:56
 */
public interface Compare extends Lambda {
    boolean cmp(Object x, Object y);
    default boolean compare(List<Object> args) throws ParserException {
        if(args.size() < 2) {
            throw new ParserException(String.format("args [size %d] too less for compare", args.size()));
        }
        var left = args.get(0);
        for (Object right: args.subList(1, args.size())){
            if(!cmp(left, right)){
                return false;
            }
            left = right;
        }
        return true;
    }

    @Override
    default Object apply(Env env, List<Object> args) throws ParserException {
        return compare(prepare(env, args));
    }
}
