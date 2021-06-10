public class UniversityFootballClub extends FootballClub {
    private String universityName;
    private static final long serialVersionUID = 1L;

    public UniversityFootballClub(String clubName, String manager, String region, String city){
        super(clubName, manager, region, city);
    }

    public String getUniversityName(){
        return universityName;
    }
}