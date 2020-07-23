package jisp.parsers;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;
import jisp.ast.Element;

import java.io.EOFException;
import java.util.function.Predicate;

import static jaskell.parsec.common.Combinator.*;
import static jaskell.parsec.common.Atom.*;
import static jaskell.parsec.common.Txt.joining;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:48
 */
public class NameParser implements Parsec<Object, Character> {
    private final Predicate<Character> predicate = c -> !(c == ')' || Character.isWhitespace(c));
    private final Parsec<String, Character> parser = many1(is(predicate)).bind(joining());

    @Override
    public String parse(State<Character> s) throws EOFException, ParsecException {
        return parser.parse(s);
    }
}
