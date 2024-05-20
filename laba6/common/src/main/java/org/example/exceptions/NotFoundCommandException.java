package org.example.exceptions;

import java.io.IOException;

public class NotFoundCommandException extends IOException {
    public String getMessage(){
        return "Команда не введена";
    }
}
