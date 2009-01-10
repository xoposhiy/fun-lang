package ru.usu.cs.fun.lang;

import ru.usu.cs.fun.engine.BasicScope;
import ru.usu.cs.fun.engine.ConstantFun;
import ru.usu.cs.fun.engine.Fun;

public class FunScope extends BasicScope {

	public FunScope() {
		super();
		addFun("+", new Add());
		addFun("-", new Substract());
		addFun("*", new Multiply());
		addFun("/", new Div());
		addFun("%", new Mod());
		addFun("=", new Eq());
		addFun("#", new NotEq());
		addFun(">", new More());
		addFun("<", new Less());
		addFun(">=", new MoreEq());
		addFun("<=", new LessEq());
		addFun("&", new And());
		addFun("if", new If());
		addFun("!", new Not());
	}

	@Override
	public Fun findFun(String name) {
		Fun constant = resolveConstant(name);
		if (constant != null)
			return constant;
		return super.findFun(name);
	}

	private Fun resolveBool(String name) {
		if (name.equals("true"))
			return new ConstantFun(true);
		if (name.equals("false"))
			return new ConstantFun(false);
		return null;
	}

	private Fun resolveConstant(String name) {
		Fun r = resolveInt(name);
		if (r == null)
			r = resolveBool(name);
		return r;
	}

	private Fun resolveInt(String name) {
		try {
			return new ConstantFun(Integer.parseInt(name));
		} catch (Exception e) {
			return null;
		}
	}

}
