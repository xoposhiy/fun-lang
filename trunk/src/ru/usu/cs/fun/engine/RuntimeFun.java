package ru.usu.cs.fun.engine;

import java.util.List;

/**
 * Заглушка. Реальная функция, стоящая за этим именем не известна до момента выполнения.
 * В момент выполнения реальная функция получается по имени из scope.
 * @author pe
 */
public class RuntimeFun extends Fun {

	public RuntimeFun(String name) {
		super(name);
	}

	@Override
	protected Object calculate(Scope scope, List<Fun> args) {
		Fun fun = scope.findFun(getName());
		if (fun == null)
			throw new RuntimeException("fun " + getName() + " is undefined");
		for (Fun arg : args) {
			fun = fun.curry(0, arg);
		}
		return fun.execute(scope);
	}
}
