/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.dao;

import com.example.BTL_IoT_HTTC.model.Account;
import com.example.BTL_IoT_HTTC.model.Parameter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author dovan
 */
@Service
public interface ParameterRepository extends JpaRepository<Parameter, String>{
    @Query(value = "SELECT * FROM parameters WHERE account_id = ?1", nativeQuery = true)
    public List<Parameter> findAllByAccountId(int id);
    
    @Query(value = "SELECT * FROM parameters WHERE account_id = ?1 AND name = ?2", nativeQuery = true)
    public Parameter findByAccountAndName(int id, String name);
}
