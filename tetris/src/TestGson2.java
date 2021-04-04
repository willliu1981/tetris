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

abstract class Birds {
	public final static String NameInfoType_Master = "master";
	public final static String NameInfoType_Self = "self";

	protected enum NameInfoType {
		Master, Self
	}

	private Map<Enum<NameInfoType>, String> mapNameInfo = new HashMap<>();// <type,name>

	public void setNameInfo(String name, NameInfoType type) {
		this.mapNameInfo.put(type, name);
	}

	public String getName(NameInfoType type) {
		return mapNameInfo.get(type);
	}

	public String toString() {
		return String.format("%s::infoMap->%s", this.getClass().getTypeName(), this.mapNameInfo);
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
		eagle.setNameInfo("Alice", Birds.NameInfoType.Self);
		eagle.setNameInfo("Peter", Birds.NameInfoType.Master);
		eagle.setFlyHeight(100);
		eagle.setHuntingAtk(3600);

		Penguin penguin = new Penguin();
		penguin.setNameInfo("Tiffany", Birds.NameInfoType.Self);
		penguin.setNameInfo("Mary", Birds.NameInfoType.Master);
		penguin.setSwimmingSpeed(20);

		Map<String, Birds> toJsonPetMap = new HashMap<>();// <master,pet>
		toJsonPetMap.put("Peter", eagle);
		toJsonPetMap.put("Mary", penguin);

		String json = toJson(toJsonPetMap);

		System.out.println("toJson" + json);
		System.out.println("fromJsonPetMap->" + fromJson(json));
	}

	static String toJson(Map<String, Birds> mapPet) {
		JsonSerializer<Birds> serializer = new JsonSerializer<Birds>() {
			@Override
			public JsonElement serialize(Birds birds, Type typeOfOri, JsonSerializationContext context) {
				JsonObject jsonObj = new JsonObject();
				jsonObj.addProperty("type", typeOfOri.getTypeName());
				jsonObj.add("data", new Gson().toJsonTree(birds));
				return jsonObj;
			}
		};
		String strJson = new GsonBuilder().registerTypeAdapter(Eagle.class, serializer)
				.registerTypeAdapter(Penguin.class, serializer).create().toJson(mapPet);
		return strJson;
	}

	static Map<String, Birds> fromJson(String json) {
		Type type = new TypeToken<Map<String, Birds>>() {
		}.getType();

		return new GsonBuilder().registerTypeAdapter(Birds.class, new JsonDeserializer<Birds>() {
			@Override
			public Birds deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context)
					throws JsonParseException {
				String type = elem.getAsJsonObject().get("type").getAsString();
				JsonObject data = elem.getAsJsonObject().get("data").getAsJsonObject();

				Gson gsonBirds = new GsonBuilder().registerTypeAdapter(Enum.class, new JsonDeserializer<Enum<?>>() {
					@Override
					public Enum<?> deserialize(JsonElement elem, Type typeOfOri, JsonDeserializationContext context)
							throws JsonParseException {
						return new Gson().fromJson(elem, Birds.NameInfoType.class);
					}
				}).create();
				switch (type) {
				case "Eagle":
					return gsonBirds.fromJson(data, Eagle.class);
				case "Penguin":
					return gsonBirds.fromJson(data, Penguin.class);
				default:
					throw new IllegalArgumentException("No match class");
				}
			}
		}).create().fromJson(json, type);
	}

}
