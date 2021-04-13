package com.test;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.reflect.TypeToken;
import com.main.control.manager.SignManager.SignType;
import com.main.model.Sign;
import com.tool.direction.Direction;

public class Test7 {

	public static void main(String[] args) {
		Map<Integer, Map<Direction, Sign>> map = new HashMap<>();
		Map<Direction, Sign> strMap = new HashMap<>();
		Map<Direction, Sign> strMap2 = new HashMap<>();
		strMap.put(new Direction(0,0), new Sign() {});
		strMap.put(new Direction(1,0), new Sign() {});
		strMap.put(new Direction(2,0), new Sign() {});
		map.put(0, strMap);
		strMap2.put(new Direction(0,1), new Sign() {});
		strMap2.put(new Direction(1,1), new Sign() {});
		strMap2.put(new Direction(2,1), new Sign() {});
		map.put(1, strMap2);
		
		
		Map<Direction, Sign> newMap = map.values().stream().reduce( (x1, x2) -> {
			x1.putAll(x2);
			return x1;
		}).get();

		System.out.println(newMap  );
		System.out.println(newMap.size()  );
	}

	static class AA<T> {
		Map<String, Integer> map = new HashMap<>();
		TypeToken<T> token = new TypeToken<T>() {
		};

		<R> TypeToken getToken(R s) {
			s.getClass();

			return new TypeToken() {
			};
		}

	}
}
