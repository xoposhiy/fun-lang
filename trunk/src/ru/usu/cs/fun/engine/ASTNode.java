package ru.usu.cs.fun.engine;

/**
 * ���� ������������ ��������������� ������ ���������. ����� ����
 * �������� � ������ ��������� ������� ���������.
 * @author pe 
 */
public interface ASTNode {
	Object execute(Scope scope);
}
