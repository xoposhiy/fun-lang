package ru.usu.cs.fun.engine;

import java.io.IOException;
import java.io.Reader;

/**
 * Простая обертка над классом Reader. В дальнейшем сюда может быть добавлен
 * подсчет позиции (номера строки и колонки) в входном файле. Семантика
 * current/next.
 * 
 * @author pe
 */
public class CharReader {
	private final Reader reader;
	private int current;

	/**
	 * После вызова конструктора CharReader уже спозиционирован на первый символ
	 * из reader.
	 */
	public CharReader(Reader reader) {
		super();
		this.reader = reader;
		current = next();
	}

	/**
	 * @return последний прочитанный символ или -1, если символов больше нет.
	 */
	public int current() {
		return current;
	}

	/**
	 * @return следующий символ или -1, если символов больше нет
	 */
	public int next() {
		try {
			current = reader.read();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return current;
	}

	@Override
	public String toString() {
		if (current == -1)
			return "[empty]";
		return "[" + (char) current + "]";
	}
}
