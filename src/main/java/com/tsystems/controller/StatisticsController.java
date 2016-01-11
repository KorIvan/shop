package com.tsystems.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tsystems.service.StatisticsService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping(value = "/")
	public ModelAndView getStatistics() {
		ModelAndView model = new ModelAndView("statistics");
		Calendar week = Calendar.getInstance();
		week.clear(Calendar.MINUTE);
		week.clear(Calendar.SECOND);
		week.clear(Calendar.MILLISECOND);
		week.set(Calendar.HOUR_OF_DAY, 0);
		week.set(2015, Calendar.DECEMBER, 2);
		System.out.println("Week now is " + week.getTime());
		
		model.addObject("stats", statisticsService.calculateWeekIncome(week));
		model.addObject("totalIncome",statisticsService.gatherTotalIncome());
		model.addObject("topClients", statisticsService.getTop10Clients(10));
		model.addObject("topProducts", statisticsService.getTop10Products(10));
		return model;
	}

	@RequestMapping(value = "/topClients")
	public ModelAndView getTopClients() {
		ModelAndView model = new ModelAndView("statisticsTopClients");
		model.addObject("topClients", statisticsService.getTop10Clients(10));
		return model;
	}

	/*
	 * @RequestMapping(value = "/", method = RequestMethod.POST) public
	 * ModelAndView postStatistics() { ModelAndView model = new
	 * ModelAndView("statistics"); System.out.println("POST METHOS");
	 * model.addObject("stats", "lol"); return model; }
	 */
	@RequestMapping(value = "/perWeek", method = RequestMethod.GET)
	public ModelAndView getPerWeek() {
		ModelAndView model = new ModelAndView("statistics");
		model.addObject("income", "ll");
		return model;
	}

	@RequestMapping(value = "/perWeek", method = RequestMethod.POST)
	 public ModelAndView getWeekStatistics(@RequestParam("from") String
	 from,@RequestParam("to") String to ){
//	public ModelAndView getWeekStatistics(HttpServletRequest request) {
//		String from = request.getAttribute("from").toString();
//		String to = request.getAttribute("to").toString();
		ModelAndView model = new ModelAndView("statistics");
		System.out.println("POST sent");
		model.addObject("income", statisticsService.calculateWeekIncome(Date.valueOf(from), Date.valueOf(to)));
		return model;
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
