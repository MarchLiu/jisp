package jisp.ast;

import jisp.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 20:07
 */
public class Rest implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        if (args.size() != 1) {
            throw new ParserException(String.format("head function only accept one parameter but [%s]", args));
        }
        var param = env.eval(args.get(0));
        if (param instanceof Expression) {
            var elems = ((Expression) param).getElements();
            if (elems.size() > 0) {
                return elems.subList(1, elems.size());
            } else {
                return new Quote(new Expression(new ArrayList<>()));
            }
        }
        if (param instanceof List) {
            var elems = (List) param;
            if (elems.size() > 0) {
                return elems.subList(1, elems.size());
            } else {
                return new ArrayList<>();
            }
        }
        throw new ParserException(String.format("(head %s) unsupported", param));
    }
}
