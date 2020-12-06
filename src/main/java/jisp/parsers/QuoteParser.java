package jisp.parsers;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;
import jisp.ast.Quote;

import java.io.EOFException;

import static jaskell.parsec.common.Atom.pack;
import static jaskell.parsec.common.Txt.ch;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 19:36
 */
public class QuoteParser implements Parsec<Character, Object> {
    @Override
    public Object parse(State<Character> s) throws EOFException, ParsecException {
        var parser = new Parser();
        var psc = ch('\'').then(parser).bind(value -> pack(new Quote(value)));
        return psc.parse(s);
    }
}
