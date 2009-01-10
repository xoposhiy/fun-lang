package ru.usu.cs.fun.lang;

import java.util.List;

import ru.usu.cs.fun.engine.Fun;
import ru.usu.cs.fun.engine.Scope;

public class If extends Fun {
	public If() {
		super("if");
	}

	@Override
	protected Object calculate(Scope scope, List<Fun> args) {
		if ((Boolean) args.get(0).execute(scope))
			return args.get(1).execute(scope);
		return args.get(2).execute(scope);
	}
}
