package jisp.parsers;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;

import java.io.EOFException;
import java.util.function.Predicate;

import static jaskell.parsec.common.Atom.is;
import static jaskell.parsec.common.Combinator.*;
import static jaskell.parsec.common.Txt.ch;
import static jaskell.parsec.common.Txt.joining;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/24 16:34
 */
public class StringParser implements Parsec<Object, Character> {
    private final Predicate<Character> predicate = c -> !(c == '"' || c == '\\');
    private final Parsec<Character, Character> character = attempt(is(predicate));
    private final Parsec<Character, Character> escapeCharacter = ch('\\').then(s -> {
        Character c = s.next();
        switch (c) {
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case '"':
                return '"';
            default:
                throw s.trap(String.format("invalid char  \\%c", c));
        }
    });
    private final Parsec<String, Character> parser = between(ch('"'), ch('"'),
            many(choice(character, escapeCharacter))).bind(joining());

    @Override
    public Object parse(State<Character> s) throws EOFException, ParsecException {
        return parser.parse(s);
    }
}
