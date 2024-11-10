package com.example.Dto;

import java.math.BigDecimal;

import com.example.Model.Price;


public class ResponseData {
	
//取的API資料對應的欄位
	private int id;
    private String name;
    private String shortName;
    private String dataGrouping;
    private Object[] data;
		
		
    public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDataGrouping() {
        return dataGrouping;
    }

    public void setDataGrouping(String dataGrouping) {
        this.dataGrouping = dataGrouping;
    }
	


}
