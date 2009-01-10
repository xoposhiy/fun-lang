package ru.usu.cs.fun.engine;

import java.util.HashMap;
import java.util.Map;

public class BasicScope implements Scope {

	private Map<String, Fun> funs = new HashMap<String, Fun>();

	@Override
	public void addFun(String name, Fun fun) {
		funs.put(name, fun);
	}

	public Fun findFun(String name) {
		return funs.get(name);
	}
}