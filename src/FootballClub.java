public class FootballClub extends SportsClub implements Comparable<FootballClub>{
    private static final long serialVersionUID = 1L;
    private int played = 0;
    private int won = 0;
    private int drawn = 0;
    private int lost = 0;
    private int goalsF = 0; // Goals for
    private int goalsA = 0; // Goals against
    private int goalsD = 0; // Goals difference
    private int points = 0;

    public FootballClub(String clubName, String manager, String region, String city){
       super(clubName, manager, region, city);
    }

    public FootballClub(String clubName, String manager, String region, String city, int played, int won, int drawn, int lost, int goalsF, int goalsA, int goalsD, int points){
        super(clubName, manager, region, city);
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsF = goalsF;
        this.goalsA = goalsA;
        this.goalsD = goalsD;
        this.points = points;
    }

    public FootballClub(String clubName, int played, int won, int drawn, int lost, int goalsF, int goalsA, int goalsD, int points){
        super(clubName);
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsF = goalsF;
        this.goalsA = goalsA;
        this.goalsD = goalsD;
        this.points = points;
    }

    @Override
    public int compareTo(FootballClub compareClub) {
        int comparePoints = ((FootballClub)compareClub).getPoints();
        if(comparePoints-this.points == 0){
            int compareGoalDifference = ((FootballClub)compareClub).getGoalsD();
            return compareGoalDifference-this.goalsD;
        }
        else {
            return comparePoints - this.points;
        }
    }

    public void setWon(int GF, int GA){
        played++;
        won++;
        points =+ 3;
        goalsF =+ GF;
        goalsA =+ GA;
        goalsD =+ (GF - GA);
    }

    public void setLost(int GF, int GA){
        played++;
        lost++;
        goalsF = goalsF + GF;
        goalsA = goalsA + GA;
        goalsD = goalsD - (GA - GF);
    }

    public void setDrawn(int goals){
        played++;
        drawn++;
        points++;
        goalsF = goalsF + goals;
        goalsA = goalsA + goals;
    }

    public int getPlayed(){
        return played;
    }

    public  int getWon(){
        return won;
    }

    public int getLost(){
        return lost;
    }

    public int getDrawn(){
        return drawn;
    }

    public int getGoalsF(){
        return goalsF;
    }

    public int getGoalsA(){
        return goalsA;
    }

    public int getGoalsD() {
        return goalsD;
    }

    public int getPoints() {
        return points;
    }

    public void displayClub(){
        System.out.println("\n-Club Details-");
        System.out.println("Club Name\t: " + getClubName());
        System.out.println("Manager\t\t: " + getManager());
        System.out.println("Region\t\t: " + getRegion());
        System.out.println("City\t\t: " + getCity());
        System.out.println("\n-Club Statistics-");
        System.out.println("Played\t\t: " + getPlayed());
        System.out.println("Won\t\t\t: " + getWon());
        System.out.println("Drawn\t\t: " + getDrawn());
        System.out.println("Lost\t\t: " + getLost());
        System.out.println("GF\t\t\t: " + getGoalsF());
        System.out.println("GA\t\t\t: " + getGoalsA());
        System.out.println("GD\t\t\t: " + getGoalsD());
        System.out.println("Points\t\t: " + getPoints());
    }

    public void displayStats(){
        System.out.println(getClubName() + " | Points: " + getPoints() + " | GD: " + getGoalsD());
    }

//    public String printName(String name){
//        char[] charArray = name.toCharArray();
//        for (int i = 0; i < charArray.length; i++) {
//            if (charArray[i] == '_') {
//                charArray[i] = ' ';
//            }
//        }
//        return name = String.valueOf(charArray);
//    }



}