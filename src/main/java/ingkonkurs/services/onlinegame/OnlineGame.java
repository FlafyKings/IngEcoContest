package ingkonkurs.services.onlinegame;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OnlineGame {
    private final int groupCount;
    private final Clan[] clans;

    public OnlineGame(@JsonProperty("groupCount") int groupCount,
                      @JsonProperty("clans") Clan[] clans) {
        this.groupCount = groupCount;
        this.clans = clans;
    }

    public Clan[] getClans() {
        return this.clans;
    }

    public int getGroupCount() {
        return this.groupCount;
    }

    public String toString(){
        System.out.println("groupCount: " + groupCount);
        for (Clan clan : clans) {
            System.out.println("Player num: " + clan.getNumberOfPlayers() + ", points: " + clan.getPoints());
        }
        return null;
    }
}
