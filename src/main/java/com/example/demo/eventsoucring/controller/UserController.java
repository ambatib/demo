package com.example.demo.eventsoucring.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Employee;
import com.example.demo.mongo.User;
import com.example.demo.mongo.UserDAO;
import com.example.demo.mongo.UserRepository;

@RestController
public class UserController {

	private static final Logger LOG = Logger.getLogger(UserController.class.getName());

	private final UserRepository userRepository;
	
	@Autowired
	UserDAO userDAO;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		LOG.info("Getting all users.");
		return userRepository.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}." + userId);
		return userRepository.findById(userId).get();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
		return userRepository.save(user);
	}

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		LOG.info(" Get the user settings for given user.");
		Optional<User> user = userRepository.findById(userId);
		if (user != null) {
			return userDAO.getAllUserSettings(userId);
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		Optional<User> user = userRepository.findById(userId);
		if (user != null) {
			return userDAO.getUserSetting(userId,key);
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		Optional<User> user = userRepository.findById(userId);
		if (user != null) {
			user.get().getUserSettings().put(key, value);
			userRepository.save(user.get());
			return "Key added";
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/hello", produces = "application/json", method = RequestMethod.GET)
	public String sayHello() {
		LOG.info("sayHello():::");
		Calendar c = Calendar.getInstance();
		return "{\r\n" + "   \"DATE\": \" " + LocalDate.now() + "\",\r\n" + "   \"Month\": \" "
				+ c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + "\"\r\n" + "}";
	}

	@RequestMapping(value = "/employees", produces = "application/json", method = RequestMethod.GET)
	public List<Employee> getEmployees() {
		LOG.info("getEmployees():::");
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("Balu", "Ambati"));
		employees.add(new Employee("Balu", "Ambati1"));
		return employees.stream().filter(x -> "Balu".equals(x.getFirstName())).collect(Collectors.toList());
	}

}
