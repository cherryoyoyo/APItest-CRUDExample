package com.example.APItestservice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiRepostiory.ApiRepostioryPrice;
import com.example.Model.Price;


@Service
public class PriceService {

	@Autowired
     private ApiRepostioryPrice priceRepository;  

     //傳入date查詢價格
	public BigDecimal getPriceByDate(Date date) {
    	BigDecimal price=priceRepository.findPriceByDate(date);
    	return price != null ? price : BigDecimal.ZERO;
     }
	
	//傳入date,price新增資料
     public boolean addPrice(Date date, BigDecimal price) {
    	    Price newPrice = new Price();
    	    newPrice.setDate(date);
    	    newPrice.setPrice(price);
    	    try {
    	        priceRepository.save(newPrice);
    	        System.out.println(newPrice);
    	        return true;
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return false;
    	    }
     }
     
   //修改資料
     public boolean updatePrice(LocalDate date, Double price) {
         int updatedRows = priceRepository.updatePriceByDate(date, price);
         return updatedRows > 0;
     }
     
     //刪除資料
     public boolean deletePrice(LocalDate date) {
         int deleteRows = priceRepository.deletePriceByDate(date);
         return deleteRows > 0;
     }
    
     }  




