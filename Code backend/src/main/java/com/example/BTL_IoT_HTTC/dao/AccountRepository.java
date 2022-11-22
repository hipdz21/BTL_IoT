/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.dao;

import com.example.BTL_IoT_HTTC.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author dovan
 */
@Service
public interface AccountRepository extends JpaRepository<Account, Integer>{
    @Query(value = "SELECT * FROM account WHERE username = ?1", nativeQuery = true)
    public Account findAccountByUsername(String username);
}
