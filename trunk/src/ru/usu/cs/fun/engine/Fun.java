package ru.usu.cs.fun.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * �������� ������������ ���� �������� �� ����� fun. ������� - ��� �����, ���
 * ����� ���������, ������ �������� ����������. ����� ���� � ������� ���� ���. �
 * ��� ���� ����������� ��������� ��� �������� ������ �������� ��������. �������
 * (currying) - ��� �������������� ������� �� n ���������� � ������� �� n-1
 * ���������, ���� �������� �������� ������ �� ����������.
 * <p>
 * �������� ���: {@code f(x) = g(x, 1)}.
 * 
 * @author pe
 */
public abstract class Fun {

	private final String name;

	public Fun(String name) {
		this.name = name;
	}

	protected abstract Object calculate(Scope scope, List<Fun> args);

	public Fun curry(int argPos, Fun value) {
		return new CurryedFun(this, argPos, value);
	}

	public Object execute(Scope scope) {
		return calculate(scope, new ArrayList<Fun>());
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
