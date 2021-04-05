package com.test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.test.Birds.FullName;
import com.test.Birds.Name;

abstract class Birds {

	protected static class Name {
		private String name;

		public Name(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String firstName) {
			name = firstName;
		}

		public String toString() {
			return String.format("%s", this.name);
		}
	}

	protected static class FullName extends Name {
		private String lastName;

		public FullName(String firstName, String lastName) {
			super(firstName);
			this.lastName = lastName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public void setFirstName(String firstName) {
			super.setName(firstName);
		}

		public String getFirstName() {
			return super.getName();
		}

		public String toString() {
			return String.format("%s %s", this.getFirstName(), this.lastName);
		}

	}

	protected enum NameInfoType {
		Master, Self
	}

	private Map<Enum<NameInfoType>, Name> mapNameInfo = new HashMap<>();// <type,name>

	public void setNameInfo(Name name, NameInfoType type) {
		this.mapNameInfo.put(type, name);
	}

	public Name getName(NameInfoType type) {
		return mapNameInfo.get(type);
	}

	public String toString() {
		return String.format("%s::infoMap->%s", this.getClass().getClass().getSimpleName(), this.mapNameInfo);
	}

}

class Eagle extends Birds {
	private int flyHeight;
	private int huntingAtk;

	public void setFlyHeight(int height) {
		this.flyHeight = height;
	}

	public int getFlyHeight() {
		return this.flyHeight;
	}

	public int getHuntingAtk() {
		return huntingAtk;
	}

	public void setHuntingAtk(int huntingAtk) {
		this.huntingAtk = huntingAtk;
	}

	public String toString() {
		return super.toString() + String.format(",flyHeight->%d,huntingAtk->%d", this.flyHeight, this.huntingAtk);
	}
}

class Penguin extends Birds {
	private int swimmingSpeed;

	public void setSwimmingSpeed(int speed) {
		this.swimmingSpeed = speed;
	}

	public int getSwimmingSpeed() {
		return this.swimmingSpeed;
	}

	public String toString() {
		return super.toString() + String.format(",swimmingSpeed->%d", this.swimmingSpeed);
	}
}

public class TestGson2 {

	public static void main(String[] args) {
		Eagle eagle = new Eagle();
		eagle.setNameInfo(new Name("Alice"), Birds.NameInfoType.Self);
		eagle.setNameInfo(new FullName("Peter", "Wu"), Birds.NameInfoType.Master);
		eagle.setFlyHeight(100);
		eagle.setHuntingAtk(3600);

		Penguin penguin = new Penguin();
		penguin.setNameInfo(new Name("Tiffany"), Birds.NameInfoType.Self);
		penguin.setNameInfo(new FullName("Mary", "Li"), Birds.NameInfoType.Master);
		penguin.setSwimmingSpeed(20);

		Map<String, Birds> toJsonPetMap = new HashMap<>();// <master,pet>
		toJsonPetMap.put("Peter", eagle);
		toJsonPetMap.put("Mary", penguin);

		String json = toJson(toJsonPetMap);

		System.out.println("toJson" + json);
		System.out.println("fromJsonPetMap->" + fromJson(json));
	}

	static String toJson(Map<String, Birds> mapPet) {
		JsonSerializer<Birds> birdsSerializer = (birds, bt, bc) -> {
			JsonObject birdsJObj = new JsonObject();
			birdsJObj.addProperty("type", birds.getClass().getSimpleName());
			birdsJObj.add("data",
					new GsonBuilder().registerTypeAdapter(Name.class, (JsonSerializer<Name>) (name, nt2, nc2) -> {
						JsonObject nameJObj = new JsonObject();
						nameJObj.addProperty("type", name.getClass().getSimpleName());
						nameJObj.add("data", new Gson().toJsonTree(name));
						return nameJObj;
					}).create().toJsonTree(birds));
			return birdsJObj;
		};

		String strJson = new GsonBuilder().registerTypeAdapter(Eagle.class, birdsSerializer)
				.registerTypeAdapter(Penguin.class, birdsSerializer).create().toJson(mapPet);
		return strJson;
	}

	static Map<String, Birds> fromJson(String json) {
		return new GsonBuilder().registerTypeAdapter(Birds.class, (JsonDeserializer<Birds>) (birds, bt, bc) -> {
			String bType = birds.getAsJsonObject().get("type").getAsString();
			JsonObject bData = birds.getAsJsonObject().get("data").getAsJsonObject();

			Gson gsonBirds = new GsonBuilder()
					.registerTypeAdapter(Enum.class,
							(JsonDeserializer<Enum<?>>) (inum, et, ec) -> new Gson().fromJson(inum,
									Birds.NameInfoType.class))
					.registerTypeAdapter(Name.class, (JsonDeserializer<Name>) (name, et, ec) -> {
						String nType = name.getAsJsonObject().get("type").getAsString();
						JsonObject nData = name.getAsJsonObject().get("data").getAsJsonObject();
						switch (nType) {
						case "Name":
							return new Gson().fromJson(nData, Name.class);
						case "FullName":
							return new Gson().fromJson(nData, FullName.class);
						default:
							throw new IllegalArgumentException("No match class");
						}
					}).create();
			switch (bType) {
			case "Eagle":
				return gsonBirds.fromJson(bData, Eagle.class);
			case "Penguin":
				return gsonBirds.fromJson(bData, Penguin.class);
			default:
				throw new IllegalArgumentException("No match class");
			}
		}).create().fromJson(json, new TypeToken<Map<String, Birds>>() {
		}.getType());
	}

}
