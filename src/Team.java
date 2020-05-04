/*
        James Adams
        Lab-07-jadams18
        Team.java
        @username jwadams18
         */

public enum Team {

    AD("Arizona Diamondbacks", 0),
    AB("Atlanta Brave", 1),
    BO("Baltimore Orioles", 2),
    BRS("Boston Red Sox", 3),
    CC("Chicago Cubs", 4),
    CWS("Chicago White Sox", 5),
    CinR("Cincinnati Reds", 6),
    CI("Cleveland Indians", 7),
    ColR("Colorado Rockies", 8),
    DT("Detroit Tigers", 9),
    HA("Houston Astros", 10),
    KCR("Kansas City Royals", 11),
    LAA("Los Angles Angles", 12),
    LAD("Los Angeles Dodgers", 13),
    MM("Miami Marlins", 14),
    MB("Milwaukee Brewers", 15),
    MT("Minnesota Twins", 16),
    NYM("New York Mets", 17),
    NYY("New York Yankees", 18),
    OA("Oakland Athletics", 19),
    PhilP("Philadelphia Phillies", 20),
    PitP("Pittsburgh Pirates", 21),
    SDP("San Diego Padres", 22),
    SFG("San Fransico Giants", 23),
    SM("Seattle Mariners", 24),
    SLC("St. Louis Cardinals", 25),
    TBR("Tampa Bay Rays", 26),
    TR("Texas Rangers", 27),
    TBJ("Toronto Blue Jays", 28),
    WN("Washing Nationals", 29);
    private String name;
    private int index;

    Team(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }
}
