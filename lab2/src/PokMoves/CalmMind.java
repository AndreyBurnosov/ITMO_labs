package PokMoves;

import ru.ifmo.se.pokemon.*;

public class CalmMind extends StatusMove {
    public CalmMind(){
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected String describe(){
        return "does " + getClass().getSimpleName();
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        Effect effect1 = new Effect().stat(Stat.SPECIAL_ATTACK, 1);
        pokemon.addEffect(effect1);
        Effect effect2 = new Effect().stat(Stat.SPECIAL_DEFENSE, 1);
        pokemon.addEffect(effect2);
    }
}
