package myPokemons;

import PokMoves.*;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Clefairy extends Cleffa{
    public Clefairy(String name, int lvl) {
        super(name, lvl, new Move[]{new Growl()}, new int[]{70, 45, 48, 60, 65, 35});
    }

    public Clefairy(String name, int lvl, Move[] new_moves, int[] stats){
        super(name, lvl, MergeMoves(new Move[]{new Growl()}, new_moves), stats);
    }

}