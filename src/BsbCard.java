/*
        James Adams
        Lab-07-jadams18
        BsbCard.java
        @username jwadams18
         */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BsbCard {

    private String image, name;
    private Team team;
    private Position pos;
    private int age, yrsPlayed, rarity;
    private Condition condition;
    private boolean trade;

    public BsbCard(String name, String image, int age, int yrsPlayed, int team, int pos, int rarity, int condition, boolean trade) {

        this.name = name;
        this.image = image;
        this.age = age;
        this.yrsPlayed = yrsPlayed;
        setTeam(team);
        setPosition(pos);
        this.rarity = rarity;
        setCondition(condition);
        this.trade = trade;

    }

    /**
     * Used to split data from a file to create a game
     * @param fileName name of the file in the Games directory
     */
    public BsbCard(String fileName) {

        String lineOData = null;
        try {
            lineOData = Files.readAllLines(Paths.get("Cards/"+fileName)).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] elements = lineOData.split(",");

        this.name = elements[0];
        this.image = elements[1];
        this.age = Integer.parseInt(elements[2]);
        this.yrsPlayed = Integer.parseInt(elements[3]);
        this.rarity = Integer.parseInt(elements[6]);
        this.trade = Boolean.getBoolean(elements[7]);

        //Sets the team name
        for(Team t : Team.values()){
            if (t.toString().equals(elements[4])) {
                this.team = t;
                break;
            }
        }
        //Sets the position
        for(Position p : Position.values()){
            if(p.getName().equals(elements[5])){
                this.pos = p;
                break;
            }
        }
        //Sets the condition
        for(Condition c : Condition.values()){
            if(c.getName().equals(elements[7])){
                this.condition = c;
            }
        }


    }

    public void setTeam(int team) {
        for (Team t : Team.values()) {
            if (t.getIndex() == team) {
                this.team = t;
            }
        }
    }

    public void setPosition(int pos) {
        for (Position p : Position.values()) {
            if (p.getIndex() == pos) {
                this.pos = p;
            }
        }
    }

    public void setCondition(int c) {
        for (Condition condition : Condition.values()) {
            if (condition.getIndex() == c) {
                this.condition = condition;
            }
        }
    }

    public void save(){
        File file = new File("Cards/"+this.getName()+".txt");
        if(!file.exists()){
            try {
                boolean success = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw = null;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try{
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.print(this.name+",");
            pw.print(this.image+",");
            pw.print(this.age+",");
            pw.print(this.yrsPlayed+",");
            pw.print(this.team+",");
            pw.print(this.pos.getName()+",");
            pw.print(this.rarity+",");
            pw.print(this.condition.getName()+",");
            pw.print(this.trade);
            pw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setYrs(int yrs) {
        this.yrsPlayed = yrs;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setTrade(boolean trade) {
        this.trade = trade;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getImageFileStr() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public Position getPos() {
        return pos;
    }

    public int getRarity() {
        return rarity;
    }

    public Condition getCondition() {
        return condition;
    }

    public boolean isTrade() {
        return trade;
    }

    public int getAge() {
        return age;
    }

    public int getYrsPlayed() {
        return yrsPlayed;
    }
}
