package com.tanmoy.grpc.server;

import java.io.IOException;

import com.tanmoy.grpc.service.UserService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Grpc server started");
		Server server = ServerBuilder.forPort(9090).addService(new UserService()).build();
		server.start();
		System.out.println("Grpc server started at port: " + server.getPort());
		server.awaitTermination();

	}

}
