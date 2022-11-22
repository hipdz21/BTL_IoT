/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BTL_IoT_HTTC.dao;

import com.example.BTL_IoT_HTTC.model.DataSensor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


/**
 *
 * @author dovan
 */
@Service
public interface DataSensorRepository extends JpaRepository<DataSensor, Integer>{
    @Query(value = "SELECT * FROM data_sensor WHERE DAY(date) = DAY(NOW()) AND account_id = ?1", nativeQuery = true)
    public List<DataSensor> getAllDataSensorsByDayAndAccount(int account_id);
}
