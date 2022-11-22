/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.controller;

import com.example.BTL_IoT_HTTC.dao.AccountRepository;
import com.example.BTL_IoT_HTTC.dao.ClockRepository;
import com.example.BTL_IoT_HTTC.dao.DataSensorRepository;
import com.example.BTL_IoT_HTTC.dao.ParameterRepository;
import com.example.BTL_IoT_HTTC.model.Account;
import com.example.BTL_IoT_HTTC.model.Clock;

import com.example.BTL_IoT_HTTC.model.DataSensor;
import com.example.BTL_IoT_HTTC.model.Parameter;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author dovan
 */
@Configuration
@EnableScheduling
public class SaveData {

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    DataSensorRepository dataSensorRepository;

    @Autowired
    ClockRepository clockRepository;

    @Scheduled(cron = "0 * * * * *")//Chạy vào 0s của mỗi phút
    public void saveDataToDatabase() {
        try {
            System.out.println("Cap nhat du lieu do am, do am dat, nhiet do!");
            List<Account> listAccounts = new ArrayList<>();
            listAccounts = accountRepository.findAll();
            for (Account i : listAccounts) {
                List<Parameter> parameters = new ArrayList<>();
                parameters = parameterRepository.findAllByAccountId(i.getId());
                DataSensor dataSensor = new DataSensor();
                dataSensor.setAccount(i);
                Gson gson = new Gson();
                Parameter parameter = new Parameter();
                for (Parameter p : parameters) {
                    Unirest.setTimeouts(0, 0);
                    if (p.getName().equals("Sensor.Parameter4")) {
                        HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + p.getId() + "/Value")
                                .header("EIOT-AuthToken", i.getToken())
                                .asString();
                        String responeJSONString = response.getBody().toString();
                        parameter = gson.fromJson(responeJSONString, Parameter.class);
                        dataSensor.setSoilHuminity(parameter.getValue());
                    }
                    if (p.getName().equals("Sensor.Parameter5")) {
                        HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + p.getId() + "/Value")
                                .header("EIOT-AuthToken", i.getToken())
                                .asString();
                        String responeJSONString = response.getBody().toString();
                        parameter = gson.fromJson(responeJSONString, Parameter.class);
                        dataSensor.setHuminity(parameter.getValue());
                    }
                    if (p.getName().equals("Sensor.Parameter6")) {
                        HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + p.getId() + "/Value")
                                .header("EIOT-AuthToken", i.getToken())
                                .asString();
                        String responeJSONString = response.getBody().toString();
                        parameter = gson.fromJson(responeJSONString, Parameter.class);
                        dataSensor.setTemperature(parameter.getValue());
                    }
                }
                dataSensorRepository.save(dataSensor);
                System.out.println("Cap nhat du lieu do am, do am dat, nhiet do thanh cong!");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi ap nhat du lieu do am, do am dat, nhiet do!");
        }

    }

    @Scheduled(cron = "0 * * * * *")//Chạy vào 0s của mỗi phút
    public void checkTime() {
        try {
            System.out.println("Kiem tra gio de tuoi cay!");
            List<Clock> listClock = new ArrayList<>();
            listClock = clockRepository.findAll();
            Gson gson = new Gson();
            for (Clock c : listClock) {
                long now = System.currentTimeMillis();
                Time timeNow = new Time(now);
                Date dayNow = new Date(now);
                if (c.isRepeats() || c.getDay().toString().equals(dayNow.toString())) {
                    if (c.getHour().getHours() == timeNow.getHours() && c.getHour().getMinutes()== timeNow.getMinutes()) {
                        Parameter parameter = parameterRepository.findByAccountAndName(c.getAccount().getId(), "Sensor.Parameter3");
                        List<Para> lPr = new ArrayList<>();
                        lPr.add(new Para(parameter.getId(), 1));
                        Unirest.setTimeouts(0, 0);
                        HttpResponse<String> response = Unirest.post("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/Values")
                                .header("EIOT-AuthToken", c.getAccount().getToken())
                                .header("Content-Type", "application/json")
                                .body(gson.toJson(lPr))
                                .asString();
                        System.out.println("Tuoi cay cua account " + c.getAccount().getId() + "  thanh cong!");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi tuoi cay theo gio da dat!");
        }
    }
}
class Para{
    private String Id;
    private int Value;

    public Para() {
    }

    public Para(String Id, int Value) {
        this.Id = Id;
        this.Value = Value;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int Value) {
        this.Value = Value;
    }
};
