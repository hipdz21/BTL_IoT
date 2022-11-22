/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.api;

import com.example.BTL_IoT_HTTC.controller.RainForecast;
import com.example.BTL_IoT_HTTC.dao.AccountRepository;
import com.example.BTL_IoT_HTTC.dao.ClockRepository;
import com.example.BTL_IoT_HTTC.dao.DataSensorRepository;
import com.example.BTL_IoT_HTTC.model.Parameter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.BTL_IoT_HTTC.dao.ParameterRepository;
import com.example.BTL_IoT_HTTC.model.Account;
import com.example.BTL_IoT_HTTC.model.Clock;
import com.example.BTL_IoT_HTTC.model.DataForecast;
import com.example.BTL_IoT_HTTC.model.DataSensor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dovan
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    DataSensorRepository dataSensorRepository;
    
    @Autowired
    ClockRepository clockRepository;
    
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/get-pump")
    public int getPum(@RequestHeader("account_id") int account_id) {
        try {
            Parameter parameter = parameterRepository.findByAccountAndName(account_id, "Sensor.Parameter3");
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + parameter.getId() + "/Value")
                    .header("EIOT-AuthToken", parameter.getAccount().getToken())
                    .asString();
            String responeJSONString = response.getBody().toString();
            Parameter p = gson.fromJson(responeJSONString, Parameter.class);
            return (int) (float) p.getValue();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi lay trang thai bom!");
        }
        return 0;
    }

    @GetMapping("/get-treshold-hum")
    public int getTresholdSoilHuminity(@RequestHeader("account_id") int account_id) {
        try {
            Parameter parameter = parameterRepository.findByAccountAndName(account_id, "Sensor.Parameter1");
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + parameter.getId() + "/Value")
                    .header("EIOT-AuthToken", parameter.getAccount().getToken())
                    .asString();
            String responeJSONString = response.getBody().toString();
            Parameter p = gson.fromJson(responeJSONString, Parameter.class);
            return (int) (float) p.getValue();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi lay nguong do am dat!");
        }
        return 0;
    }

    @GetMapping("/get-auto-mode")
    public int getAutoMode(@RequestHeader("account_id") int account_id) {
        try {
            Parameter parameter = parameterRepository.findByAccountAndName(account_id, "Sensor.Parameter2");
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + parameter.getId() + "/Value")
                    .header("EIOT-AuthToken", parameter.getAccount().getToken())
                    .asString();
            String responeJSONString = response.getBody().toString();
            Parameter p = gson.fromJson(responeJSONString, Parameter.class);
            return (int) (float) p.getValue();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi lay trang thai che do auto!");
        }
        return 0;
    }

    @GetMapping("/get-soil-hum")
    public int getSoilHuminity(@RequestHeader("account_id") int account_id) {
        try {
            Parameter parameter = parameterRepository.findByAccountAndName(account_id, "Sensor.Parameter4");
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + parameter.getId() + "/Value")
                    .header("EIOT-AuthToken", parameter.getAccount().getToken())
                    .asString();
            String responeJSONString = response.getBody().toString();
            Parameter p = gson.fromJson(responeJSONString, Parameter.class);
            return (int) (float) p.getValue();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi lay trang thai che do auto!");
        }
        return 0;
    }

    @GetMapping("/get-hum")
    public float getHuminity(@RequestHeader("account_id") int account_id) {
        try {
            Parameter parameter = parameterRepository.findByAccountAndName(account_id, "Sensor.Parameter5");
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + parameter.getId() + "/Value")
                    .header("EIOT-AuthToken", parameter.getAccount().getToken())
                    .asString();
            String responeJSONString = response.getBody().toString();
            Parameter p = gson.fromJson(responeJSONString, Parameter.class);
            return (float) p.getValue();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi lay trang thai che do auto!");
        }
        return 0;
    }

    @GetMapping("/get-tem")
    public float getTemperature(@RequestHeader("account_id") int account_id) {
        try {
            Parameter parameter = parameterRepository.findByAccountAndName(account_id, "Sensor.Parameter6");
            Gson gson = new Gson();
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/" + parameter.getId() + "/Value")
                    .header("EIOT-AuthToken", parameter.getAccount().getToken())
                    .asString();
            String responeJSONString = response.getBody().toString();
            Parameter p = gson.fromJson(responeJSONString, Parameter.class);
            return (float) p.getValue();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi lay trang thai che do auto!");
        }
        return 0;
    }

    @GetMapping("/get-data")
    public List<Data> getDataSensorByDate(@RequestHeader("account_id") int account_id) {
        try {
            List<DataSensor> ldDataSensors = dataSensorRepository.getAllDataSensorsByDayAndAccount(account_id);
            List<Data> ldDatas = new ArrayList<>();
            for (DataSensor d : ldDataSensors) {
                ldDatas.add(new Data(d.getDate(), d.getSoilHuminity(), d.getHuminity(), d.getTemperature()));
            }
            return ldDatas;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi lay du lieu do am dat, do am, nhiet do!");
        }
        return null;
    }
    
    @GetMapping("/get-timer")
    public List<Clock> getTimer(@RequestHeader("account_id") int account_id){
        try {
            List<Clock> lClocks = new ArrayList<>();
            lClocks = clockRepository.findAllClockByAcount(account_id);
            for(Clock c: lClocks){
                c.setAccount(null);
            }
            return lClocks;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi lay thong tin hen gio");
        }
        return null;
    }
    @GetMapping("/get-rain-forecast")
    public DataForecast getRainForecast(@RequestHeader("account_id") int account_id){
        try {
            return RainForecast.Forecast(getHuminity(account_id), getTemperature(account_id));
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi du bao mua");
        }
        return null;
    }

    @PostMapping("/set-parameter")
    public boolean setParameter(@RequestHeader("account_id") int account_id, @RequestBody Parameter parameter) {
        try {
            Parameter p = parameterRepository.findByAccountAndName(account_id, parameter.getName());
            Unirest.setTimeouts(0, 0);
            Gson gson = new Gson();
            List<Para> lp = new ArrayList<>();
            lp.add(new Para(p.getId(), (int)(float)parameter.getValue()));
            HttpResponse<String> response = Unirest.post("http://cloud.iot-playground.com:40404/RestApi/v1.0/Parameter/Values")
                    .header("EIOT-AuthToken", p.getAccount().getToken())
                    .header("Content-Type", "application/json")
                    .body(gson.toJson(lp))
                    .asString();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi cap nhat du lieu");
        }
        return false;
    }
    
    @PostMapping("/set-timer")
    public boolean setTimer(@RequestHeader("account_id") int account_id, @RequestBody Clock clock){
        try {
            Account account = accountRepository.findById(account_id).get();
            clock.setAccount(account);
            clockRepository.save(clock);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi cap nhat thoi gian hen gio!");
        }
        return false;
    }
    
    @DeleteMapping("/del-timer")
    public boolean deleteTimer(@RequestParam int id){
        try {
            clockRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi xoa hen gio tuoi cay!");
        }
        return false;
    }
}

class Data {

    private Date date;
    private float soilHuminity;
    private float huminity;
    private float temperature;

    public Data() {
    }

    public Data(Date date, float soilHuminity, float huminity, float temperature) {
        this.date = date;
        this.soilHuminity = soilHuminity;
        this.huminity = huminity;
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getSoilHuminity() {
        return soilHuminity;
    }

    public void setSoilHuminity(float soilHuminity) {
        this.soilHuminity = soilHuminity;
    }

    public float getHuminity() {
        return huminity;
    }

    public void setHuminity(float huminity) {
        this.huminity = huminity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
};
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