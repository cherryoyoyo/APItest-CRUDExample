package com.example.APItestservice;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ApiRepostiory.ApiRepostioryPrice;
import com.example.Model.Price;
import com.example.Model.Product;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//從外部API獲得資料後，插入兩張table
@Service
public class DataRespService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiRepostioryPrice apiRepostioryPrice;

    @Autowired
    private ApiRepostioryProduct apiRepostioryProduct;
    // 取得 API 資料
    public JsonNode getApiData() {
        String urlString = "https://www.cathaybk.com.tw/cathaybk/service/newwealth/fund/chartservice.asmx/GetFundNavChart";
        String requestBody = "{\"req\":{\"Keys\":[\"10480016\"],\"From\":\"2023/03/10\",\"To\":\"2024/03/10\"}}";

        // 設定請求標頭
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 建立 HttpEntity 包含 headers 和 body
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        JsonNode rootNode ;
        Product product = new Product();
        
        try {
            // 發送 POST 請求並接收回應
            ResponseEntity<String> response = restTemplate.postForEntity(urlString, request, String.class);

            // 使用 ObjectMapper 解析 JSON 回傳結果
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(response.getBody());

            // 拆解資料，並將 product 存於 product table
            int id = rootNode.get("Data").get(0).get("id").asInt();
            String name = rootNode.get("Data").get(0).get("name").asText();
            String shortName = rootNode.get("Data").get(0).get("shortName").asText();
            boolean dataGrouping = rootNode.get("Data").get(0).get("dataGrouping").asBoolean();

            product.setId(id);
            product.setName(name);
            product.setShortName(shortName);
            product.setDataGrouping(dataGrouping);
          
            savePriceData(rootNode, product);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return rootNode;
    }

    // 保存 price 資料的方法
    public boolean savePriceData(JsonNode rootNode, Product product) {
        JsonNode dataArray = rootNode.get("Data").get(0).get("data");
        System.out.println(dataArray);
        
        

        try {
            for (JsonNode dataEntry : dataArray) {
            	System.out.println(dataEntry.get(0));
                Date date = Date.from(Instant.ofEpochMilli(dataEntry.get(0).asLong()));
                BigDecimal productPrice = dataEntry.get(1).decimalValue();

                Price priceRecord = new Price();
                priceRecord.setProductid(product.getId());
                priceRecord.setDate(date);
                priceRecord.setPrice(productPrice);

                apiRepostioryPrice.save(priceRecord);
            }
            return true; // 成功時回傳 true
        } catch (Exception e) {
            System.err.println("Error saving price data: " + e.getMessage());
            e.printStackTrace();
            return false; // 失敗時回傳 false
        }
    }
}
