package classes;

import abstracts.Items;
import enums.Food;
import enums.Symbols;
import enums.Tastes;
import interfaces.Edible;

import java.util.List;

public class Bottle extends Items {
    private class Content implements Edible {
        private String name;
        private List<Food> beSame;
        private Tastes taste;
        private Content(String name, List<Food> food, Tastes taste) {
            this.beSame = food;
            this.taste = taste;
            this.name = name;
        }
        public String eat() {
            String res = name + " оказалось " + Tastes.getName(taste) + " и похожим на";
            for (var food: beSame){
                res += " " + Food.getName(food) + ",";
            }
            return res.substring(0, res.length() - 1);
        }
        public String getName() {
            return name;
        }
    }
    List<Symbols> symbols;
    private Content content;
    private boolean fill = false;
    public Bottle(List<Symbols> symbols) {
        this.symbols = symbols;
    }
    public String notExist() {
        String res = "не было";
        for (var symbol: Symbols.values()) {
            if (!symbols.contains(symbol)) {
                res += " " + Symbols.getName(symbol) + ",";
            }
        }
        if (res.length() == 7) {return "было всё";}
        return res.substring(0, res.length() - 1);
    }
    public String isFilled() {
        return fill ? "не пустая": "пустая";
    }

    public Tastes contentTaste() {
        return content.taste;
    }
    public String drink() {
        fill = false;
        return content.eat();
    }
    public String getContentName() {
        if (!fill) {
            return "пусто";
        }
        return content.getName();
    }
    public void pour(List<Food> food, Tastes taste, String name) {
        fill = true;
        this.content = new Content(name, food, taste);
    }
}
