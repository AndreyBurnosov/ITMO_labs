package PokMoves;

import ru.ifmo.se.pokemon.*;

public class ChargeBeam extends SpecialMove {
    public ChargeBeam() {
        super(Type.ELECTRIC, 50, 90);
    }

    @Override
    protected String describe(){
        return "does " + getClass().getSimpleName();
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        if (chance(0.7)) {
            Effect effect = new Effect().stat(Stat.SPECIAL_ATTACK, 1);
            pokemon.addEffect(effect);
        }
    }

    private boolean chance(double d) {
        return d > Math.random();
    }
}
