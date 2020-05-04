//    Lab-07-jadams18
//    Position.java
//    @username jwadams18

public enum Position {
    C("Catcher", 0),
    SP("Starting Pitcher", 1),
    RP("Relief Pitcher", 2),
    First("First Base", 3),
    Second("Second Base", 4),
    SS("Shortstop", 5),
    Third("Third base", 6),
    RF("Right field", 7),
    CF("Center field", 8),
    LF("Left field", 9);

    private String position;
    private int index;
    Position(String position, int i) {
        this.position = position;
        this.index = i;
    }

    public String getName() {
        return this.position;
    }

    public int getIndex(){
        return this.index;
    }
}
