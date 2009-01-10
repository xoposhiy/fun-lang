package ru.usu.cs.fun.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Основной строительный блок программ на языке fun. Функция - это нечто, что
 * можно выполнить, указав значения аргументов. Кроме того у функции есть имя. А
 * ещё есть возможность совершать над функцией хитрую операцию карринга. Карринг
 * (currying) - это преобразование функции от n аргументов в функцию от n-1
 * аргумента, путём фиксации значения одного из аргументов.
 * <p>
 * Например так: {@code f(x) = g(x, 1)}.
 * 
 * @author pe
 */
public abstract class Fun {

	private final String name;

	public Fun(String name) {
		this.name = name;
	}

	protected abstract Object calculate(Scope scope, List<Fun> args);

	public Fun curry(int argPos, Fun value) {
		return new CurryedFun(this, argPos, value);
	}

	public Object execute(Scope scope) {
		return calculate(scope, new ArrayList<Fun>());
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
