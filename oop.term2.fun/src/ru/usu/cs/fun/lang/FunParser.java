package ru.usu.cs.fun.lang;

import ru.usu.cs.fun.back.Abstraction;
import ru.usu.cs.fun.back.Application;
import ru.usu.cs.fun.back.Term;
import ru.usu.cs.fun.back.Variable;
import ru.usu.cs.fun.front.AbstractLL1Parser;
import ru.usu.cs.fun.front.CharReader;
import ru.usu.cs.fun.front.Lexeme;
import ru.usu.cs.fun.front.Lexer;
import ru.usu.cs.fun.front.ParseAction;

// Statement - initial nonterm
// Statement has type Term
// Term has type Terms
// Tail has type Terms

// Statement ::= 'let' name ':=' Term ';'
// Statement ::= Term ';'
// Term ::= '(' Term ')' Tail
// Term ::= name Tail
// Term ::= 'fun' '(' name ')' Term Tail
// Tail ::=
// Tail ::= Term
public class FunParser extends AbstractLL1Parser {
	private final FunScope scope;

	private static Lexer createLexer(CharReader reader) {
		return new FunLexer(reader);
	}

	// последовательно выполняет все предложения из programBlock
	// возвращает результат выполнения последнего из них
	public Term eval(String programBlock) {
		Lexer lexer = createLexer(new CharReader(programBlock));
		Object res = executeNext(lexer);
		Object newRes;
		while ((newRes = executeNext(lexer)) != null)
			res = newRes;
		return (Term) res;
	}

	// вычитывает из лексера и выполняет одно очередное предложение
	public Term evalNext(Lexer lexer) {
		return (Term) executeNext(lexer);
	}

	private static class Terms {

		public final Term head;
		public final Terms tail;

		public Terms(Term head, Terms tail) {
			super();
			this.head = head;
			this.tail = tail;
		}

		public Term build() {
			Term result = head;
			Terms tmpTail = tail;
			while (tmpTail != null) {
				result = new Application(result, tmpTail.head);
				tmpTail = tmpTail.tail;
			}
			return result;
		}
	}

	public FunParser() {
		this(new FunScope());
	}

	public FunParser(FunScope aScope) {
		super();
		this.scope = aScope;

		// Statement ::= 'let' name '=' Term ';' // scope.add(name, Term)
		add("Statement", new String[] { "let", "name", ":=", "Term", ";" }, new ParseAction() {
			public Term execute(Object[] items) {
				String name = ((Lexeme) items[1]).getText();
				Term term = ((Terms) items[3]).build();
				scope.add(name, term.eval(scope));
				return term;
			}
		});

		// Statement ::= Term ';' // return Term.eval(scope);
		add("Statement", new String[] { "Term", ";" }, new ParseAction() {
			public Term execute(Object[] items) {
				Term term = ((Terms) items[0]).build();
				// EvaluationLogger logger = new EvaluationLogger(term);
				Term res = term.eval(scope);
				System.out.println(res.toString());
				return res;
			}
		});

		// Term ::= '(' Term ')' Tail // return Term.apply(Tail);
		add("Term", new String[] { "(", "Term", ")", "Tail" }, new ParseAction() {
			public Terms execute(Object[] items) {
				Term termInBrackets = ((Terms) items[1]).build();
				return new Terms(termInBrackets, (Terms) items[3]);
			}
		});

		// Term ::= name Tail // return var(name).apply(Tail);
		add("Term", new String[] { "name", "Tail" }, new ParseAction() {
			public Terms execute(Object[] items) {
				Variable var = new Variable(((Lexeme) items[0]).getText());
				return new Terms(var, (Terms) items[1]);
			}
		});

		// Term ::= 'fun' '(' FunTail // return funTail;
		add("Term", new String[] { "fun", "(", "FunTail" }, new ParseAction() {
			public Terms execute(Object[] items) {
				return (Terms) items[2];
			}
		});

		// FunTail ::= ')' Term // return body;
		add("FunTail", new String[] { ")", "Term" }, new ParseAction() {
			public Terms execute(Object[] items) {
				Term body = ((Terms) items[1]).build();
				return new Terms(body, null);
			}
		});

		// FunTail ::= name ')' Term // return fun(name) body;
		add("FunTail", new String[] { "name", ")", "Term" }, new ParseAction() {
			public Terms execute(Object[] items) {
				Term body = ((Terms) items[2]).build();
				String name = ((Lexeme) items[0]).getText();
				Abstraction abstraction = new Abstraction(name, name.startsWith("~"), body);
				return new Terms(abstraction, null);
			}
		});

		// Tail ::= // return null;
		add("Tail", new String[] { ";", ")", }, new String[] {}, new ParseAction() {
			public Terms execute(Object[] items) {
				return null;
			}
		});

		// Tail ::= // return Term
		add("Tail", new String[] { "Term" }, new ParseAction() {
			public Terms execute(Object[] items) {
				return (Terms) items[0];
			}
		});

		setInitial("Statement");
	}
}
