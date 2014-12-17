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
public class FriendController {
	private static FriendController instance = null;
	private static final Logger logger = Logger
			.getLogger(FriendController.class);

	private FriendController() {
	}

	public static FriendController getInstance() {
		if (instance == null) {
			instance = new FriendController();
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
