package ru.usu.cs.fun.engine;

import java.io.IOException;
import java.io.Reader;

/**
 * ������� ������� ��� ������� Reader. � ���������� ���� ����� ���� ��������
 * ������� ������� (������ ������ � �������) � ������� �����. ���������
 * current/next.
 * 
 * @author pe
 */
public class CharReader {
	private final Reader reader;
	private int current;

	/**
	 * ����� ������ ������������ CharReader ��� ��������������� �� ������ ������
	 * �� reader.
	 */
	public CharReader(Reader reader) {
		super();
		this.reader = reader;
		current = next();
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
