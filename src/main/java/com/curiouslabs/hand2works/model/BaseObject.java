package com.curiouslabs.hand2works.model;

import com.google.gson.annotations.JsonAdapter;

public abstract class BaseObject{
    private Long id;
    private int idx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}
    
}
