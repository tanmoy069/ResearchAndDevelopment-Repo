package com.tanmoy.JerseyWebApiRnd.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tanmoy.JerseyWebApiRnd.model.User;
import com.tanmoy.JerseyWebApiRnd.model.UserList;

@Service
public class UserService {
	
	private static Map<Integer, User> USER_VIRTUAL_DB = new HashMap<>();
	
	static
	  {
	    User user1 = new User(1, "tanmoy", "12345");	 
	    User user2 = new User(2, "tushar", "12345");
	    User user3 = new User(3, "alvi", "12345");
	    User user4 = new User(4, "random", "12345");
	    User user5 = new User(5, "naika", "12345");
	     
	    USER_VIRTUAL_DB.put(user1.getUserId(), user1);
	    USER_VIRTUAL_DB.put(user2.getUserId(), user2);
	    USER_VIRTUAL_DB.put(user3.getUserId(), user3);
	    USER_VIRTUAL_DB.put(user4.getUserId(), user4);
	    USER_VIRTUAL_DB.put(user5.getUserId(), user5);
	  }
	
	public Map<Integer, User> getUserListMap(){
		return USER_VIRTUAL_DB;
	}
	
	public UserList getUserList(Map<Integer, User> map, int milliSeconds) {
		for (Integer key : map.keySet()) {
			try {
				System.out.println("Waiting for 3 seconds");
				Thread.sleep(milliSeconds);
				System.out.println("User No: " + key + "; " + map.get(key));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return new UserList(map.values().stream().collect(Collectors.toList()));
	}

}
