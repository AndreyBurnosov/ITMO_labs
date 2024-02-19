package classes;

import abstracts.Items;
import interfaces.BottleAction;

public class Bottle extends Items implements BottleAction {
    public String notExist() {
        return "не было";
    }
    public String becomeEmpty() {
        return "опустеть";
    }

    public static class Content extends Items{
        public String beTaste() {
            return "оказалось вкусным";
        }
        public String beSame() {
            return "похоже на";
        }
    }
}
