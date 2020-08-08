package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 20:07
 */
public class First implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        if (args.size() != 1) {
            throw new ParserException(String.format("head function only accept one parameter but [%s]", args));
        }

        var param = env.eval(args.get(0));

        if (!isList(param)) {
            throw new ParserException(String.format("(head %s) unsupported", param));
        }

        var elems = elements(param);
        if (elems.size() > 0) {
            return elems.get(0);
        } else {
            return null;
        }
    }
}
