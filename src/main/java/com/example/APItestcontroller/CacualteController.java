package com.example.APItestcontroller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.APItestservice.CaculateService;

//計算漲跌幅API

@RestController
@RequestMapping("/caculate")
public class CacualteController {
	
	
	@Autowired
    private CaculateService caculateService;

    // 計算漲跌 (後收 - 前收)
    @GetMapping("/change")
    public BigDecimal getPriceChange(@RequestParam String startDate, @RequestParam String endDate) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate start = LocalDate.parse(startDate,formatter);
        LocalDate end = LocalDate.parse(endDate,formatter);
        System.out.println("34");
        return caculateService.calculatePriceChange(start, end);
    }

    // 計算漲跌幅 (後收 - 前收) / 前收
    @GetMapping("/percentage")
    public BigDecimal getPriceChangePercentage(@RequestParam String startDate, @RequestParam String endDate) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter); // 使用 LocalDate
        LocalDate end = LocalDate.parse(endDate, formatter); // 使用 LocalDate
        return  caculateService.calculatePriceChangePercent(start, end);
    }
}
