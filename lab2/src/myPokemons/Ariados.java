package myPokemons;

import PokMoves.*;
import ru.ifmo.se.pokemon.Move;

public class Ariados extends Spinarak{
    public Ariados(String name, int lvl) {
        super(name, lvl, new Move[]{new VenomDrench()}, new int[]{70, 90, 70, 60, 70, 40});
    }
}