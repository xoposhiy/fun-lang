package ru.usu.cs.fun.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Обёртка над функцией, фиксирующая один из аргументов этой функции. Результат
 * карринга функции.
 * 
 * @author pe
 */
public class CurryedFun extends Fun {

	private final Fun fun;
	private final int pos;
	private final Fun value;

	public CurryedFun(Fun realFun, int argPos, Fun value) {
		super(realFun.getName() + "(" + (argPos == 0 ? "" : argPos + "<-")
				+ value + ")");
		this.fun = realFun;
		this.pos = argPos;
		this.value = value;
	}

	@Override
	protected Object calculate(Scope scope, List<Fun> args) {
		if (args == null)
			args = new ArrayList<Fun>();
		args.add(pos, value);
		return fun.calculate(scope, args);
	}

}
