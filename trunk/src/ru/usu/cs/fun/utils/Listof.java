package ru.usu.cs.fun.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Вспомогательный класс для упрощения создания списков строк.
 * @author pe
 */
public class Listof {
	public static List<String> strings(String... args) {
		return Arrays.asList(args);
	}
}
