package ru.usu.cs.fun.engine;

import java.util.List;

public class ConstantFun extends Fun {

	private final Object value;

	public ConstantFun(Object value) {
		super(value.toString());
		this.value = value;
	}

	@Override
	protected Object calculate(Scope scope, List<Fun> args) {
		return value;
	}
}
