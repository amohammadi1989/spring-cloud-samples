package com.main.grpcserver.grpc.cal;
import com.main.calculator.CalculatorServiceGrpc;
import com.main.calculator.Input;
import com.main.calculator.Output;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
/**
 * Created By: Ali Mohammadi
 * Date: 02 Nov, 2021
 */
@GrpcService
public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {
  final int sum=0;
  @Override
  public void find(Input request, StreamObserver<Output> responseObserver) {
    int input = request.getNumber();
       int result=input*100;
      Output output = Output.newBuilder()
      .setResult( result )
      .build();
      responseObserver.onNext( output );

    responseObserver.onCompleted();
  }
  
  @Override
  public void getAll(Input request, StreamObserver<Output> responseObserver) {
    int input = request.getNumber();
    for(int i=0;i<=10;i++) {
      long result = input * i;
      Output output = Output.newBuilder()
      .setResult( result )
      .build();
      responseObserver.onNext( output );
    
      try {
        Thread.sleep( 1000 );
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
      
      }
    
    
    }
    responseObserver.onCompleted();
  }
  
  @Override
  public StreamObserver<Input> sumAll(StreamObserver<Output> responseObserver) {
    return new CalcObserver( responseObserver );
  }
}
