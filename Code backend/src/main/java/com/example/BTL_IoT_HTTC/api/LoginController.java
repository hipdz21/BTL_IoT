/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.api;

import com.example.BTL_IoT_HTTC.dao.AccountRepository;
import com.example.BTL_IoT_HTTC.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dovan
 */
@CrossOrigin
@RestController
public class LoginController {
    
    @Autowired
    AccountRepository accountRepository;
    
    @PostMapping("/login")
    public info login(@RequestBody Account account){
        String code = "ERROR";
        String value = "Tài khoản không tồn tại!";
        try {
            Account a = accountRepository.findAccountByUsername(account.getUsername());
            if(a != null){
                value = "Mật khẩu không chính xác!";
                if(a.getPassword().equals(account.getPassword())){
                    code = "OK";
                    value = a.getId()+ "";
                }
            }
        } catch (Exception e) {
        }
        return new info(code, value);
    }
}
class info{
    private String code;
    private String value;

    public info() {
    }

    public info(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    } 
};