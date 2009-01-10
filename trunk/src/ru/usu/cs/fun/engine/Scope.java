package ru.usu.cs.fun.engine;


/**
 * ������� ��������� - ����� ��������� �������. 
 * ��������� �������� ����� ��������� � ������� ��������� ����� �������.
 * @author pe
 *
 */
public interface Scope {
	void addFun(String name, Fun fun);

	Fun findFun(String name);

}
