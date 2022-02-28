package com.tanmoy.JerseyWebApiRnd.controller;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.tanmoy.JerseyWebApiRnd.model.User;
import com.tanmoy.JerseyWebApiRnd.model.UserList;
import com.tanmoy.JerseyWebApiRnd.service.UserService;

/**
 * Controller class of Jersey
 * 
 * @author tanmoy.tushar
 * @since Feb 28, 2022
 *
 */
@Path("/control")
public class JerseyWebApiController {

	@Autowired
	private UserService service;

	/**
	 * This api thread will execute on time, before AysncResponse timeout time.
	 * AsyncResponse TimeoutHandler part will not execute in this api. Response will
	 * send by thread part.
	 * 
	 * @param ar
	 * @throws InterruptedException
	 */
	@GET
	@Path("/alluser")
	public void getUserListNotExecuteHandleTimeout(@Suspended final AsyncResponse ar) throws InterruptedException {

		ar.setTimeoutHandler(new TimeoutHandler() {

			@Override
			public void handleTimeout(AsyncResponse asyncResponse) {
				System.out.println("Async Rsponse method start");
				Map<Integer, User> map = service.getUserListMap();
				UserList userList = new UserList(map.values().stream().collect(Collectors.toList()));
				System.out.println("Printing Aysnc Method task: " + userList.toString());
				asyncResponse.resume(
						Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("From Async Response: " + userList.toString()).build());
				System.out.println("Async Rsponse method end");
			}
		});
		ar.setTimeout(10000, TimeUnit.MILLISECONDS);

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread Started");
				ar.resume(Response.status(Response.Status.OK).entity("From Thread: " + getUserList().toString()).build());
				System.out.println("Thread finished Started");
			}

			private UserList getUserList() {
				UserList userList = service.getUserList(service.getUserListMap(), 200);
				return userList;
			}
		}).start();

	}

	/**
	 * This api thread won't execute on time, AysncResponse timeout will exceed.
	 * AsyncResponse TimeoutHandler part will execute in this api, response will
	 * send from handler part.
	 * 
	 * @param ar
	 * @throws InterruptedException
	 */
	@GET
	@Path("/alluser-timeout")
	public void getUserListExecuteTimeoutHandler(@Suspended final AsyncResponse ar) throws InterruptedException {

		ar.setTimeoutHandler(new TimeoutHandler() {

			@Override
			public void handleTimeout(AsyncResponse asyncResponse) {
				System.out.println("Async Rsponse method start");
				Map<Integer, User> map = service.getUserListMap();
				UserList userList = new UserList(map.values().stream().collect(Collectors.toList()));
				System.out.println("Printing Aysnc Method task: " + userList.toString());
				asyncResponse.resume(
						Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("From Async Response: " +userList.toString()).build());
				System.out.println("Async Rsponse method end");
			}
		});
		ar.setTimeout(10000, TimeUnit.MILLISECONDS);

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Thread Started");
				ar.resume(Response.status(Response.Status.OK).entity("From Thread: " + getUserList().toString()).build());
				System.out.println("Thread finished Started");
			}

			private UserList getUserList() {
				UserList userList = service.getUserList(service.getUserListMap(), 3000);
				return userList;
			}
		}).start();

	}

}
