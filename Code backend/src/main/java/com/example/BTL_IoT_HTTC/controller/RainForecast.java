/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.controller;

import com.example.BTL_IoT_HTTC.model.DataForecast;
import com.example.BTL_IoT_HTTC.model.DataRainForecast;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author dovan
 */
public class RainForecast {

    public static List<DataRainForecast> listDataRainForecasts;

    public RainForecast() {
        String csvFile = "src\\main\\java\\com\\example\\BTL_IoT_HTTC\\data\\DataRainForecast.csv";
        try {
            listDataRainForecasts = new ArrayList<>();
            Reader reader = new FileReader(csvFile);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.get(1).equals("MO")) {
                    continue;
                }
                listDataRainForecasts.add(new DataRainForecast(Integer.parseInt(csvRecord.get(1)), Integer.parseInt(csvRecord.get(2)), Float.parseFloat(csvRecord.get(3)), Float.parseFloat(csvRecord.get(4)), Integer.parseInt(csvRecord.get(5))));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static DataForecast Forecast(float hum, float tem) {
        Date date = new Date();
        String[] enumLable = {"Không mưa", "Mưa nhỏ", "Mưa vừa", "Mưa lớn", "Mưa rất lớn"};
        float max = 0;
        int lb = 0;
        float t = 0;
        for (DataRainForecast drf : listDataRainForecasts) {
            t = (float) ((float) (0.25 * div(date.getMonth(), drf.getMonth()) + 0.1 * div(date.getDay(), drf.getDay()) + div(hum, drf.getHuminity()) + div(tem, drf.getTemperature())) / (0.25 + 0.1 + 1 + 1));
            if (max < t) {
                max = t;
                lb = drf.getLabel();
            }
        }
        return new DataForecast(enumLable[lb], t * 100);
    }

    private static float div(float a, float b) {
        if (a < b) {
            return a / b;
        }
        return b / a;
    }
}
