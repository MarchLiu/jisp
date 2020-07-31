package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 17:55
 */
public class If implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        if(args.size() < 2 || args.size() > 3) {
            throw new ParserException(
                    String.format(
                            "invalid statement (if %s) [size is %d], parameters count of if must always 2 or 3",
                            args, args.size()));
        }
        var cond = env.eval(args.get(0));
        if((Boolean) cond){
            return env.eval(args.get(1));
        } else {
            if (args.size()==3){
                return env.eval(args.get(2));
            } else {
                return null;
            }
        }
    }
}
