package com.xtrip.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author longnh
 */
@Controller
public class HomeController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, HttpServletRequest req,
			HttpServletResponse resp) {
		return PlanController.getInstance().process(locale, req, resp);
	}


	@RequestMapping(value = "/plan", method = RequestMethod.GET)
	public ModelAndView plan(Locale locale, HttpServletRequest req,
			HttpServletResponse resp) {
		return PlanController.getInstance().process(locale, req, resp);
	}
	
	@RequestMapping(value = "/friend", method = RequestMethod.GET)
	public ModelAndView friend(Locale locale, HttpServletRequest req,
			HttpServletResponse resp) {
		return FriendController.getInstance().process(locale, req, resp);
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView map(Locale locale, HttpServletRequest req,
			HttpServletResponse resp) {
		return MapController.getInstance().process(locale, req, resp);
	}
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public ModelAndView weather(Locale locale, HttpServletRequest req,
			HttpServletResponse resp) {
		return WeatherController.getInstance().process(locale, req, resp);
	}
	
	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public ModelAndView schedule(Locale locale, HttpServletRequest req,
			HttpServletResponse resp) {
		return ScheduleController.getInstance().process(locale, req, resp);
	}
	
	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public ModelAndView notification(Locale locale, HttpServletRequest req,
			HttpServletResponse resp) {
		return NotificationController.getInstance().process(locale, req, resp);
	}
	
	@Override
	public void run() {
		logger.info("XTrip start ...");
	}

}
