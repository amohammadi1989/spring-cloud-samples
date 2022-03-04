package com.main.grpcserver.grpc.trip;
import com.main.trip.TripRequest;
import com.main.trip.TripResponse;
import io.grpc.stub.StreamObserver;

import java.time.Duration;
import java.time.LocalTime;
/**
 * Created By: Ali Mohammadi
 * Date: 03 Nov, 2021
 */
public class TripRequestObserver implements StreamObserver<TripRequest> {
  
  private final int totalDistance = 100;
  private LocalTime startTime = LocalTime.now();
  private int distanceTraveled;
  private final StreamObserver<TripResponse> tripResponseStreamObserver;
  
  public TripRequestObserver(StreamObserver<TripResponse> tripResponseStreamObserver) {
    this.tripResponseStreamObserver = tripResponseStreamObserver;
  }
  
  @Override
  public void onNext(TripRequest tripRequest) {
    this.distanceTraveled = Math.min(totalDistance, (this.distanceTraveled + tripRequest.getDistanceTraveled()));
    int remainingDistance = Math.max(0, (totalDistance - distanceTraveled));
  
    // the client has reached destination
    if(remainingDistance == 0){
      this.tripResponseStreamObserver.onNext(TripResponse.getDefaultInstance());
      return;
    }
  
    // client has not yet reached destination
    long elapsedDuration = Duration.between( this.startTime, LocalTime.now()).getSeconds();
    elapsedDuration = elapsedDuration < 1 ? 1 : elapsedDuration;
    double currentSpeed = (distanceTraveled * 1.0d) / elapsedDuration;
    int timeToReach = (int) (remainingDistance / currentSpeed);
    TripResponse tripResponse = TripResponse.newBuilder()
    .setRemainingDistance(remainingDistance)
    .setTimeToDestination(timeToReach)
    .build();
    this.tripResponseStreamObserver.onNext(tripResponse);
  }
  
  @Override
  public void onError(Throwable throwable) {
  
  }
  
  @Override
  public void onCompleted() {
    this.tripResponseStreamObserver.onCompleted();
    System.out.println("Client reached safely");
  }
}
