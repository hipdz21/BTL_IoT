package com.example.BTL_IoT_HTTC;

import com.example.BTL_IoT_HTTC.controller.RainForecast;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BtlIoTHttcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtlIoTHttcApplication.class, args);
                RainForecast rf = new RainForecast();
	}
}
