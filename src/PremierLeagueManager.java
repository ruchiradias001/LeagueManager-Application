import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static javafx.geometry.Pos.CENTER;

public class PremierLeagueManager implements LeagueManager{
    ArrayList<FootballClub> PremierLeague = new ArrayList<FootballClub>();
    ArrayList<Match> PremierLeagueMatches = new ArrayList<Match>();

    @Override
    public void addClub(){
        if (PremierLeague.size() <= 20) {
            System.out.println("\nAdd to Premier League");
            String clubName = inputName("\nClub name\t: ");
            String manager = inputName("\nManager\t\t: ");
            String region = inputName("\nRegion\t\t: ");
            String city = inputName("\nCity\t\t: ");
            FootballClub newClub = new FootballClub(clubName,manager,region,city);
            objectCheck(newClub);   // To check new football club name and manager already taken or not
            PremierLeague.add(newClub);
            System.out.println("\n" + newClub.getClubName() + " added to Premier League");

        }
        else {
            System.out.println("Premier League is Full");
        }
    }

    public String inputName(String msg){
        String name;
        Scanner sc = new Scanner(System.in);
        int count = 0;  // Count of characters other than letters, underscores and spaces
        while(true) {
            System.out.print(msg);
            name = sc.nextLine();
            if (name.isEmpty()) {
                System.out.println("Invalid Input !!!");    // If entered without entering anything
            }
            else {
                char[] charArray = name.toCharArray();
                int arrayLength = charArray.length;
                for (int i = 0; i < arrayLength; i++) {
                    if (!Character.isLetter(charArray[i])) {
                        if (charArray[i] == ' ' || charArray[i] == '_') {
                            charArray[i] = '_';
                            // If next character also a space or a underscore - code start
                            if (i < arrayLength - 1) {
                                int a = 1;
                                for(int b = 0; b < a; b++) {
                                    if (charArray[i + 1] == ' ' || charArray[i + 1] == '_') {
                                        for (int j = i + 1; j < arrayLength - 1; j++) {
                                            charArray[j] = charArray[j + 1];
                                        }
                                        charArray[arrayLength - 1] = ' '; // This one is no need
                                        arrayLength--;
                                        if (i < arrayLength - 1) {
                                            a++; // To check if next character also a space or a underscore
                                        }
                                    }
                                }
                            }
                            // If next character also a space or a underscore - code close
                        }
                        else {
                            count++;
                        }
                    }
                }
                if(arrayLength == 1){
                    System.out.println("Invalid Input !!!"); // If someone input spaces or underscores or one letter
                }
                else if (count == 0) {
                    if (charArray[0] == '_' || charArray[arrayLength-1] == '_') {
                        if (charArray[0] == '_' && charArray[arrayLength - 1] == '_'){
                            name = String.valueOf(charArray).substring(1, arrayLength - 1);
                        }
                        else if (charArray[0] == '_'){
                            name = String.valueOf(charArray).substring(1, arrayLength);
                        }
                        else{
                            name = String.valueOf(charArray).substring(0, arrayLength - 1);
                        }
                    }
                    else {
                        name = String.valueOf(charArray).substring(0, arrayLength);
                    }
                    break;
                } else {
                    System.out.println("Invalid Input !!!"); // if someone enters character other than letters or spaces or underscores
                }
                count = 0;
            }
        }
        return name;
    }

    public void objectCheck(FootballClub obj){
        for (FootballClub club : PremierLeague) {
            if (obj.getClubName().equals(club.getClubName())) {
                System.out.println("\nAlready another club is using \"" + obj.getClubName() +"\" as there name");
                obj.setClubName(inputName("Club name\t: "));
                objectCheck(obj);
            }
        }
        for (FootballClub club : PremierLeague) {
            if(obj.getManager().equals(club.getManager())) {
                System.out.println("\nAlready another club is using \"" + obj.getManager() +"\" as there manager");
                obj.setManager(inputName("Manager\t\t: "));
                objectCheck(obj);
            }
        }
    }

    @Override
    public void deleteClub(){
        if (!PremierLeague.isEmpty()) {
            System.out.println("\nDelete from Premier League");
            int deleteClubIndex = clubIndex("\nClub Name\t: ");
            System.out.println(printName(PremierLeague.get(deleteClubIndex).getClubName()) + " deleted from Premier League");
            PremierLeague.remove(deleteClubIndex);
        }
        else {
            System.out.println("Premier League is Empty");
        }
    }

