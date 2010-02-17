package ru.usu.cs.fun.lang;

import java.util.HashMap;
import java.util.Map;

import ru.usu.cs.fun.back.Scope;
import ru.usu.cs.fun.back.Term;

public class FunScope implements Scope {

	private final Map<String, Term> items = new HashMap<String, Term>();

	public void add(String name, Term term) {
		if (items.containsKey(name))
			throw new RuntimeException("Symbol '" + name + "' has beed already defined");
		items.put(name, term);
	}

	@Override
	public Term get(String name) {
		Term result = resolveConstant(name);
		if (result != null)
			return result;
		if (name.equals("="))
			return new Eq();
		if (name.equals("+"))
			return new Add();
		result = items.get(name);
		if (result == null)
			return Term.bottom;
		return result;
	}

	private Term resolveConstant(String name) {
		Term result = resolveInt(name);
		if (result == null)
			result = resolveBool(name);
		return result;
	}

	private Term resolveInt(String name) {
		try {
			return new Int(Integer.parseInt(name));
		} catch (Exception e) {
			return null;
		}
	}

	private Term resolveBool(String name) {
		if (name.equals("true"))
			return Bool.TRUE;
		else if (name.equals("false"))
			return Bool.FALSE;
		else
			return null;
	}
}