package com.curiouslabs.hand2works.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Brand extends BaseObject{
	
	private String brandName;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	

}
