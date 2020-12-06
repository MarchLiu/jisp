package jisp.parsers;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;

import java.io.EOFException;

import static jaskell.parsec.common.Combinator.attempt;
import static jaskell.parsec.common.Combinator.choice;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:48
 */
public class Parser implements Parsec<Character, Object> {
    @Override
    public Object parse(State<Character> s) throws EOFException, ParsecException {
        var parser = choice(attempt(new QuoteParser()),
                attempt(new ExprParser()),
                attempt(new NumberParser()),
                attempt(new StringParser()),
                new NameParser());
        return parser.parse(s);
    }
}
