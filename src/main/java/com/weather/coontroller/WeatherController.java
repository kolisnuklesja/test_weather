package com.weather.controller;

import com.weather.entity.Time;
import com.weather.entity.WeatherData;
import com.weather.xml.WeatherXMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Tanya on 24.01.2017.
 */

@Controller
public class WeatherController {

    @Autowired
    private WeatherXMLParser weatherXMLParser;

    @Autowired
    private WeatherData weatherData;

    @RequestMapping("/")
    public ModelAndView welcome() throws Exception {

        return new ModelAndView("login");
    }


    @RequestMapping("/login")
    public ModelAndView login() throws Exception {

        return new ModelAndView("index");
    }

    @RequestMapping("/login/weather")
    @ResponseBody
    public List<Time> getWeather(@RequestParam(name = "city", defaultValue = "") String city) {
        List<Time> result = null;
        weatherData = weatherXMLParser.getTimeListByCity(city);
        if (weatherData != null)
            result = weatherData.getForecast().getTimeList();
        return result;
    }

    @RequestMapping("/error403")
    public ModelAndView error() throws Exception {

        return new ModelAndView("error403");
    }
}