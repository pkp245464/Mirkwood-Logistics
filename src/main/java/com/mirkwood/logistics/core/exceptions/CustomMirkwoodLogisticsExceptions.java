package com.mirkwood.logistics.core.exceptions;

public class CustomMirkwoodLogisticsExceptions extends RuntimeException{

    public CustomMirkwoodLogisticsExceptions() {
        super();
    }

    public CustomMirkwoodLogisticsExceptions(String message) {
        super(message);
    }

    public CustomMirkwoodLogisticsExceptions(String message,Throwable throwable) {
        super(message,throwable);
    }

    public CustomMirkwoodLogisticsExceptions(Throwable throwable) {
        super(throwable);
    }
}
