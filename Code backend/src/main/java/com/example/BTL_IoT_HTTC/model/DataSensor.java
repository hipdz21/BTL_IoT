/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.model;

import java.time.Instant;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author dovan
 */
@Entity
@Data
public class DataSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private float soilHuminity;
    private float huminity;
    private float temperature;
    @ManyToOne
    private Account account;

    public DataSensor() {
        this.date = new java.util.Date();
    }

    public DataSensor(int id, Date date, float soilHuminity, float huminity, float temperature, Account account) {
        this.id = id;
        this.date = date;
        this.soilHuminity = soilHuminity;
        this.huminity = huminity;
        this.temperature = temperature;
        this.account = account;
    }
    
    
}
