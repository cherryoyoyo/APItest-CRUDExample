package com.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@jakarta.persistence.Table(name = "Products")
public class Product {
    // 定義屬性
	@Id
    private int id;
    private String name;
    private String shortName;
    private boolean dataGrouping;

    // Constructor（可選）
    public Product() {
    }

    // Getter 和 Setter 方法

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


    public boolean isDataGrouping() {
		return dataGrouping;
	}

	public void setDataGrouping(boolean dataGrouping) {
		this.dataGrouping = dataGrouping;
	}

	// 可選的 toString 方法，方便查看物件資訊
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", dataGrouping='" + dataGrouping + '\'' +
                '}';
    }
}


