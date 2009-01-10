package ru.usu.cs.fun.engine;

/**
 * ����� - ��� ������� ������, �������������� ����������� ������� ���������.
 * Parser �������� � ������� ���������, �������� �� ������.
 * � ������� ����� ����� ������� ���������� � ������ ������ � ������� � ������.
 * 
 * @author pe
 */
public class Token {
	private String value;

	public Token(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "<" + value + ">";
	}

}
