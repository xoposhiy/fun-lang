package ru.usu.cs.fun.front;

public interface Lexer {
	// ��� ������ ����� ������ ���������� ��������� ����������� �������
	// ���� ������ ������ ��� � ���������� null
	public Lexeme readNext();

	// ��������� ����������� �������, ���� null, ���� ������ ������ ���
	public Lexeme current();
}
