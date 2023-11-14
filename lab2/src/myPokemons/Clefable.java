package myPokemons;

import PokMoves.*;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Clefable extends Clefairy{
    public Clefable(String name, int lvl) {
        super(name, lvl, new Move[]{}, new int[]{95, 70, 73, 95, 90, 60});
    }
}