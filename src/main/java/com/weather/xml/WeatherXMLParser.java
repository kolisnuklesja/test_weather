package com.weather.xml;

import com.weather.entity.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Tanya on 25.01.2017.
 */
@Service
public class WeatherXMLParser {

    @Autowired
    private WeatherData weatherData;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherData getTimeListByCity(String city) {
        StringBuilder urlValue = new StringBuilder("http://api.openweathermap.org/data/2.5/forecast?q=&mode=xml&appid=00d5b48712bf948fd69be12283e54674");
        urlValue.insert(urlValue.lastIndexOf("q=") + 2, city);
        try {
            weatherData = getWeatherData(urlValue.toString());
            return weatherData;
        } catch (HttpMessageNotReadableException ex) {
            return null;
        }
    }

    public WeatherData getWeatherData(String url) {
        return restTemplate.getForObject(url, WeatherData.class);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}