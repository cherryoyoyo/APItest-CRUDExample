package com.example.APItestcontroller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.APItestservice.PriceService;
import com.example.ApiRepostiory.ApiRepostioryPrice;
import com.example.ApiRepostiory.DatabaseConnectionTest;
import com.example.Model.Price;

import ch.qos.logback.core.joran.conditional.IfAction;

//增刪修查API

@RestController
@RequestMapping("/crud")
public class CRUDApi {
	@Autowired
    private ApiRepostioryPrice apiRepositoryPrice;
	@Autowired
	public DatabaseConnectionTest databaseTest ;
	@Autowired
	public PriceService priceService;
	
	//查詢價格
	@GetMapping("/getPrice")
    public BigDecimal getPriceByDate(@RequestParam("date") @DateTimeFormat (pattern="yyyy-MM-dd") Date date) {
		
        return priceService.getPriceByDate(date);
    }
	
	@PostMapping("/addPrice")
	public String addPrice(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
	                       @RequestParam("price") BigDecimal price) {
	    System.out.println("Received date: " + date);
	    System.out.println("Received price: " + price);
	    
	    boolean isAdded = priceService.addPrice(date, price);
	    
	    if (isAdded) {
	        return "Price added successfully.";
	    } else {
	        return "Failed to add price.";
	    }
	}
	
	@PutMapping("/updatePrice")
	public String updatePrice(@RequestParam String date, @RequestParam Double price) {
        // 解析傳入的日期字串為 LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        boolean isupdate =priceService.updatePrice(parsedDate, price);
        if(isupdate=true) {
        	return "update succeed";
        }
        else {
        	return "update fail";
        
        }
	}
        
        @DeleteMapping("/deletePrice")
    	public String deletePrice(@RequestParam String date) {
            // 解析傳入的日期字串為 LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            boolean isdelete=priceService.deletePrice(parsedDate);
            
            if(isdelete=true) {
            	return "delete price successfully";
            }
            else {
            	return "fail to delete";
            }
          
	}
}


	
	
	
	
	
	
	
	
    
    
	


