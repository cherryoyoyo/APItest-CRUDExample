package com.example.ApiRepostiory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.Model.Price;
import com.example.Model.Product;

import jakarta.transaction.Transactional;

public interface ApiRepostioryPrice extends JpaRepository<Price, Integer> {

    // 查詢某日期的價格
    @Query("SELECT p.price FROM Price p WHERE FUNCTION('DATE', p.date) = :date")
    BigDecimal findPriceByDate(@Param("date") Date date);

    // 更新某日期的價格
    @Transactional
    @Modifying
    @Query("UPDATE Price p SET p.price = :price WHERE FUNCTION('DATE', p.date) = :date")
    int updatePriceByDate(@Param("date") LocalDate date, @Param("price") Double price);
    
    //刪除價格
    @Transactional
    @Modifying
    @Query("DELETE FROM Price p WHERE FUNCTION('DATE', p.date) = :date")
    int deletePriceByDate(@Param("date") LocalDate date);
    //區間內金額
    @Query("SELECT p FROM Price p WHERE FUNCTION('DATE', p.date) >= :startDate AND FUNCTION('DATE', p.date) <= :endDate ORDER BY p.date ASC")
    List<Price> findPricesInRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}








	

