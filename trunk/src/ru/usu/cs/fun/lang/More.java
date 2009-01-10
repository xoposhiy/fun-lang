package ru.usu.cs.fun.lang;

import java.util.List;

import ru.usu.cs.fun.engine.Fun;
import ru.usu.cs.fun.engine.Scope;

public class More extends Fun {
	public More() {
		super(">");
	}

	@Override
	protected Object calculate(Scope scope, List<Fun> args) {
		return (Integer) args.get(0).execute(scope) > (Integer) args.get(1)
				.execute(scope);
	}
}
