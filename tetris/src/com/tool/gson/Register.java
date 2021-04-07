package com.tool.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Register {
	default Gson getGson() {
		GsonBuilder builder = this.registerChildrenNodeTypeAdapter(new GsonBuilder());
		if (builder == null) {
			return new Gson();
		}
		return builder.create();
	}

	public abstract GsonBuilder registerChildrenNodeTypeAdapter(GsonBuilder builder);
}