    @Override
    public void displayClubDetails(){
        if (!PremierLeague.isEmpty()) {
            System.out.println("\nDisplay details of a club in Premier League");
            String clubName = inputName("\nClub name\t: ");
            int count = 0;
            for (FootballClub obj : PremierLeague){
                if(obj.getClubName().equals(clubName)){
                    obj.displayClub();
                    count++;
                }
            }
            if (count == 0){
                System.out.println(printName(clubName)  + " not found in Premier League");
            }
        }
        else {
            System.out.println("Premier League is Empty");
        }
    }

    @Override
    public void displayPLT(){
        System.out.println("\nDisplay Premier League Table");
        Collections.sort(PremierLeague);
        for (FootballClub obj : PremierLeague){
            obj.displayStats();
        }
    }

    // Convert stored name into normal format
    public String printName(String name){
        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '_') {
                charArray[i] = ' ';
            }
        }
        return name = String.valueOf(charArray);
    }

    @Override
    public void addMatch(){
        if (PremierLeague.size() > 1) {
            System.out.println("\nAdd played match");
            Date date = new Date();
            System.out.println("\n-Date-");
            date.setDate();
            System.out.println("Date : " + date.getDate());
            System.out.println("\n-Club Names-");
            int indexHome = clubIndex("\nHome Team\t: "); // Index of the home team in PremierLeague ArrayList
            int indexAway = 0; // Index of the away team in PremierLeague ArrayList
            while (true) {
                indexAway = clubIndex("\nAway Team\t: ");
                if (indexHome == indexAway) {
                    System.out.println(PremierLeague.get(indexHome).getClubName() + " already set as the home team");
                }
                else {
                    break;
                }
            }
            System.out.println("\n-Match Result-");
            int goalsHome = inputInt("\nHome Goals\t: ");
            int goalsAway = inputInt("\nAway Goals\t: ");
            if (goalsHome == goalsAway) {
                PremierLeague.get(indexHome).setDrawn(goalsHome);
                PremierLeague.get(indexAway).setDrawn(goalsAway);
                System.out.println("\nMatch drawn");
            }
            else if (goalsHome > goalsAway) {
                PremierLeague.get(indexHome).setWon(goalsHome, goalsAway);
                PremierLeague.get(indexAway).setLost(goalsAway, goalsHome);
                System.out.println("\n" + PremierLeague.get(indexHome).getClubName() + " match won by " + (goalsHome - goalsAway) + "goals.");
            } else {
                PremierLeague.get(indexHome).setLost(goalsHome, goalsAway);
                PremierLeague.get(indexAway).setWon(goalsAway, goalsHome);
                System.out.println("\n" + PremierLeague.get(indexAway).getClubName() + " match won by " + (goalsAway - goalsHome) + " goals.");
            }
            Match match = new Match(date, date.getDate(), PremierLeague.get(indexHome).getClubName(), PremierLeague.get(indexAway).getClubName(), goalsHome, goalsAway);
            PremierLeagueMatches.add(match);
        }
        else if(PremierLeague.isEmpty()){
            System.out.println("Premier League is Empty");
        }
        else{
            System.out.println("Only one club is in Premier League");
        }
    }

    public int clubIndex(String msg){
        int count = 0; // To check teams in PremierLeague ArrayList or not. If c > 0, team is ArrayList
        int index = 0;
        while (true){
            String club = inputName(msg);
            for (FootballClub obj : PremierLeague){
                if(obj.getClubName().equals(club)){
                    index = PremierLeague.indexOf(obj);
                    count++;
                }
            }
            if (count != 0){
                break;
            }
            else {
                System.out.println(printName(club)  + " not found in Premier League");
            }
        }
        return index;
    }

    public int inputInt(String msg){
        Scanner sc = new Scanner(System.in);
        String input;
        while (true){
            System.out.print(msg);
            input = sc.nextLine();
            try {
                Integer.parseInt(input);
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Invalid Input!!!"); // If someone entered characters other than integers
            }
        }
        return Integer.parseInt(input);
    }

    @Override
    public void saveToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("PremierLeague.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (FootballClub club : PremierLeague) {
                objectOutputStream.writeObject(club);
            }
            objectOutputStream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("PremierLeagueMatches.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Match match : PremierLeagueMatches) {
                objectOutputStream.writeObject(match);
            }
            objectOutputStream.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void loadFromFile() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream("PremierLeague.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            for(;;){
                try{
                    PremierLeague.add((FootballClub)objectInputStream.readObject());
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        catch (FileNotFoundException e){
            System.out.println();
        }
        catch (EOFException e) {
            System.out.println();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("PremierLeagueMatches.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            for(;;){
                try{
                    PremierLeagueMatches.add((Match)objectInputStream.readObject());
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        catch (FileNotFoundException e){
            System.out.println();
        }
        catch (EOFException e) {
            System.out.println();
        }
    }

    // GUI - START

    @Override
    public void GUI(){
        Stage stage = new Stage();
        stage.setTitle("Premier League");

        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);
        layout.setHgap(10);
        layout.setVgap(10);

        Scene scn1 = new Scene(layout, 1080, 720);
        scn1.getStylesheets().add("Stylesheet.css");

        Button btn1 = new Button("All Club Stats");
        Button btn2 = new Button("Random Match Fix");
        Button btn3 = new Button("Matches Played");
        Button btn4 = new Button("Search Match by Date");
        Button btn5 = new Button("Close GUI");

        btn1.getStyleClass().add("menu-btn");
        btn2.getStyleClass().add("menu-btn");
        btn3.getStyleClass().add("menu-btn");
        btn4.getStyleClass().add("menu-btn");
        btn5.getStyleClass().add("menu-btn");


        layout.getChildren().addAll(btn1,btn2,btn3,btn4,btn5);

        GridPane.setConstraints(btn1,0,0);
        GridPane.setConstraints(btn2,0,4);
        GridPane.setConstraints(btn3,0,8);
        GridPane.setConstraints(btn4,0,12);
        GridPane.setConstraints(btn5,0,16);

        btn1.setOnAction(e -> {
            Scene scn2 = new Scene(clubListLayout(stage,scn1), 1080,720);
            scn2.getStylesheets().add("Stylesheet.css");
            stage.setScene(scn2);
        });
        btn2.setOnAction(e -> {
            if (PremierLeague.size() > 1) {
                Scene scn3 = new Scene(randomMatchLayout(stage, scn1), 1080, 720);
                scn3.getStylesheets().add("Stylesheet.css");
                stage.setScene(scn3);
            }
            else if (PremierLeague.isEmpty()){
                AlertBox("Premier League is Empty");
            }
            else {
                AlertBox("Only one team in Premier League");
            }
        });
        btn3.setOnAction(e -> {
            Scene scn4 = new Scene(matchesPlayedLayout(stage,scn1,"Matches Played"), 1080,720);
            scn4.getStylesheets().add("Stylesheet.css");
            stage.setScene(scn4);
        });
        btn4.setOnAction(e -> {
            if(!PremierLeague.isEmpty()) {
                Scene scn5 = new Scene(searchMatchesLayout(stage, scn1, "Search Match by Date"), 1080, 720);
                scn5.getStylesheets().add("Stylesheet.css");
                stage.setScene(scn5);
            }
            else {
                AlertBox("Premier League is Empty");
            }
        });
        btn5.setOnAction(e -> {
            stage.close();
        });

        stage.setScene(scn1);
        stage.showAndWait();
    }

    public GridPane searchMatchesLayout(Stage stage, Scene scn, String title){
        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);

        GridPane layout1 = titleLayout("Search Match by Date");

        GridPane layout2 = new GridPane();
        layout2.setAlignment(CENTER);
        layout2.setMinSize(1080,480);
        layout2.setHgap(10);
        layout2.setVgap(10);

        Label lbl1 = new Label("Input year\t:");
        Label lbl2 = new Label("Input month\t:");
        Label lbl3 = new Label("Input day\t\t:");

        lbl1.getStyleClass().add("data-about");
        lbl2.getStyleClass().add("data-about");
        lbl3.getStyleClass().add("data-about");

        layout2.getChildren().addAll(lbl1,lbl2,lbl3);

        GridPane.setConstraints(lbl1,0,0);
        GridPane.setConstraints(lbl2,0,1);
        GridPane.setConstraints(lbl3,0,2);

        TextField txtF1 = new TextField();
        TextField txtF2 = new TextField();
        TextField txtF3 = new TextField();

        txtF1.getStyleClass().add("text-field");
        txtF2.getStyleClass().add("text-field");
        txtF3.getStyleClass().add("text-field");

        layout2.getChildren().addAll(txtF1,txtF2,txtF3);

        GridPane.setConstraints(txtF1,1,0);
        GridPane.setConstraints(txtF2,1,1);
        GridPane.setConstraints(txtF3,1,2);

        Button btn = new Button("Enter");

        btn.getStyleClass().add("option-btn");

        layout2.getChildren().add(btn);

        GridPane.setConstraints(btn,0,4,2,1,HPos.CENTER,VPos.CENTER);

        btn.setOnAction(e ->{
            String yearInput = txtF1.getText();
            String monthInput = txtF2.getText();
            String dayInput = txtF3.getText();

            if (intCheck(yearInput) && intCheck(monthInput) && intCheck(dayInput)){
                int year = Integer.parseInt(yearInput);
                int month = Integer.parseInt(monthInput);
                int day = Integer.parseInt(dayInput);
                if(yearCheck(year,txtF1)){
                    if(monthCheck(year,month, txtF2)){
                        if (dayCheck(year,month,day,txtF3)){
                            String inputDate = String.format("%04d-%02d-%02d", year, month, day);
                            ArrayList<Match> matchesOnDate = new ArrayList<Match>();
                            for (Match match : PremierLeagueMatches){
                                if(match.getDateInString().equals(inputDate)){
                                    matchesOnDate.add(match);
                                }
                            }
                            if(!matchesOnDate.isEmpty()){
                                Scene scn1 = new Scene(foundMatchLayout(stage,scn,"Descending oder of Points",matchesOnDate));
                                scn1.getStylesheets().add("Stylesheet.css");
                                stage.setScene(scn1);
                            }
                            else {
                                txtF1.clear();
                                txtF2.clear();
                                txtF3.clear();
                                AlertBox("No matches found on this date " + inputDate);
                            }
                        }
                    }
                }
            }
            else {
                txtF1.clear();
                txtF2.clear();
                txtF3.clear();
                AlertBox("Some or all inputs are not integers");
            }

        });

        GridPane layout3 = backBTNLayout(stage,scn);

        layout.getChildren().addAll(layout1, layout2, layout3);

        GridPane.setConstraints(layout1,0,0);
        GridPane.setConstraints(layout2,0,4);
        GridPane.setConstraints(layout3,0,8);

        return layout;
    }

    public GridPane foundMatchLayout(Stage stage, Scene scn, String title, ArrayList<Match> matches){
        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);

        GridPane layout1 = titleLayout(title);

        GridPane layout2 = new GridPane();
        layout2.setAlignment(CENTER);
        layout2.setMinSize(1080,480);
        layout2.getChildren().add(matchTable(matches));

        GridPane layout3 = backBTNLayout(stage,scn);

        layout.getChildren().addAll(layout1, layout2, layout3);

        GridPane.setConstraints(layout1,0,0);
        GridPane.setConstraints(layout2,0,4);
        GridPane.setConstraints(layout3,0,8);

        return layout;
    }

    public boolean yearCheck(int year, TextField txt1F) {
        LocalDate presentDate = LocalDate.now();
        if (year > presentDate.getYear()) {
            txt1F.clear();
            AlertBox("Not a valid year");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean monthCheck(int year, int month, TextField txt2F) {
        LocalDate presentDate = LocalDate.now();
        if (month >= 1 && month <= 12) {
            if (year == presentDate.getYear()) {
                if (month > presentDate.getMonthValue()) {
                    txt2F.clear();
                    AlertBox("Not a valid month");
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            txt2F.clear();
            AlertBox("Not a valid month");
            return false;
        }
    }

    public boolean dayCheck(int year, int month, int day, TextField txt3F){
        LocalDate presentDate = LocalDate.now();
        if (year == presentDate.getYear() && month == presentDate.getMonthValue()){
            if( day > presentDate.getDayOfMonth()){
                txt3F.clear();
                AlertBox("Not a valid day");
                return false;
            }
            else {
                return true;
            }
        }
        else if (month == 1 ||  month == 3 || month == 5 ||  month == 7 || month == 8 ||  month == 10 || month == 12) {
            if (day < 1 || day > 31){
                txt3F.clear();
                AlertBox("Not a valid day");
                return false;
            }
            else {
                return true;
            }
        }
        else {
            if(month == 2){
                if (year%4 == 0) {
                    if (day >= 1 && day <= 29) {
                        return true;
                    }
                    else {
                        txt3F.clear();
                        AlertBox("Not a valid day");
                        return false;
                    }
                }
                else {
                    if (day >= 1 && day <= 28){
                        return true;
                    }
                    else
                    {
                        txt3F.clear();
                        AlertBox("Not a valid day");
                        return false;
                    }
                }
            }
            else {
                if (day >= 1 && day <= 30){
                    return true;
                }
                else {
                    txt3F.clear();
                    AlertBox("Not a valid day");
                    return false;
                }
            }
        }
    }

    //alert box gui
    public void AlertBox(String msg){
        Stage AlertBoxStage = new Stage();
        GridPane AlertBoxPane = new GridPane();
        AlertBoxPane.setAlignment(CENTER);
        AlertBoxPane.setHgap(20);
        AlertBoxPane.setVgap(20);

        Label alertMessage = new Label(msg);
        AlertBoxPane.getChildren().add(alertMessage);
        GridPane.setConstraints(alertMessage, 0, 0);

        Scene AlertBoxScene = new Scene(AlertBoxPane, 600, 150);
        AlertBoxStage.setTitle("Alert !!!");
        AlertBoxStage.setScene(AlertBoxScene);
        AlertBoxStage.showAndWait();
    }

    public boolean intCheck(String input){
        try {
            Integer.parseInt(input);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public TableView matchTable(ArrayList<Match> matches){
        TableView table = new TableView();

        TableColumn col1Title = new TableColumn<>("Date");
        col1Title.setCellValueFactory(new PropertyValueFactory<Match, String>("dateInString"));

        TableColumn col2Title = new TableColumn<>("Home Team");
        col2Title.setCellValueFactory(new PropertyValueFactory<Match, String>("homeClub"));

        TableColumn col3Title = new TableColumn<>("Away Team");
        col3Title.setCellValueFactory(new PropertyValueFactory<Match, String>("awayClub"));

        TableColumn col4Title = new TableColumn<>("Home Goals");
        col4Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("homeGoals"));

        TableColumn col5Title = new TableColumn<>("Away Goals");
        col5Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("awayGoals"));

        table.getColumns().addAll(col1Title,col2Title,col3Title,col4Title,col5Title);

        Collections.sort(matches,Collections.reverseOrder());
        for (Match match : matches) {
            table.getItems().add(new Match(match.getDateInString(),match.getHomeClub(),match.getAwayClub(),match.getHomeGoals(),match.getAwayGoals()));
        }

        return table;
    }

    public GridPane matchesPlayedLayout(Stage stage, Scene scn, String title){
        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);

        GridPane layout1 = titleLayout(title);

        GridPane layout2 = new GridPane();
        layout2.setAlignment(CENTER);
        layout2.setMinSize(1080,480);
        layout2.getChildren().add(matchTable(PremierLeagueMatches));

        GridPane layout3 = backBTNLayout(stage,scn);

        layout.getChildren().addAll(layout1, layout2, layout3);

        GridPane.setConstraints(layout1,0,0);
        GridPane.setConstraints(layout2,0,4);
        GridPane.setConstraints(layout3,0,8);

        return layout;
    }

    public GridPane randomMatchLayout(Stage stage, Scene scn){
        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);

        GridPane layout1 = titleLayout("All Club Stats");

        GridPane layout2 = new GridPane();
        layout2.setAlignment(CENTER);
        layout2.setMinSize(1080,480);
        layout2.setHgap(10);
        layout2.setVgap(30);

        LocalDate presentDate = LocalDate.now();
        Date date = new Date(presentDate.getYear(), presentDate.getMonthValue(), presentDate.getDayOfMonth());

        int indexHome = (int) (Math.random() * (PremierLeague.size()));
        int indexAway = (int) (Math.random() * (PremierLeague.size()));
        while (indexHome == indexAway) {

            indexAway = (int) (Math.random() * (PremierLeague.size()));


        }
        int goalsHome = (int) (Math.random() * 100);
        int goalsAway = (int) (Math.random() * 100);

        if (goalsHome == goalsAway) {
            PremierLeague.get(indexHome).setDrawn(goalsHome);
            PremierLeague.get(indexAway).setDrawn(goalsAway);

            Label lblStatus = new Label("Match drawn");
            lblStatus.getStyleClass().add("data");
            layout2.getChildren().add(lblStatus);
            GridPane.setConstraints(lblStatus,0,6,2,1,HPos.CENTER,VPos.CENTER);
        }

        else if (goalsHome > goalsAway) {
            PremierLeague.get(indexHome).setWon(goalsHome, goalsAway);
            PremierLeague.get(indexAway).setLost(goalsAway, goalsHome);

            Label lblStatus = new Label(PremierLeague.get(indexHome).getClubName() + " match won by " + (goalsHome - goalsAway) + " goals.");
            lblStatus.getStyleClass().add("data");
            layout2.getChildren().add(lblStatus);
            GridPane.setConstraints(lblStatus,0,6,2,1,HPos.CENTER,VPos.CENTER);
        }
        else {
            PremierLeague.get(indexHome).setLost(goalsHome, goalsAway);
            PremierLeague.get(indexAway).setWon(goalsAway, goalsHome);

            Label lblStatus = new Label(PremierLeague.get(indexAway).getClubName() + " match won by " + (goalsAway - goalsHome) + " goals.");
            lblStatus.getStyleClass().add("data");
            layout2.getChildren().add(lblStatus);
            GridPane.setConstraints(lblStatus,0,6,2,1,HPos.CENTER,VPos.CENTER);
        }
        Match match = new Match(date, date.getDate(), PremierLeague.get(indexHome).getClubName(), PremierLeague.get(indexAway).getClubName(), goalsHome, goalsAway);
        PremierLeagueMatches.add(match);

        Label lbl1 = new Label("Date\t\t\t: ");
        Label lbl2 = new Label("Home Team\t: ");
        Label lbl3 = new Label("Away Team\t: ");
        Label lbl4 = new Label("Home Goals\t: ");
        Label lbl5 = new Label("Away Goals\t: ");

        lbl1.getStyleClass().add("data-about");
        lbl2.getStyleClass().add("data-about");
        lbl3.getStyleClass().add("data-about");
        lbl4.getStyleClass().add("data-about");
        lbl5.getStyleClass().add("data-about");

        layout2.getChildren().addAll(lbl1,lbl2,lbl3,lbl4,lbl5);

        GridPane.setConstraints(lbl1,0,0);
        GridPane.setConstraints(lbl2,0,1);
        GridPane.setConstraints(lbl3,0,2);
        GridPane.setConstraints(lbl4,0,3);
        GridPane.setConstraints(lbl5,0,4);

        Label lblDate = new Label(date.getDate());
        Label lblHomeTeam = new Label(PremierLeague.get(indexHome).getClubName());
        Label lblAwayTeam = new Label(PremierLeague.get(indexAway).getClubName());
        Label lblHomeGoals = new Label(String.valueOf(goalsHome));
        Label lblAwayGoals = new Label(String.valueOf(goalsAway));

        lblDate.getStyleClass().add("data");
        lblHomeTeam.getStyleClass().add("data");
        lblAwayTeam.getStyleClass().add("data");
        lblHomeGoals.getStyleClass().add("data");
        lblAwayGoals.getStyleClass().add("data");

        layout2.getChildren().addAll(lblDate,lblHomeTeam,lblAwayTeam,lblHomeGoals,lblAwayGoals);

        GridPane.setConstraints(lblDate,1,0);
        GridPane.setConstraints(lblHomeTeam,1,1);
        GridPane.setConstraints(lblAwayTeam,1,2);
        GridPane.setConstraints(lblHomeGoals,1,3);
        GridPane.setConstraints(lblAwayGoals,1,4);

        GridPane layout3 = backBTNLayout(stage,scn);

        layout.getChildren().addAll(layout1, layout2, layout3);

        GridPane.setConstraints(layout1,0,0);
        GridPane.setConstraints(layout2,0,4);
        GridPane.setConstraints(layout3,0,8);

        return  layout;
    }

    public GridPane clubListLayout(Stage stage, Scene scn){
        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);

        GridPane layout1 = titleLayout("All Club Stats");

        GridPane layout2 = new GridPane();
        layout2.setAlignment(CENTER);
        layout2.setMinSize(1080,480);
        layout2.setHgap(10);
        layout2.setVgap(10);

        Button btn1 = new Button("Descending oder of Points");
        Button btn2 = new Button("Descending oder of Wins");
        Button btn3 = new Button("Descending oder of Goals");

        btn1.getStyleClass().add("option-btn");
        btn2.getStyleClass().add("option-btn");
        btn3.getStyleClass().add("option-btn");

        layout2.getChildren().addAll(btn1,btn2,btn3);

        GridPane.setConstraints(btn1,0,0);
        GridPane.setConstraints(btn2,0,4);
        GridPane.setConstraints(btn3,0,8);

        GridPane layout3 = backBTNLayout(stage,scn);

        btn1.setOnAction(e ->{
            Collections.sort(PremierLeague);
            Scene scn1 = new Scene(listSort(stage,scn,"Descending oder of Points"));
            scn1.getStylesheets().add("Stylesheet.css");
            stage.setScene(scn1);
        });
        btn2.setOnAction(e ->{
            Collections.sort(PremierLeague, Comparator.comparingInt(FootballClub::getWon).reversed());
            Scene scn2 = new Scene(listSort(stage,scn,"Descending oder of Wins"));
            scn2.getStylesheets().add("Stylesheet.css");
            stage.setScene(scn2);
        });
        btn3.setOnAction(e ->{
            Collections.sort(PremierLeague, Comparator.comparingInt(FootballClub::getGoalsF).reversed());
            Scene scn3 = new Scene(listSort(stage,scn,"Descending oder of Goals"));
            scn3.getStylesheets().add("Stylesheet.css");
            stage.setScene(scn3);
        });

        layout.getChildren().addAll(layout1, layout2, layout3);

        GridPane.setConstraints(layout1,0,0);
        GridPane.setConstraints(layout2,0,4);
        GridPane.setConstraints(layout3,0,8);

        return layout;
    }

    public GridPane titleLayout(String title){
        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);
        layout.setMinSize(1080,120);

        Label lbl = new Label(title);
        lbl.getStyleClass().add("title");
        layout.getChildren().add(lbl);
        GridPane.setConstraints(lbl ,0,0);

        return layout;
    }

    public GridPane backBTNLayout(Stage stage, Scene scn){
        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);
        layout.setMinSize(1080,120);

        Button btn = new Button("GUI Menu");
        btn.getStyleClass().add("menu-btn");
        btn.setOnAction(e->{
            stage.setScene(scn);
        });
        layout.getChildren().add(btn);
        GridPane.setConstraints(btn ,0,0);

        return layout;
    }

    public GridPane listSort(Stage stage, Scene scn, String title){
        TableView<FootballClub> table = new TableView<FootballClub>();

        TableColumn col1Title = new TableColumn<>("Club Name");
        col1Title.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("clubName"));

        TableColumn col2Title = new TableColumn<>("Played");
        col2Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("played"));

        TableColumn col3Title = new TableColumn<>("Won");
        col3Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("won"));

        TableColumn col4Title = new TableColumn<>("Drawn");
        col4Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("drawn"));

        TableColumn col5Title = new TableColumn<>("Lost");
        col5Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("lost"));

        TableColumn col6Title = new TableColumn<>("GF");
        col6Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("goalsF"));

        TableColumn col7Title = new TableColumn<>("GA");
        col7Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("goalsA"));

        TableColumn col8Title = new TableColumn<>("GD");
        col8Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("goalsD"));

        TableColumn col9Title = new TableColumn<>("Points");
        col9Title.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("points"));

        table.getColumns().addAll(col1Title,col2Title,col3Title,col4Title,col5Title,col6Title,col7Title,col8Title,col9Title);

        for (FootballClub club : PremierLeague) {
            table.getItems().add(new FootballClub(club.getClubName(),club.getPlayed(),club.getWon(),club.getDrawn(),club.getLost(),club.getGoalsF(),club.getGoalsA(),club.getGoalsD(),club.getPoints()));
        }

        GridPane layout = new GridPane();
        layout.setAlignment(CENTER);

        GridPane layout1 = titleLayout(title);

        GridPane layout2 = new GridPane();
        layout2.setAlignment(CENTER);
        layout2.setMinSize(1080,480);
        layout2.getChildren().add(table);

        GridPane layout3 = backBTNLayout(stage,scn);

        layout.getChildren().addAll(layout1, layout2, layout3);

        GridPane.setConstraints(layout1,0,0);
        GridPane.setConstraints(layout2,0,4);
        GridPane.setConstraints(layout3,0,8);

        return layout;
    }
}