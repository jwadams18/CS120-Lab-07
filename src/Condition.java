//    Lab-07-jadams18
//    Condition.java
//    @username jwadams18

public enum Condition {

    MINT("Mint", 0),
    EXCELLENT("Excellent", 1),
    GOOD("Good", 2),
    FAIR("Fair", 3),
    POOR("Poor", 4);

    private String name;
    private int index;
    Condition(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return index;
    }
}
