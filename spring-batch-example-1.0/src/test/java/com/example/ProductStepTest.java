package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"/spring/import-products-job-context.xml", 
		"/spring/test-context.xml" 
	})
public class ProductStepTest {
	@Autowired
	private Job job;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void testIntegration() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("inputResource", "classpath:/input/products.zip")
				.addString("targetDirectory", "./target/importproductsbatch/")
				.addString("targetFile", "products.txt")
				.addLong("timestamp", System.currentTimeMillis())
				.toJobParameters();
		
		jobLauncher.run(job, jobParameters);
	
		assertEquals(26, jdbcTemplate.queryForInt("SELECT count(*) FROM products"));
	}
}