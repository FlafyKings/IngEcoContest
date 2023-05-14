package ingkonkurs.services.onlinegame;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

public final class Clan implements Comparable<Clan>{

    private final int numberOfPlayers;
    private final int points;

    public Clan(@JsonProperty("numberOfPlayers") int numberOfPlayers,
                @JsonProperty("points") int points) {
        this.numberOfPlayers = numberOfPlayers;
        this.points = points;
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public int getPoints() {
        return this.points;
    }

    @Override
    public int compareTo(Clan o) {
        return Comparator.comparing(Clan::getPoints)
                .thenComparing(Clan::getNumberOfPlayers, Comparator.reverseOrder())
                .compare(this, o);
    }

}
