package PokMoves;

import ru.ifmo.se.pokemon.*;

public class SteelWing extends PhysicalMove {
    public SteelWing(){
        super(Type.STEEL, 70, 90);
    }

    @Override
    protected String describe(){
        return "does " + getClass().getSimpleName();
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        if (chance(0.1)) {
            Effect effect = new Effect().stat(Stat.DEFENSE, 1);
            pokemon.addEffect(effect);
        }
    }

    private boolean chance(double d) {
        return d > Math.random();
    }
}
