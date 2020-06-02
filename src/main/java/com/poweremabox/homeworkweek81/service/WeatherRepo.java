package com.poweremabox.homeworkweek81.service;

import com.poweremabox.homeworkweek81.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<Temperature,Long> {



}
