package com.weather.controller;

import com.weather.entity.Time;
import com.weather.entity.WeatherData;
import com.weather.model.User;
import com.weather.service.CityService;
import com.weather.service.SecurityService;
import com.weather.service.UserService;
import com.weather.service.WeatherService;
import com.weather.service.impl.WeatherServiceImpl;
import com.weather.validator.UserValidator;
import com.weather.xml.WeatherXMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String registerUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPassword());
        return "redirect:/login";
    }

    @RequestMapping("/login/weather")
    @ResponseBody
    public List<Time> getWeather(@RequestParam(name = "city", defaultValue = "") String city) {
        List<Time> result = null;
        weatherData = weatherXMLParser.getTimeListByCity(city);
        if (weatherData != null)
        {
            result = weatherData.getForecast().getTimeList();
            weatherService.save(result, cityService.findByCityName(city));
        }

        return result;
    }

    @RequestMapping("/error403")
    public ModelAndView error() throws Exception {

        return new ModelAndView("error403");
    }
}