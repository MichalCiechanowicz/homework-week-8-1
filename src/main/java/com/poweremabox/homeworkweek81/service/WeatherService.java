package com.poweremabox.homeworkweek81.service;

import com.poweremabox.homeworkweek81.model.WeahterFacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@ConfigurationProperties(prefix = "weather")
public class WeatherService {

    private RestTemplate restTemplate;
    private String key;

    @Autowired
    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    public WeahterFacts connectionWithWeatherApi() {
        String link = "http://api.openweathermap.org/data/2.5/weather?q=lodz&APPID=" + key;
        return restTemplate.getForObject(link, WeahterFacts.class);
    }

    public String getTemperature() {
        return String.format("%.2f", connectionWithWeatherApi().getMain().getTemp() - 273.15);
    }

    public String getCountry() {
        return connectionWithWeatherApi().getSys().getCountry();
    }

    public String getCity() {
        return connectionWithWeatherApi().getName();
    }

    public String getTime() {
        Integer time = connectionWithWeatherApi().getDt();
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                .format(new Date((long)time * 1000));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
