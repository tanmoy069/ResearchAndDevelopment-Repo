package com.tanmoy.grpcclient;

import com.tanmoy.grpc.User.ApiResponse;
import com.tanmoy.grpc.User.LoginRequest;
import com.tanmoy.grpc.userGrpc;
import com.tanmoy.grpc.userGrpc.userBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
	
	public static void main(String[] args) {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
		
		// stubs - generate from proto
		
		userBlockingStub userStub = userGrpc.newBlockingStub(channel);
		
		LoginRequest loginrequest = LoginRequest.newBuilder().setUsername("tanmoy").setPassword("tanmoy").build();
		
		ApiResponse response = userStub.login(loginrequest);
		
		System.out.println(response.getResponseMessage());	
	} 

}
