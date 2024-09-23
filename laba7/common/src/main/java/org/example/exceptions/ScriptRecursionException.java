package org.example.exceptions;

import java.io.IOException;

public class ScriptRecursionException extends IOException {
    public String getMessage(){
        return "Скрипты не могут вызываться рекурсивно";
    }
}
