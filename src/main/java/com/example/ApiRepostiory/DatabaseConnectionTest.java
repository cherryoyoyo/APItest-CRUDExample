package com.example.ApiRepostiory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Model.Product;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

	@Autowired
	private ApiRepostioryPrice apiRepositoryPrice;

	public void printPriceProducts() {
		// 列出 Price 表中資料的數量
		long count = apiRepositoryPrice.count();
		System.out.println("連線成功！Price 表中的資料數量: " + count);
		// ptoduct 列出所有欄位
		apiRepositoryPrice.findAll().forEach(price -> {
			System.out.println("Price: " + price.getProductid());
		});

		apiRepositoryPrice.findAll().forEach(product -> {
			System.out.println("Product: " + product.getProductid());
		});

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}
}
