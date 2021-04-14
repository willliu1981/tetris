package com.tool;

import java.io.File;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TFiles {
	public static final String regularSeparator="\\\\";
	public static final String root="src";

	public static Set<File> getFiles(String fName) {
		File[] files = new File(fName).listFiles();
		Set<File> set = Stream.of(files).filter(x -> x.isFile()).collect(Collectors.toSet());
		Stream.of(files).filter(x -> x.isDirectory()).map(x2 -> getFiles(x2.toString())).reduce((s1, s2) -> {
			s1.addAll(s2);
			return s1;
		}).ifPresent(set::addAll);
		return set;
	}

	public static String removeExtension(String fName) {
		StringBuilder sb = new StringBuilder(fName);
		int dot = sb.lastIndexOf(".");

		return sb.substring(0, dot).toString();
	}

	public static String removeExtension(File file) {
		return removeExtension(file.getName());
	}

	public static String separatorToDot(String oriStr, String oriSeparator) {
		return Pattern.compile(oriSeparator).matcher(new StringBuilder(oriStr)).replaceAll(".");
	}
}
