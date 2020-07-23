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
    private final Map<String, Object> local = new HashMap<>();
    public Object put(String name, Object value) {
        return local.put(name, value);
    }

    public Object get(String name) throws ParserException {
        if (local.containsKey(name)){
            return local.get(name);
        } else {
            throw new ParserException(String.format("%s not found", name));
        }
    }
}
