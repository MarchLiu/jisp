package jisp.parsers;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;
import jisp.ast.NumberElement;

import java.io.EOFException;

import static jaskell.parsec.common.Txt.*;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/19 23:13
 */
public class NumberParser implements Parsec<Object, Character> {
    private final Parsec<String, Character> parser = decimal();
    @Override
    public NumberElement parse(State<Character> s) throws EOFException, ParsecException {
        String data = parser.parse(s);
        return new NumberElement(Double.parseDouble(data));
    }
}
