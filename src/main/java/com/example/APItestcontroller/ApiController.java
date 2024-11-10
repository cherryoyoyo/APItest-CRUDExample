package com.example.APItestcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.APItestservice.DataRespService;
import com.example.ApiRepostiory.ApiRepostioryPrice;
import com.example.ApiRepostiory.DatabaseConnectionTest;
///*測試連結資料庫是否成功，以及資料是否成功匯入table

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
    private ApiRepostioryPrice apiRepositoryPrice;
	@Autowired
	public DatabaseConnectionTest databaseTest ;
	@Autowired
	private DataRespService dataRespService;;
	
	@GetMapping("/price")
	public String counttable() {
		databaseTest.printPriceProducts();
		return "連線成功，列印成功";
		
	}
	
	@GetMapping("/datainsert")
	public String insertdata() {
		dataRespService.getApiData();
	
		return "獲取資料成功";
		
	}
		
		
	

}
