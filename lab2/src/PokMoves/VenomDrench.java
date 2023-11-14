package PokMoves;

import ru.ifmo.se.pokemon.*;

public class VenomDrench extends StatusMove {
    public VenomDrench(){
        super(Type.POISON, 0, 100);
    }

    @Override
    protected String describe(){
        return "does " + getClass().getSimpleName();
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);
        if (pokemon.hasType(Type.POISON)) {
            Effect effect1 = new Effect().stat(Stat.ATTACK, -1);
            Effect effect2 = new Effect().stat(Stat.SPECIAL_ATTACK, -1);
            Effect effect3 = new Effect().stat(Stat.SPEED, -1);
            pokemon.addEffect(effect1);
            pokemon.addEffect(effect2);
            pokemon.addEffect(effect3);
        }

    }
}
