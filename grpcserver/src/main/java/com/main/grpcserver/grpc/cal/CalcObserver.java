package com.main.grpcserver.grpc.cal;
import com.main.calculator.Input;
import com.main.calculator.Output;
import io.grpc.stub.StreamObserver;
/**
 * Created By: Ali Mohammadi
 * Date: 03 Nov, 2021
 */
public class CalcObserver implements StreamObserver<Input> {
  int sum=0;
  private StreamObserver<Output> outputStreamObserver;
  
  public CalcObserver(StreamObserver<Output> outputStreamObserver) {
    this.outputStreamObserver = outputStreamObserver;
  }
  
  @Override
  public void onNext(Input input) {
     sum+=input.getNumber()+sum;
  }
  
  @Override
  public void onError(Throwable throwable) {
  
  }
  
  @Override
  public void onCompleted() {
    outputStreamObserver.onNext( Output.newBuilder().setResult( sum ).build() );
    outputStreamObserver.onCompleted();
  }
}
