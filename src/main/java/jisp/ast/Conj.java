package jisp.ast;

import jisp.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/08 15:29
 */
public class Conj implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        if(args.size() != 2 || !isList(args.get(0))) {
            throw new ParserException(String.format("expect (cons x seq) but get (cons %s)", args));
        }

        var  list = new ArrayList<>();
        if(args.get(0) instanceof List) {
            for (var arg : elements(args.get(0))) {
                list.add(env.eval(arg));
            }
        }

        list.add(env.eval(args.get(1)));
        return list;
    }
}
