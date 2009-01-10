package ru.usu.cs.fun.engine;

/**
 * Scope для тестирования. Знает про числа и функции + и =.
 * 
 * @author pe
 */
public class FakeScope extends BasicScope {

	public FakeScope() {
		super();
		addFun("+", new Fun("+") {
			@Override
			protected Object calculate(Scope scope, java.util.List<Fun> args) {
				return (Integer) args.get(0).execute(scope)
						+ (Integer) args.get(1).execute(scope);
			}
		});
		addFun("=", new Fun("=") {
			@Override
			protected Object calculate(Scope scope, java.util.List<Fun> args) {
				return args.get(0).execute(scope).equals(
						args.get(1).execute(scope));
			}
		});

	}

	@Override
	public Fun findFun(String name) {
		try {
			return new ConstantFun(Integer.parseInt(name));
		} catch (NumberFormatException e) {
			return super.findFun(name);
		}
	}
}