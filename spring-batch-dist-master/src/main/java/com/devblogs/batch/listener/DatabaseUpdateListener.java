package com.devblogs.batch.listener;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseUpdateListener implements JobExecutionListener {
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println(".........................job listener start..............................");
		List<String> result = jdbcTemplate.query("SELECT id, name, description, price FROM products", 
				(ResultSet rs, int rowNum) ->
						rs.getString("id") + ", " 
						+ rs.getString("name") + ", "
						+ rs.getString("description") + ", "
						+ rs.getBigDecimal("price"));
		for (String line : result) {
			System.out.println(line);
		}
		System.out.println(".........................job listener stendart..............................");
	}
}