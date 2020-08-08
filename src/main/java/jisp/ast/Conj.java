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
        if(args.size() != 2 || !(args.get(0) instanceof List)) {
            throw new ParserException(String.format("expect (conj seq x) but get (conj %s)", args));
        }

        var  list = new ArrayList<>();
        for(var arg: (List<Object>)args.get(0)){
            list.add(env.eval(arg));
        }
        list.add(env.eval(args.get(1)));
        return list;
    }
}
