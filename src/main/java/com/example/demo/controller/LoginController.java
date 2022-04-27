package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Utenti;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/login")
public class LoginController {

	private final JdbcTemplate jdbc;
	
	public LoginController(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@PostMapping
	public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpSession session) {
		
		Utenti user = jdbc.queryForObject("SELECT * FROM users WHERE username = ?", new RowMapper<Utenti>() {

			@Override
			public Utenti mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
		}, username);
		
		// TODO check email 
		session.setAttribute("user", user);
		ModelAndView mvc = new ModelAndView("index");
		return mvc;
	}
}
