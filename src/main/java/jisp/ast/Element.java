package jisp.ast;

import jisp.ParserException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:48
 */
public interface Element {
    Object eval(Env env) throws ParserException;
}
