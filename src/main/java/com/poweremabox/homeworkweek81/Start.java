package com.poweremabox.homeworkweek81;

import com.poweremabox.homeworkweek81.model.Temperature;
import com.poweremabox.homeworkweek81.service.WeatherRepo;
import com.poweremabox.homeworkweek81.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private WeatherRepo weatherRepo;
    private WeatherService weatherService;

    @Autowired
    public Start(WeatherRepo weatherRepo, WeatherService weatherService) {
        this.weatherRepo = weatherRepo;
        this.weatherService = weatherService;
    }

    @Scheduled(fixedDelay = 1500000L)
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String country = weatherService.getCountry();
        String city = weatherService.getCity();
        String time = weatherService.getTime();
        String temp = weatherService.getTemperature();
        Temperature temperature = new Temperature(country, city, time, temp);
        weatherRepo.save(temperature);

    }

}
