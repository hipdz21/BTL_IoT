/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.dao;

import com.example.BTL_IoT_HTTC.model.Clock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author dovan
 */
@Service
public interface ClockRepository extends JpaRepository<Clock, Integer>{
    @Query(value = "SELECT * FROM clocks  WHERE account_id = ?1", nativeQuery = true)
    public List<Clock> findAllClockByAcount(int account_id);
}
