package jisp.parsers;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;
import jisp.ast.Expression;

import java.io.EOFException;

import static jaskell.parsec.common.Atom.pack;
import static jaskell.parsec.common.Combinator.between;
import static jaskell.parsec.common.Combinator.sepBy1;
import static jaskell.parsec.common.Txt.ch;
import static jaskell.parsec.common.Txt.skipWhiteSpaces;
/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:49
 */
public class ExprParser implements Parsec<Character, Object> {
    private final Parsec<Character, ?> skip = skipWhiteSpaces();
    private final Parsec<Character, Object> elementParser = new Parser();
    private final Parsec<Character, Expression> parser =
            between(ch('('), ch(')'), sepBy1(elementParser, skip))
                    .bind(values -> pack(new Expression(values)));
    @Override
    public Object parse(State<Character> s) throws EOFException, ParsecException {
        return parser.parse(s);
    }
}
