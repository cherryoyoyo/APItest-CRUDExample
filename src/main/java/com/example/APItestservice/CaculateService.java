package com.example.APItestservice;

	import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.stereotype.Service;
	import org.springframework.web.client.RestTemplate;

import com.example.ApiRepostiory.ApiRepostioryPrice;
import com.example.Model.Price;

import org.springframework.http.ResponseEntity;import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.web.client.RestTemplate;

	@Service
	public class CaculateService {
		
		 @Autowired
		    private ApiRepostioryPrice apiRepostioryPrice;

		    // 計算漲跌 (後收 - 前收)
		    public BigDecimal calculatePriceChange(LocalDate startDate, LocalDate endDate) {
		        List<Price> prices = apiRepostioryPrice.findPricesInRange( startDate, endDate);
		        
		        if (prices.size() < 2) {
		            throw new IllegalArgumentException("Price data is insufficient to calculate change");
		        }

		        // 假設第一筆是前收，最後一筆是後收
		         BigDecimal firstPrice = prices.get(0).getPrice(); // 取得第一筆價格		        
		         BigDecimal lastPrice = prices.get(prices.size() - 1).getPrice(); // 取得最後一筆價格
		        // 計算漲跌（後收 - 前收），使用subtract
		         BigDecimal Change = lastPrice.subtract(firstPrice);
		         System.out.println("漲跌 = " + Change);
		         return Change; 
		    }

		    // 計算漲跌幅 (後收 - 前收) / 前收
		    public BigDecimal calculatePriceChangePercent(LocalDate startDate, LocalDate endDate) {
            List<Price> prices = apiRepostioryPrice.findPricesInRange(startDate, endDate);
		        
		        if (prices.size() < 2) {
		            throw new IllegalArgumentException("Price data is insufficient to calculate change");
		        }

		        BigDecimal firstPrice = prices.get(0).getPrice(); // 取得第一筆價格		        
		         BigDecimal lastPrice = prices.get(prices.size() - 1).getPrice(); // 取得最後一筆價格
		        // 計算漲跌（後收 - 前收），使用subtract
		         BigDecimal Change = lastPrice.subtract(firstPrice);
		         BigDecimal Percentage = Change.divide(firstPrice, 4, RoundingMode.HALF_UP); // 保留4位小數，四捨五入
		         System.out.println("漲跌幅= " + Percentage);
		         return Percentage; 
		        
		        
		    }

	 
	

	

}
