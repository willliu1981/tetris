package com.tool;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TFiles {

	public static Set<File> getFiles(String fName) {
		Set<File> set = new HashSet<>();
		File[] files = new File(fName).listFiles();
		set = Stream.of(files).filter(x -> x.isFile()).collect(Collectors.toSet());
		Stream.of(files).filter(x -> x.isDirectory()).map(x2 -> getFiles(x2.toString()))
				.reduce((xx1, xx2) -> {
					xx1.addAll(xx2);
					return xx1;
				}).ifPresent(set::addAll);
		return set;
	}

}
