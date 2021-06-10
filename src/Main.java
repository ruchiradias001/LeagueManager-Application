import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PremierLeagueManager obj = new PremierLeagueManager();
        Scanner sc = new Scanner(System.in);
        obj.loadFromFile();

        System.out.println("Premier League Championship Manager Application Starts");

        menu:
        while (true) {

            System.out.println("");
            System.out.println("Enter \"A\" to add a new football club to the Premier League");
            System.out.println("Enter \"D\" to delete an existing club from the Premier League");
            System.out.println("Enter \"C\" to display the stats of a Premier League Club");
            System.out.println("Enter \"T\" to display the Premier League Table");
            System.out.println("Enter \"M\" to add a match statics");
            System.out.println("Enter \"S\" to save to file");
            System.out.println("Enter \"G\" to GUI Menu");
            System.out.println("Enter \"Q\" to quit");
            System.out.print("\nEnter Your Option : ");
            String Option = sc.next().toUpperCase();
            switch (Option) {
                case "A":
                    obj.addClub();
                    break;
                case "D":
                    obj.deleteClub();
                    break;
                case "C":
                    obj.displayClubDetails();
                    break;
                case "T":
                    obj.displayPLT();
                    break;
                case "M":
                    obj.addMatch();
                    break;
                case "S":
                    obj.saveToFile();
                    break;
                case "G":
                    obj.GUI();
                    break;
                case "Q":
                    System.out.println("\nPremier League Championship Manager Application Quits");
                    break menu;
                default:
                    System.out.println("Invalid Option\nRe-Enter Option");
            }
        }
    }
}

