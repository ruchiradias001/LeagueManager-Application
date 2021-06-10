import java.io.IOException;

public interface LeagueManager {
    void addClub();
    void deleteClub();
    void displayClubDetails();
    void displayPLT();
    void addMatch();
    void saveToFile();
    void loadFromFile() throws IOException;
    void GUI();
}
