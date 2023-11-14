package PokMoves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class FurySwipes extends PhysicalMove {
    public FurySwipes(){
        super(Type.NORMAL, 18, 80);
    }

    @Override
    protected String describe(){
        return "does " + getClass().getSimpleName();
    }

    @Override
    protected void applyOppDamage(Pokemon pokemon, double damage) {
        super.applyOppDamage(pokemon, damage);
        if (chance(3d / 8d)) {
            super.applyOppDamage(pokemon, damage * 2);
        }
        if (chance(3d / 8d)) {
            super.applyOppDamage(pokemon, damage * 3);
        }
        if (chance(1d / 8d)) {
            super.applyOppDamage(pokemon, damage * 4);
        }
        if (chance(1d / 8d)) {
            super.applyOppDamage(pokemon, damage * 5);
        }
    }

    private boolean chance(double d) {
        return d > Math.random();
    }
}


