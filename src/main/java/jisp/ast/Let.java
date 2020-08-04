package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/03 19:04
 */
public class Let implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        var declares = args.get(0);
        if (!(declares instanceof Expression)) {
            throw new ParserException(String.format("first element of let must be declare but now is %s", declares));
        }

        var environment = new Env();
        environment.setGlobal(env);
        var pairs = ((Expression) declares).getElements();
        if (pairs.size() % 2 != 0) {
            throw new ParserException(String.format("invalid declare (%s})", pairs));
        }

        for (var idx = 0; idx < pairs.size(); idx += 2) {
            var name = pairs.get(idx);
            if (!(name instanceof Name)) {
                throw new ParserException(
                        String.format("invalid declare, first element must be a name but [%s]", name));
            }
            var result = environment.eval(pairs.get(idx + 1));
            env.put(((Name) name).getName(), result);

        }

        for (var element : args.subList(1, args.size())) {
            env.eval(element);
        }

        return env.eval(args.get(args.size() - 1));
    }
}
