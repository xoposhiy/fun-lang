package ru.usu.cs.fun.engine;

import java.util.List;

/**
 * �������, ������� ��� ���������� ���������� ���� ����������� scope, ���������
 * �� ������ �������� ���� �������. ������������ ��� ������ ������� �����������
 * �������������.
 * 
 * @author pe
 */
public class FixedScopeFun extends Fun {

	private final Fun fun;
	private final Scope staticScope;

	public FixedScopeFun(Fun fun, Scope staticScope) {
		super(fun.getName());
		this.fun = fun;
		this.staticScope = staticScope;
	}

	@Override
	protected Object calculate(Scope notUsedScope, List<Fun> args) {
		return fun.calculate(staticScope, args);
	}

}
