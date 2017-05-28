package com.curiouslabs.hand2works.util;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonResponseTransformer implements ResponseTransformer{
	
	private Gson gson = new Gson();

	@Override
	public String render(Object model) throws Exception {
		return gson.toJson(model);
	}

}
