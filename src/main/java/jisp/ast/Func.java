package jisp.ast;

import jisp.ParserException;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 21:25
 */
public class Func implements Recurable {
    private final List<Name> parameters;
    private final List<Object> body;
    private final Env our;

    public Func(List<Name> parameters, List<Object> body, Env our) {
        this.parameters = parameters;
        this.body = body;
        this.our = our;
    }

    public List<Name> getParameters() {
        return parameters;
    }

    public List<Object> getBody() {
        return body;
    }

    public Env getOur() {
        return our;
    }

    @Override
    public Object invoke(Env env, List<Object> args) throws ParserException {
        if(parameters.size() != args.size()){
            throw new ParserException(String.format("function invoke require %d parameters but given %d",
                    parameters.size(), args.size()));
        }
        var our = this.our.copy();
        var environment = new Env();
        environment.setGlobal(our);
        our.setGlobal(env);
        for(var idx= 0; idx < parameters.size(); idx++){
            environment.put(parameters.get(idx).getName(), env.eval(args.get(idx)));
        }
        for(var expr: body.subList(0, body.size()-1)) {
            environment.eval(expr);
        }
        return environment.eval(body.get(body.size()-1));
    }
}
