package ru.usu.cs.fun.front;

/**
 * Простая обертка над классом Reader. В дальнейшем сюда может быть добавлен
 * подсчет позиции (номера строки и колонки) в входном файле. Семантика
 * current/next.
 * 
 * @author pe
 */
public class CharReader {
	private final String text;
	private int currentIndex = -1;
	private int current;

	/**
	 * После вызова конструктора CharReader уже спозиционирован на первый символ
	 * из reader.
	 */
	public CharReader(String text) {
		super();
		this.text = text;
		next();
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
		currentIndex++;
		current = currentIndex >= text.length() ? -1 : text.charAt(currentIndex);
		return current;
	}

	@Override
	public String toString() {
		if (current == -1)
			return "[end_of_text]";
		return "[" + (char) current + "]";
	}

	public int pos() {
		return currentIndex;
	}

	public String getText(int beginPos, int endPos) {
		return text.substring(beginPos, endPos);
	}
}
