package com.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@jakarta.persistence.Table(name = "Price")

public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	    private int priceseq;      // 唯一標識
	    private int productid;     // 產品ID
	    private Date date;         // 日期
	    private BigDecimal price;  // 價格

	    
	    public Price() {
	    }
	    
	    public int getPriceseq() {
	        return priceseq;
	    }

	    public void setPriceseq(int priceseq) {
	        this.priceseq = priceseq;
	    }

	    public int getProductid() {
	        return productid;
	    }

	    public void setProductid(int productid) {
	        this.productid = productid;
	    }

	    public Date getDate() {
	        return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public BigDecimal getPrice() {
	        return price;
	    }

	    public void setPrice(BigDecimal price) {
	        this.price = price;
	    }

	    // 這是一個可選的toString方法，方便顯示Price物件的內容
	    @Override
	    public String toString() {
	        return "Price{" +
	                "priceseq=" + priceseq +
	                ", productid=" + productid +
	                ", date=" + date +
	                ", price=" + price +
	                '}';
	    }
	}



