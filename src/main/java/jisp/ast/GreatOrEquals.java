package jisp.ast;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 17:26
 */
public class GreatOrEquals implements Compare {
    @Override
    public boolean cmp(Object x, Object y) {
        if (x instanceof  Number && y instanceof  Number) {
            return ((Number)x).doubleValue() >= ((Number) y).doubleValue();
        }

        if(!(x instanceof Comparable) || !(x.getClass().isInstance(y))) {
            return false;
        }
        return ((Comparable) x).compareTo(y) >= 0;
    }
}
