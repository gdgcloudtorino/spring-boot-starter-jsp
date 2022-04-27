package com.example.demo.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Utenti;

@Controller
@RequestMapping("/utenti")
public class UtentiController {

	private final JdbcTemplate jdbc;
	
	public UtentiController(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView mvc = new ModelAndView("utenti");
		List<Utenti> users = jdbc.query("SELECT * FROM utenti", new RowMapper<Utenti>(){

			@Override
			public Utenti mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utenti u = new Utenti();
				u.setUsername(rs.getString("username"));
				u.setTipologia(rs.getString("tipologia"));
				u.setAttivo(rs.getBoolean("attivo"));
				return u;
			}
			
		});
		mvc.addObject("users", users);
		return mvc;
	}
}
