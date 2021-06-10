import java.io.Serializable;

public class Match implements Comparable<Match>, Serializable {
    private static final long serialVersionUID = 1L;
    private Date matchDate;
    private String dateInString;
    private String homeClub;
    private String awayClub;
    private int homeGoals;
    private int awayGoals;

    public Match(Date matchDate,String dateInString, String homeClub, String awayClub, int homeGoals, int awayGoals){
        this.matchDate = matchDate;
        this.dateInString = dateInString;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public Match(String dateInString, String homeClub, String awayClub, int homeGoals, int awayGoals){
        this.dateInString = dateInString;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    @Override
    public int compareTo(Match compareMatch) {
        Date compareDate = ((Match)compareMatch).getMatchDate();
        if(compareDate.getYear()-this.matchDate.getYear() == 0){
            if(compareDate.getMonth()-this.matchDate.getMonth() == 0){
                return compareDate.getDay()-this.matchDate.getDay();
            }
            else {
                return compareDate.getMonth()-this.matchDate.getMonth();
            }
        }
        else {
            return compareDate.getYear()-this.matchDate.getYear();
        }
    }

    public Date getMatchDate(){
        return matchDate;
    }

    public String getDateInString(){
        return dateInString;
    }

    public String getHomeClub(){
        return homeClub;
    }

    public String getAwayClub(){
        return awayClub;
    }

    public int getHomeGoals(){
        return homeGoals;
    }

    public int getAwayGoals(){
        return awayGoals;
    }
}
