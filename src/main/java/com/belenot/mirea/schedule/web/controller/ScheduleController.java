package com.belenot.mirea.schedule.web.controller;

import java.util.List;
import java.util.Map;

import com.belenot.mirea.schedule.domain.Schedule;
import com.belenot.mirea.schedule.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( "/api" )
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    
    @ResponseBody
    @GetMapping( path = "/{groupName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Schedule getSchedule(@PathVariable String groupName) {
	return scheduleService.getSchedule(groupName.toUpperCase());
    }

    @ResponseBody
    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public List<String> hello() {
	return scheduleService.getGroupNames();
    }

    @ResponseBody
    @GetMapping( "/init" )
    public Map<String, Boolean>  init() {
	return scheduleService.saveAllSchedules();
    }
    
    
}
