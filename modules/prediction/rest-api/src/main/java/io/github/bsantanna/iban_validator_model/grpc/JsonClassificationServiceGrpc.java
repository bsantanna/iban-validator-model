package io.github.bsantanna.iban_validator_model.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: json_classification_service.proto")
public final class JsonClassificationServiceGrpc {

  private JsonClassificationServiceGrpc() {}

  public static final String SERVICE_NAME = "iban_validator_model.JSONClassificationService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<InputFeatures,
    OutputLabel> METHOD_GET_PREDICTION =
      io.grpc.MethodDescriptor.<InputFeatures, OutputLabel>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "iban_validator_model.JSONClassificationService", "getPrediction"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              InputFeatures.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              OutputLabel.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static JSONClassificationServiceStub newStub(io.grpc.Channel channel) {
    return new JSONClassificationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static JSONClassificationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new JSONClassificationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static JSONClassificationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new JSONClassificationServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class JSONClassificationServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getPrediction(InputFeatures request,
                              io.grpc.stub.StreamObserver<OutputLabel> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_PREDICTION, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_PREDICTION,
            asyncUnaryCall(
              new MethodHandlers<
                InputFeatures,
                OutputLabel>(
                  this, METHODID_GET_PREDICTION)))
          .build();
    }
  }

  /**
   */
  public static final class JSONClassificationServiceStub extends io.grpc.stub.AbstractStub<JSONClassificationServiceStub> {
    private JSONClassificationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JSONClassificationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JSONClassificationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JSONClassificationServiceStub(channel, callOptions);
    }

    /**
     */
    public void getPrediction(InputFeatures request,
                              io.grpc.stub.StreamObserver<OutputLabel> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_PREDICTION, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class JSONClassificationServiceBlockingStub extends io.grpc.stub.AbstractStub<JSONClassificationServiceBlockingStub> {
    private JSONClassificationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JSONClassificationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JSONClassificationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JSONClassificationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public OutputLabel getPrediction(InputFeatures request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_PREDICTION, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class JSONClassificationServiceFutureStub extends io.grpc.stub.AbstractStub<JSONClassificationServiceFutureStub> {
    private JSONClassificationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private JSONClassificationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected JSONClassificationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new JSONClassificationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<OutputLabel> getPrediction(
        InputFeatures request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_PREDICTION, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PREDICTION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final JSONClassificationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(JSONClassificationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PREDICTION:
          serviceImpl.getPrediction((InputFeatures) request,
              (io.grpc.stub.StreamObserver<OutputLabel>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class JSONClassificationServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return JsonClassificationService.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (JsonClassificationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new JSONClassificationServiceDescriptorSupplier())
              .addMethod(METHOD_GET_PREDICTION)
              .build();
        }
      }
    }
    return result;
  }
}
