package com.tsystems.controller;

import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tsystems.model.Person;
import com.tsystems.model.Statistics;
import com.tsystems.service.StatisticsService;

@RestController("/statisticsREST")
public class StatisticsControllerRest {
	private static Logger log = Logger.getLogger(StatisticsControllerRest.class.getName());
	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping(value = "/")
	public Statistics getStatistics() {
		ModelAndView model = new ModelAndView("statistics");
		Calendar week = Calendar.getInstance();
		Statistics stat=new Statistics();
		stat.setTopProducts(statisticsService.getTopProducts(10));
		stat.setTopClients(statisticsService.getTopClients(10));
		stat.setIncomeTotal(statisticsService.gatherTotalIncome());
		model.addObject("stats", statisticsService.calculateWeekIncome(week));
		return stat;
	}

	@RequestMapping(value = "/topClients",method=RequestMethod.GET)
	public Map<Float, Person> getTopClients(@RequestParam(required=false,value = "topSize", defaultValue = "10") String topSize) {
		try {
			return statisticsService.getTopClients(Integer.parseInt(topSize));
		} catch (NumberFormatException e) {
			log.error("Unappropriate format of topSize");
			return null;
		}
	}

	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView getTest() {
		ModelAndView model = new ModelAndView("test1");
		System.out.println("GET");
		return model;
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ModelAndView postTest(@ModelAttribute("test1") String test1, BindingResult result) {
		ModelAndView model = new ModelAndView("test1");
		System.out.println("POST " + test1);
		model.addObject("test2", test1);
		return model;
	}

}
