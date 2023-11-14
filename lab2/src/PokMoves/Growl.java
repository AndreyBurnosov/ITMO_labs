package PokMoves;

import ru.ifmo.se.pokemon.*;

public class Growl extends StatusMove {
    public Growl(){
        super(Type.NORMAL, 0, 100);
    }
    @Override
    protected String describe(){
        return "does " + getClass().getSimpleName();
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);
        Effect effect = new Effect().stat(Stat.ATTACK, -1);
        pokemon.addEffect(effect);
    }
}
