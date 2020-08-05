package jisp.ast;

import jisp.ParserException;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:48
 */
public class Env {
    private Env global = null;

    public Env getGlobal() {
        return global;
    }

    public void setGlobal(Env env) {
        global = env;
    }

    private final Map<String, Object> local = new HashMap<>();

    public Object put(String name, Object value) {
        return local.put(name, value);
    }

    public Object findOut(String name) throws ParserException {
        if (global == null) {
            throw new ParserException(String.format("%s not found", name));
        }
        return global.get(name);
    }

    public boolean existsOut(String name) {
        try {
            if (global == null) {
                return false;
            }
            global.get(name);
            return true;
        } catch (ParserException e) {
            return false;
        }
    }

    public Object findIn(String name) throws ParserException {
        if (local.containsKey(name)) {
            return local.get(name);
        } else {
            throw new ParserException(String.format("%s not found in local", name));
        }
    }

    public boolean existsIn(String name) {
        return local.containsKey(name);
    }

    public Object get(String name) throws ParserException {
        try {
            return findIn(name);
        } catch (ParserException notfound) {
            return findOut(name);
        }
    }

    public boolean exists(String name) {
        return existsIn(name) || existsOut(name);
    }

    public Object eval(Object data) throws ParserException {
        if (data instanceof Element) {
            return ((Element) data).eval(this);
        } else {
            return data;
        }
    }

    public Env copy() {
        Env result = new Env();
        result.local.putAll(this.local);
        return result;
    }
}
