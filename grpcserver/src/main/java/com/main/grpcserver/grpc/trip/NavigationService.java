package com.main.grpcserver.grpc.trip;
import com.main.grpcserver.services.Services;
import com.main.trip.NavigationServiceGrpc;
import com.main.trip.TripRequest;
import com.main.trip.TripResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Created By: Ali Mohammadi
 * Date: 03 Nov, 2021
 */
@GrpcService
public class NavigationService extends NavigationServiceGrpc.NavigationServiceImplBase {
  @Autowired
  Services services;
  @Override
  public StreamObserver<TripRequest> navigate(StreamObserver<TripResponse> responseObserver) {
    return new TripRequestObserver( responseObserver );
  }
}
