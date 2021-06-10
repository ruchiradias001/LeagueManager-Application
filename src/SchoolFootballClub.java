public class SchoolFootballClub extends FootballClub {
    private String schoolName;
    private static final long serialVersionUID = 1L;

    public SchoolFootballClub(String clubName, String manager, String region, String city){
        super(clubName, manager, region, city);
    }

    public String getUniversityName(){
        return schoolName;
    }
}
