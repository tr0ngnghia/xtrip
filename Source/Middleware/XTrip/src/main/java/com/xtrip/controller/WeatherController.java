package com.xtrip.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.xtrip.model.bean.BUser;

/**
 * @author longnh
 */
public class WeatherController {
	private static WeatherController instance = null;
	private static final Logger logger = Logger
			.getLogger(WeatherController.class);

	private WeatherController() {
	}

	public static WeatherController getInstance() {
		if (instance == null) {
			instance = new WeatherController();
		}
		return instance;
	}

	public ModelAndView process(Locale locale, HttpServletRequest req,
			HttpServletResponse resp) {
		ModelAndView modelAndView = new ModelAndView("home");
		try {
			String id = req.getParameter("id");
			
			

			
		} catch (Exception ex) {
			logger.error("process", ex);
		}
		return modelAndView;
	}

	private void APISuggest(BUser user) {
		
	}
}
