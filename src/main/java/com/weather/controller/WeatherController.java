package com.weather.controller;

import com.weather.dto.WeatherDTO;
import com.weather.entity.Time;
import com.weather.entity.WeatherData;
import com.weather.model.User;
import com.weather.service.CityService;
import com.weather.service.SecurityService;
import com.weather.service.UserService;
import com.weather.service.WeatherService;
import com.weather.validator.UserValidator;
import com.weather.xml.WeatherXMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private CityService cityService;


    @RequestMapping("/")
    public ModelAndView welcome() throws Exception {
//        GregorianCalendar calen = new GregorianCalendar(2016, 1, 1);
//        LocalDate specificDate = LocalDate.of(2016, Month.JANUARY, 1);
//        LocalDateTime localDateTime = LocalDateTime.of(specificDate, LocalTime.of(0, 0, 0));
//        List<String> clouds = weatherService.getDistinctCloudsName();
//        List<String> winddirection = weatherService.getDistinctWindDirection();
//        List<String> windspeed = weatherService.getDistinctWindSpeed();
//        List<Time> list = new ArrayList<>();
//        Time time ;
//        Random r = new Random();
//        WindDirection wDirection;
//        WindSpeed wSpeed;
//        Humidity humidity;
//        Temperature temperature;
//        Clouds clouds1;
//        int t;
//        for (int i = 0; i < 365; i++) {
//            time = new Time();
//            wDirection = new WindDirection();
//            wSpeed = new WindSpeed();
//            humidity = new Humidity();
//            temperature = new Temperature();
//            clouds1 = new Clouds();
//            localDateTime = localDateTime.plusDays(1);
//            time.setFrom(localDateTime.toString() + ":00");
//            time.setTo(localDateTime.toString() + ":00");
//            t = winddirection.size();
//            wDirection.setName(winddirection.get(r.nextInt(t)));
//            time.setWindDirection(wDirection);
//            t = windspeed.size();
//            wSpeed.setName(windspeed.get(r.nextInt(t)));
//            wSpeed.setMps(String.valueOf(r.nextInt(6)));
//            time.setWindSpeed(wSpeed);
//            humidity.setValue(String.valueOf(r.nextInt(100)));
//            time.setHumidity(humidity);
//            t = r.nextInt(60) - 30;
//            temperature.setMin(String.valueOf(t));
//            temperature.setMax(String.valueOf(t));
//            temperature.setValue(String.valueOf(t));
//            time.setTemperature(temperature);
//            clouds1.setValue(clouds.get(r.nextInt(clouds.size())));
//            clouds1.setAll(String.valueOf(r.nextInt(clouds.size())));
//            time.setClouds(clouds1);
//            System.out.println(time.getFrom()+ " - " + time.getTemperature().getValue());
//            list.add(time);
//
//        }
//
//        weatherService.save(list, cityService.findOrCreateByCityName("Minsk"));
        return new ModelAndView("login");
    }


    @RequestMapping("/login")
    public ModelAndView login() throws Exception {
        return new ModelAndView("index");
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterPage() throws Exception {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("userForm", new User());
        return modelAndView;
    }

    @RequestMapping("/register/new")
    public String registerUser(@ModelAttribute("userForm") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(user);
        return "redirect:/";

    }

    @RequestMapping("/login/weather")
    @ResponseBody
    public List<Time> getWeather(@RequestParam(name = "city", defaultValue = "") String city) {
        List<Time> result = null;
        weatherData = weatherXMLParser.getTimeListByCity(city);
        if (weatherData != null) {
            result = weatherData.getForecast().getTimeList();
            weatherService.save(result, cityService.findOrCreateByCityName(city));
        }

        return result;
    }

    @RequestMapping("/error403")
    public ModelAndView error() throws Exception {

        return new ModelAndView("error403");
    }

    @RequestMapping("/forecast")
    public ModelAndView getForecastPage() throws Exception {
        ModelAndView modelAndView = new ModelAndView("userforecast");
        modelAndView.addObject("forecastForm", new WeatherDTO());
        modelAndView.addObject("clouds", weatherService.getDistinctCloudsName());
        modelAndView.addObject("windSpeeds", weatherService.getDistinctWindSpeed());
        modelAndView.addObject("windDirections", weatherService.getDistinctWindDirection());
        return modelAndView;
    }

    @RequestMapping("/forecast/new")
    public String addForecastByUser(@ModelAttribute("forecastForm") @Valid WeatherDTO forecastForm,
                                    BindingResult bindingResult, Authentication authentication) throws Exception {
        if (bindingResult.hasErrors()) {
            return "userforecast";
        }

        weatherService.saveUserForecast(forecastForm, userService.findByUsername(authentication.getName()),
                cityService.findOrCreateByCityName("Minsk"));
        return "redirect:/login";
    }

    @RequestMapping("avgtempreture")
    public ModelAndView getAVGTempreture(ModelAndView modelAndView) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String from = LocalDateTime.of(LocalDate.of(2016, 1, 1), LocalTime.of(0, 0, 0)).toString()+":00",
                to = LocalDateTime.of(LocalDate.of(2016, 1, 2), LocalTime.of(0, 0, 0)).toString()+":00";
        double d = weatherService.getAVGTempretureFromTo(cityService.findOrCreateByCityName("Minsk"),
                format.parse(from), format.parse(to));
        modelAndView.setViewName("index");
        modelAndView.addObject("result", d);
        return modelAndView;
    }
}
