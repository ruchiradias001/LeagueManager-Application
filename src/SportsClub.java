import java.io.Serializable;

public class SportsClub implements Serializable {
    private static final long serialVersionUID = 1L;
    private String clubName;
    private String manager;
    private String region;
    private String city;

    public SportsClub(String clubName, String manager, String region, String city){
        this.clubName = clubName;
        this.manager = manager;
        this.region = region;
        this.city = city;
    }

    public SportsClub(String clubName){
        this.clubName = clubName;
    }

    public void setClubName(String n){
        clubName = n;
    }

    public void setManager(String m){
        manager = m;
    }

    public String getClubName(){
        return clubName;
    }

    public String getManager(){
        return manager;
    }

    public String getRegion(){
        return region;
    }

    public String getCity(){
        return city;
    }
}

