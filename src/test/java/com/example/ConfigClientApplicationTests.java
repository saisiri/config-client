package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigClientApplicationTests {

	@Autowired
	private ConfigurableEnvironment environment;

	@Autowired
	private MessageRestController controller;

	@Autowired
	private ContextRefresher refresher;

	@Test
	public void contextLoads() {
		assertThat(controller.getMessage()).isNotEqualTo("Hello test");
		EnvironmentTestUtils.addEnvironment(environment, "key1:Hello test");
		assertThat(controller.getMessage()).isNotEqualTo("Hello test");
		refresher.refresh();
		assertThat(controller.getMessage()).isEqualTo("Hello test");
	}


}
