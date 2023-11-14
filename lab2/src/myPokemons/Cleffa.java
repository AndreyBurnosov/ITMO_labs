package myPokemons;

import PokMoves.*;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Cleffa extends Pokemon{
    public Cleffa(String name, int lvl){
        super(name, lvl);
        super.setType(Type.FAIRY);
        super.setStats(50, 25, 28, 45, 55, 15);
        Move[] moves = new Move[]{new Facade(), new Sing()};
        super.setMove(moves);
        super.setLevel(MovesLVL.findMinimalLvl(moves));
    }
    public Cleffa(String name, int lvl, Move[] new_moves, int[] stats){
        super(name, lvl);
        super.setType(Type.FAIRY);
        super.setStats(stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);

        super.setMove(MergeMoves(new Move[]{new Facade(), new Sing()}, new_moves));
        super.setLevel(MovesLVL.findMinimalLvl(MergeMoves(new Move[]{}, new_moves)));
    }

    protected static Move[] MergeMoves(Move[] a, Move[] b){
        Move[] moves = new Move[a.length + b.length];
        int cnt = 0;

        for(int i = 0; i < a.length; i++) {
            moves[i] = a[i];
            cnt++;
        }

        for(int j = 0; j < b.length;j++) {
            moves[cnt++] = b[j];
        }

        return moves;
    }
}