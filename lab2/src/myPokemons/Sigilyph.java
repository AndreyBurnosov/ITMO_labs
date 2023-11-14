package myPokemons;

import PokMoves.*;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Sigilyph extends Pokemon {
    public Sigilyph(String name, int lvl){
        super(name, lvl);
        super.setType(Type.PSYCHIC, Type.FLYING);
        super.setStats(72, 58, 80, 103, 80, 97);

        Move[] moves = new Move[]{new SteelWing(), new CalmMind(), new Psychic(), new ChargeBeam()};
        super.setMove(moves);
        super.setLevel(MovesLVL.findMinimalLvl(moves));
    }
}
