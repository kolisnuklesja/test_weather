package com.weather;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Tanya on 24.01.2017.
 */

@Controller
public class WeatherController {

    @RequestMapping("/")
    public ModelAndView welcome() {
        return new ModelAndView("index");
    }


}
