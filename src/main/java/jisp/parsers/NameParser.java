package jisp.parsers;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;
import jisp.ast.Name;

import java.io.EOFException;
import java.util.function.Predicate;

import static jaskell.parsec.common.Atom.is;
import static jaskell.parsec.common.Atom.pack;
import static jaskell.parsec.common.Combinator.many1;
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
    private final Parsec<Name, Character> parser = many1(is(predicate)).bind(joining())
            .bind(name -> pack(new Name(name)));

    @Override
    public Name parse(State<Character> s) throws EOFException, ParsecException {
        return parser.parse(s);
    }
}
