package classes;

import abstracts.Characters;
import interfaces.AlisaAction;

public class Alisa extends Characters implements AlisaAction {
    public String toTry() {
        return "попробовала";
    }
    public String notNotice() {
        return "не заметила";
    }
}
