package ru.usu.cs.fun.engine;

public class ChildScope extends BasicScope implements Scope {

	private final Scope parentScope;

	public ChildScope(Scope parentScope) {
		this.parentScope = parentScope;
	}

	@Override
	public Fun findFun(String name) {
		Fun res = super.findFun(name);
		if (res != null)
			return res;
		return parentScope.findFun(name);
	}
}
