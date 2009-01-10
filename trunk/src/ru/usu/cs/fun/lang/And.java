package ru.usu.cs.fun.lang;

import java.util.List;

import ru.usu.cs.fun.engine.Fun;
import ru.usu.cs.fun.engine.Scope;

public class And extends Fun {
	public And() {
		super("&");
	}

	@Override
	protected Object calculate(Scope scope, List<Fun> args) {
		return (Boolean) args.get(0).execute(scope)
				&& (Boolean) args.get(1).execute(scope);
	}
}
