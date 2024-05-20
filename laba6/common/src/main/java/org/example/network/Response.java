package org.example.network;

import org.example.utility.Status;

import java.io.Serializable;
import java.util.Objects;

public class Response implements Serializable {
    private final String message;
    private final Status status;
    public Response (Status status, String message){
        this.status = status;
        this.message = message;
    }
    public Status getStatus(){return status;}
    public String getMessage(){return message;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(message, response.message) && Objects.equals(status, response.status);
    }
}
