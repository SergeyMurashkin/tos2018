package net.thumbtack.school.concert.requestException;

public class RequestException extends Exception {

    private RequestErrorCode requestErrorCode;

    public RequestException(RequestErrorCode requestErrorCode){
        this.requestErrorCode = requestErrorCode;
    }

    public RequestErrorCode getRequestErrorCode(){
        return requestErrorCode;
    }

}
