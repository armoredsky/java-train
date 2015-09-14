package com.mstreeter.trains.domain;

public class NoRouteException extends Throwable{
    public NoRouteException(){
        super("NO SUCH ROUTE");
    }

    public NoRouteException(String message) {
        super(message);
    }
}
