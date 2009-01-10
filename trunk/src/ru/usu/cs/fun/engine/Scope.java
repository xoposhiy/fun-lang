package ru.usu.cs.fun.engine;


/**
 * Область видимости - набор доступных функций. 
 * Некоторые операции могут добавлять в область видимости новые функции.
 * @author pe
 *
 */
public interface Scope {
	void addFun(String name, Fun fun);

	Fun findFun(String name);

}
