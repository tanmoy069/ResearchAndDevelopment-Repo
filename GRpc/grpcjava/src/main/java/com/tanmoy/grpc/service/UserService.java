package com.tanmoy.grpc.service;

import com.tanmoy.grpc.User.ApiResponse;
import com.tanmoy.grpc.User.Empty;
import com.tanmoy.grpc.User.LoginRequest;
import com.tanmoy.grpc.userGrpc.userImplBase;

import io.grpc.stub.StreamObserver;

public class UserService extends userImplBase {

	@Override
	public void login(LoginRequest request, StreamObserver<ApiResponse> responseObserver) {
		System.out.println("Inside login");
		String username = request.getUsername();
		String password = request.getPassword();
		
		ApiResponse.Builder response = ApiResponse.newBuilder();
		
		if(username.equals(password)) {
			response.setResponseCode(1).setResponseMessage("Success");
		} else {
			response.setResponseCode(0).setResponseMessage("Failed");
		}
		// setting response value to response observer
		responseObserver.onNext(response.build());
		// closing response observer
		responseObserver.onCompleted();
	}

	@Override
	public void logout(Empty request, StreamObserver<ApiResponse> responseObserver) {
		System.out.println("Inside logout");
	}
	
	

}
