package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"/spring/import-products-job-context.xml", 
		"/spring/test-context.xml" 
	})
public class ImportProductsIntegrationTest {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//@Before
	public void setUp() throws Exception {
		jdbcTemplate.update("delete from products");
		jdbcTemplate.update("insert into products " + "(id,name,description,price) values(?,?,?,?)", "200",
				"Nokia 2610 Phone", "", 102.23);
	}

	@Test
	public void importProducts() throws Exception {
		//int initial = jdbcTemplate.queryForInt("select count(1) from products");
		jobLauncher.run(job, new JobParametersBuilder()
				.addString("inputResource", "classpath:/input/products.zip")
				.addString("targetDirectory", "./resources/partition/input")
				.addLong("timestamp", System.currentTimeMillis())
				.toJobParameters());
		int nbOfNewProducts = 25;
		assertEquals(nbOfNewProducts, jdbcTemplate.queryForInt("select count(1) from products"));
	}
}