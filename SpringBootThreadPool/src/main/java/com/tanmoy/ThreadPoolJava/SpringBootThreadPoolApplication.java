package com.tanmoy.ThreadPoolJava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import com.tanmoy.ThreadPoolJava.model.Task;
import com.tanmoy.ThreadPoolJava.service.TaskService;

/**
 * Spring boot thread pool executor example.
 * 
 * @author tanmoy.tushar
 * @since Mar 12, 2022
 */
@SpringBootApplication
@EnableAsync
public class SpringBootThreadPoolApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ApplicationContext context = SpringApplication.run(SpringBootThreadPoolApplication.class, args);
		TaskService taskService = context.getBean(TaskService.class);
		List<Future<Task>> taskList = getFutureList(taskService, 10);
		processFutureList(taskList);
	}

	/**
	 * Get tasks from {@link TaskService#getDummyTask()}, which returns
	 * {@link Future<Task>} in every request as async process.
	 * 
	 * @param taskService
	 * @param totalThread
	 * @return
	 */
	private static List<Future<Task>> getFutureList(TaskService taskService, int totalThread) {
		List<Future<Task>> taskList = new ArrayList<>();
		for (int i = 0; i < totalThread; i++) {
			taskList.add(taskService.getDummyTask());
		}
		return taskList;
	}

	/**
	 * Processing the {@link Future} list when {@link Async} process returns value
	 * 
	 * @param taskList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private static void processFutureList(List<Future<Task>> taskList) throws InterruptedException, ExecutionException {
		Thread.sleep(1000);
		System.out.println("Start processing list of Future<Task>");
		for (Future<Task> future : taskList) {
			System.out.println(future.get().toString());
		}
	}

}
