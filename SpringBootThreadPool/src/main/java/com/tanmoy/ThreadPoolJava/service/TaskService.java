package com.tanmoy.ThreadPoolJava.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.tanmoy.ThreadPoolJava.model.Task;

/**
 * {@link TaskService} class to get Dummy {@link Task}
 * 
 * @author tanmoy.tushar
 * @since Mar 12, 2022
 *
 */
@Service
public class TaskService {

	@Async
	public Future<Task> getDummyTask() {
		try {
			System.out.println("Current thread: " + Thread.currentThread().getName());
			Thread.sleep(5000);
			int id = getRandmonNumber();
			Task task = new Task(id, "Random Task - " + id, "Dummy", "Test getting random task with random id");
			return new AsyncResult<Task>(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private int getRandmonNumber() {
		double random = Math.random();
		return (int) (random * 1000);
	}

}
