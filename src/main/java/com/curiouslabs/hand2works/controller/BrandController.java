package com.curiouslabs.hand2works.controller;

import static spark.Spark.*;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.curiouslabs.hand2works.dao.BrandDao;
import com.curiouslabs.hand2works.model.Brand;
import com.curiouslabs.hand2works.util.JsonResponseTransformer;
import com.curiouslabs.hand2works.util.RouteConstants;
import com.google.gson.Gson;


public class BrandController {
	
	public BrandController(AnnotationConfigApplicationContext ctx) {
		Gson gson = new Gson();
		BrandDao brandDao = ctx.getBean(BrandDao.class);
		
		get(RouteConstants.BRAND.PATH, (request, response) -> {
			List<Brand> brands = brandDao.getAll();
			int idx = 0;
			for(Brand brand : brands) {
				brand.setIdx(++idx);
			}
			return brands;
		}, new JsonResponseTransformer());
		
		post(RouteConstants.BRAND.PATH, (request, response) -> {
			Brand brand = gson.fromJson(request.body(), Brand.class);
			brandDao.save(brand);
			response.status(201);
			return brand;
		}, new JsonResponseTransformer());
		
		patch(RouteConstants.BRAND.PATH, (request, response) -> {
			response.status(200);
			return "";
		}, new JsonResponseTransformer());
		
	}

}
