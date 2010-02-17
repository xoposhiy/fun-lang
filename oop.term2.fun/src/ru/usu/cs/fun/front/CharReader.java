package ru.usu.cs.fun.front;

/**
 * ������� ������� ��� ������� Reader. � ���������� ���� ����� ���� ��������
 * ������� ������� (������ ������ � �������) � ������� �����. ���������
 * current/next.
 * 
 * @author pe
 */
public class CharReader {
	private final String text;
	private int currentIndex = -1;
	private int current;

	/**
	 * ����� ������ ������������ CharReader ��� ��������������� �� ������ ������
	 * �� reader.
	 */
	public CharReader(String text) {
		super();
		this.text = text;
		next();
	}

	/**
	 * @return ��������� ����������� ������ ��� -1, ���� �������� ������ ���.
	 */
	public int current() {
		return current;
	}

	/**
	 * @return ��������� ������ ��� -1, ���� �������� ������ ���
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
