package jisp.ast;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:49
 */
public interface Lambda {
    double apply(List<Object> args);
}
