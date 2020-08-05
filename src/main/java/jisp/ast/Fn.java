package jisp.ast;

import jisp.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 19:39
 */
public class Fn implements Lambda {
    @Override
    public Object apply(Env env, List<Object> args) throws ParserException {
        if (!(args.get(0) instanceof Expression)) {
            throw new ParserException(String.format("args list must be a list but [%s]", args.get(0)));
        }
        var parameters = ((Expression) args.get(0)).getElements();
        List<Name> argNames = new ArrayList<>();
        for (var p : parameters) {
            if (!(p instanceof Name)) {
                throw new ParserException(String.format("args list must all names but given [%s]", p));
            }
            argNames.add((Name) p);
        }
        var body = args.subList(1, args.size());

        var our = scan(env, argNames, body);
        return new Func(argNames, body, our);
    }

    public Env scan(Env env, List<Name> args, List<Object> body) throws ParserException {
        var local = new Env();
        var result = new Env();
        local.setGlobal(env);
        result.setGlobal(local);

        for (Name name : args) {
            local.put(name.getName(), name);
        }

        for (Object element : body) {
            if (element instanceof Name) {
                var name = ((Name) element).getName();
                if (result.exists(name)) {
                    if (!local.existsIn(name)) {
                        result.put(name, result.get(name));
                    }
                } else {
                    throw new ParserException(String.format("function define failed, %s not found", name));
                }
            }
            if (element instanceof Expression) {
                scanExpr(local, result, (Expression) element);
            }
        }

        result.setGlobal(null);
        return result;
    }

    public void scanExpr(Env our, Env env, Expression expr) throws ParserException {
        var head = expr.getElements().get(0);
        if (head instanceof Name) {
            var name = ((Name) head).getName();
            Object action;
            try {
                action = env.get(name);
            } catch (ParserException e) {
                throw new ParserException(String.format("function define failed, %s not found", name));
            }
            if(action instanceof Let) {
                var vars = (Expression)expr.getElements().get(1);
                for (var idx = 0; idx<vars.getElements().size(); idx+=2){
                    var varName = (Name) vars.getElements().get(idx);
                    our.put(varName.getName(), varName);
                }
            }
            if(action instanceof Def) {
                var varName = (Name) expr.getElements().get(1);
                our.put(varName.getName(), varName);
            }
        }
        for (var e: expr.getElements()) {
            if (e instanceof Name) {
                var name = ((Name) e).getName();
                if (env.exists(name)) {
                    if (!our.existsIn(name)) {
                        env.put(name, env.get(name));
                    }
                } else {
                    throw new ParserException(String.format("function define failed, %s not found", name));
                }
            }

            if(e instanceof Expression) {
                scanExpr(our, env, (Expression)e);
            }
        }
    }
}
