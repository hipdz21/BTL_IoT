/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author dovan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataRainForecast {
    private int month;
    private int day;
    private float huminity;
    private float temperature;
    private int label;
}
