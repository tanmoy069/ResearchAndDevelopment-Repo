package com.dev.tanmoy.grpc.module.service;

import org.olmis.pypepro.proto.module.proto.ApiResponseOuterClass.ApiResponse;
import org.olmis.pypepro.proto.module.proto.ContactOuterClass.Contact;
import org.olmis.pypepro.proto.module.proto.FindRequestOuterClass.FindRequest;
import org.olmis.pypepro.proto.module.proto.findGrpc.findImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ContactServiceImpl extends findImplBase {

	@Override
	public void findContact(FindRequest request, StreamObserver<ApiResponse> responseObserver) {

		String message = request.getId() + "";
		System.out.println("Received Message: " + message);
		Contact contact = Contact.newBuilder().setId(request.getId()).build();
		ApiResponse apiResponse = ApiResponse.newBuilder().setResponseMessage(message).setResponseCode(1).setContact(contact).build();

		responseObserver.onNext(apiResponse);
		responseObserver.onCompleted();
	}

}
