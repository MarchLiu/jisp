package jisp.ast;

import java.util.Objects;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 17:46
 */
public class NotEq implements Compare {
    @Override
    public boolean cmp(Object x, Object y) {
        return !Objects.equals(x, y);
    }
}
