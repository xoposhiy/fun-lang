package ru.usu.cs.fun.engine;

/**
 * Узел абстрактного синтаксического дерева программы. Может быть
 * выполнен в рамках некоторой области видимости.
 * @author pe 
 */
public interface ASTNode {
	Object execute(Scope scope);
}
