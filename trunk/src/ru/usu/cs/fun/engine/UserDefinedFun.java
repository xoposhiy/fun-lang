package ru.usu.cs.fun.engine;

import java.util.List;

/**
 * Функция, определённая средствами самого языка. У нее есть имя, список имён
 * аргументов и тело функции.
 * 
 * @author pe
 */
public class UserDefinedFun extends Fun {

	private final Fun body;
	private final List<String> args;

	public UserDefinedFun(String name, List<String> args, Fun body) {
		super(name);
		this.args = args;
		this.body = body;
	}

	@Override
	protected Object calculate(Scope scope, List<Fun> argFuns) {
		ChildScope s = new ChildScope(scope);
		for (int i = 0; i < args.size(); i++) {
			s.addFun(args.get(i), new FixedScopeFun(argFuns.get(i), scope));
		}
		return body.execute(s);
	}

}
