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
        if (args.size() != 2) {
            throw new ParserException(String.format("expect (conj seq x) but get %s", args));
        }
        var seq = env.eval(args.get(0));
        if (!isList(seq)) {
            throw new ParserException(String.format("expect (conj seq x) but get %s", args));
        }

        var list = new ArrayList<>(elements(seq));

        list.add(env.eval(args.get(1)));
        return Quote.fromList(list);
    }
}
