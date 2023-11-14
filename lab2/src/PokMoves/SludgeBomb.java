package PokMoves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class SludgeBomb extends SpecialMove{
    public SludgeBomb(){
        super(Type.POISON, 90, 100);
    }

    @Override
    protected String describe(){
        return "does " + getClass().getSimpleName();
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (chance(0.3)) {
            Effect.poison(pokemon);
        }
    }

    private boolean chance(double d) {
        return d > Math.random();
    }
}