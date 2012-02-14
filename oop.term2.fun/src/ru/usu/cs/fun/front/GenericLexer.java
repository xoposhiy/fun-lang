package ru.usu.cs.fun.front;

import java.util.ArrayList;
import java.util.List;

public class GenericLexer implements Lexer {
	private Lexeme currentLexeme;
	private final CharReader reader;
	private final Recognizer[] recognizers;
	private final OptimizationTable optimizationTable = new OptimizationTable();

	public GenericLexer(CharReader reader, Recognizer... recognizers) {
		this.reader = reader;
		this.recognizers = recognizers;
		readNext();
	}

	@Override
	public Lexeme current() {
		return currentLexeme;
	}

	@Override
	public Lexeme readNext() {
		currentLexeme = null;
		if (reader.current() >= 0) {
			int beginPos = reader.pos();
			Recognizer notDead = getRecognizer((char) reader.current());
			do {
				char ch = (char) reader.current();
				if (notDead.takeChar(ch) == RecognizerState.finished)
					currentLexeme = createLexeme(beginPos, notDead);
			} while (notDead.currentState() != RecognizerState.dead && reader.next() >= 0);
			if (currentLexeme == null)
				throw new RuntimeException("не разобрал " + reader.toString());
		}
		if (currentLexeme != null && currentLexeme instanceof InvisibleLexeme)
			return readNext();
		else
			return currentLexeme;
	}

	private static class InvisibleLexeme extends Lexeme {
		public InvisibleLexeme(String text, String type) {
			super(text, type);
		}

	}

	private Lexeme createLexeme(int beginPos, Recognizer recognizer) {

		String text = reader.getText(beginPos, reader.pos() + 1);
		String lexemeType = recognizer.getLexemeType();
		return recognizer.invisible() ? new InvisibleLexeme(text, lexemeType) : new Lexeme(text, lexemeType);
	}

	private Recognizer getRecognizer(char currentChar) {
		Recognizer res = optimizationTable.get(currentChar);
		if (res == null) {
			res = getRecognizerWithoutCache(currentChar);
			optimizationTable.put(currentChar, res);
		}
		res.reset();
		return res;
	}

	private Recognizer getRecognizerWithoutCache(char ch) {
		List<Recognizer> notDead = new ArrayList<Recognizer>();
		for (Recognizer r : recognizers) {
			r.reset();
			if (r.takeChar(ch) != RecognizerState.dead)
				notDead.add(r);
		}
		if (notDead.size() == 1)
			return notDead.get(0);
		return new MuxRecognizer(notDead);
	}
}
