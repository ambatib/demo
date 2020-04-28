package com.example.demp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping(value="/hello",produces="application/json", method = RequestMethod.GET)
	public String sayHello() {
		System.out.println("Hello World");
	    Calendar c = Calendar.getInstance();
		return "{\r\n" + 
				"   \"DATE\": \" "+new Date().toLocaleString()+ "\",\r\n" + 
				"   \"Month\": \" "+c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH )+ "\"\r\n" + 
				"}";
	}
	
	@RequestMapping(value="/employees",produces="application/json", method = RequestMethod.GET)
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("Balu","Ambati"));
		employees.add(new Employee("Balu","Ambati1"));
		return employees.stream().filter(x -> "Balu".equals(x.getFirstName())).collect(Collectors.toList());
	}

}
