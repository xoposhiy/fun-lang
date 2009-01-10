package ru.usu.cs.fun.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для упрощения создания списков строк.
 * @author pe
 */
public class Listof {
	public static List<String> strings(String... args) {
		ArrayList<String> res = new ArrayList<String>();
		for (String s : args) {
			res.add(s);
		}
		return res;
	}
}
