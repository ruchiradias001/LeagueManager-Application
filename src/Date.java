import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Date implements Serializable {
    private static final long serialVersionUID = 1L;
    private int year;
    private int month;
    private int day;

    public Date(){
    }

    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setDate(){
        LocalDate presentDate = LocalDate.now();
        while (true){
            year = inputInt("\nYear\t\t:");
            if (year <= presentDate.getYear()){
                break;
            }
            else {
                System.out.println("Invalid Input!!!"); // If someone entered future year
            }
        }
        while (true){
            month = inputInt("\nMonth\t\t:");
            if (month >= 1 && month <=12){
                if(year == presentDate.getYear()){
                    if(month <= presentDate.getMonthValue()){
                        break;
                    }
                    else {
                        System.out.println("Invalid Input!!!"); // If someone entered future month
                    }
                }
                else {
                    break;
                }
            }
            else {
                System.out.println("Invalid Input!!!"); // If someone entered invalid number
            }
        }
        while (true){
            day = inputInt("\nDay\t\t:");
            if (year == presentDate.getYear() && month == presentDate.getMonthValue()){
                if( day <= presentDate.getDayOfMonth()){
                    break;
                }
                else {
                    System.out.println("Invalid Input!!!"); // If someone entered int not between 1 and 31
                }
            }
            else if (month == 1 ||  month == 3 || month == 5 ||  month == 7 || month == 8 ||  month == 10 || month == 12) {
                if (day >= 1 && day <= 31){
                    break;
                }
                else {
                    System.out.println("Invalid Input!!!"); // If someone entered int not between 1 and 31
                }
            }
            else {
                if(month == 2){
                    if (year%4 == 0) {
                        if (day >= 1 && day <= 29) {
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid Input!!!");
                        }
                    }
                    else {
                        if (day >= 1 && day <= 28){
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid Input!!!");
                        }
                    }
                }
                else {
                    if (day >= 1 && day <= 30){
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid Input!!!");
                    }
                }
            }
        }
        //System.out.println(String.format("Date\t\t: %04d . %02d . %02d", year, month, day));
    }



    public String getDate(){
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
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
}
