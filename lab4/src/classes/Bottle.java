package classes;

import abstracts.Items;
import enums.Food;
import enums.Symbols;
import enums.Tastes;
import exceptions.EmptyBottleException;
import interfaces.Edible;

import java.util.List;

public class Bottle extends Items {
    private static class Content implements Edible {
        private String name;
        private List<Food> beSame;
        private Tastes taste;
        private int heightEffect;
        private Content(String name, List<Food> food, Tastes taste, int heightEffect) {
            this.beSame = food;
            this.taste = taste;
            this.name = name;
            this.heightEffect = heightEffect;
        }
        public String eat(Human human) {
            String res = name + " оказалось " + Tastes.getName(taste) + " и похожим на";
            for (var food: beSame){
                res += " " + Food.getName(food) + ",";
            }
            human.setHeight(human.getHeight() + heightEffect);
            return res.substring(0, res.length() - 1);
        }
        public String getName() {
            return name;
        }
    }

    class BottleContentManager {
        void fillBottle(String name, List<Food> food, Tastes taste, int heightEffect) {
            content = new Content(name, food, taste, heightEffect);
            fill = true;
        }

        void emptyBottle() {
            if (!fill) {
                System.out.println("Бутылка уже пуста.");
                return;
            }
            System.out.println("Бутылка опустошена: " + content.getName());
            content = null;
            fill = false;
        }
    }
    BottleContentManager bottleContentManager = new BottleContentManager();
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
    public String drink(Human human) {

        if (!fill) {
            throw new EmptyBottleException("Бутылка пуста");
        }
        fill = false;
        return content.eat(human);
    }
    public void interact(Human human) {
        new Object() {
            void applyEffect() {
                if (!fill) {
                    System.out.println("Нет эффекта, бутылка пуста.");
                    return;
                }
                System.out.println(content.eat(human));
                fill = false;
            }
        }.applyEffect();
    }
    public String getContentName() {
        if (!fill) {
            return "пусто";
        }
        return content.getName();
    }
    public void pour(List<Food> food, Tastes taste, String name, int heightEffect) {
        bottleContentManager.fillBottle(name, food, taste, heightEffect);
    }

    public void empty() {
        bottleContentManager.emptyBottle();
    }
}
