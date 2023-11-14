package PokMoves;

import ru.ifmo.se.pokemon.Move;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MovesLVL{

    private static Map<Class, Integer> createMap() {
        Map<Class, Integer> map = new HashMap<>();
        map.put(Facade.class, 5);
        map.put(Sing.class, 15);
        map.put(FurySwipes.class, 5);
        map.put(Growl.class, 40);
        map.put(NightShade.class, 5);
        map.put(VenomDrench.class, 10);
        map.put(SludgeBomb.class, 15);
        map.put(CalmMind.class, 20);
        map.put(Psychic.class, 20);
        map.put(SteelWing.class, 20);
        map.put(ChargeBeam.class, 30);
        return map;
    }

    public static int findMinimalLvl(Move[] moves) {
        Map<Class, Integer> map = createMap();
        return Arrays.stream(moves).map(Object::getClass).mapToInt(map::get).max().orElse(-1);
    }
}
