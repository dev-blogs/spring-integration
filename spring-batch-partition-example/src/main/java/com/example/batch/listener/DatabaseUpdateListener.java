package com.example.batch.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.model.Product;

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
			new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString("id") + ", " 
							+ rs.getString("name") + ", "
							+ rs.getString("description") + ", "
							+ rs.getBigDecimal("price");
				}
			}
		);
		for (String line : result) {
			System.out.println(line);
		}
		System.out.println(".........................job listener stendart..............................");
	}
}