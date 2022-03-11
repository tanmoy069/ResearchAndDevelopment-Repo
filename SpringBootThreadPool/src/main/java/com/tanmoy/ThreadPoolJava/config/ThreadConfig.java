package com.tanmoy.ThreadPoolJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * {@link ThreadPoolTaskExecutor} {@link Configuration} class.
 * {@link ThreadPoolTaskExecutor} {@link Configuration} has been configured in
 * this class.
 * 
 * @author tanmoy.tushar
 * @since Mar 12, 2022
 */
@Configuration
public class ThreadConfig {

	@Bean
	public TaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(5);
		executor.setThreadNamePrefix("executor_thread");
		executor.initialize();
		return executor;
	}

}
