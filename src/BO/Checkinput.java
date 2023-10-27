package BO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.IntPredicate;
import jdk.nashorn.internal.parser.Lexer;

public class Checkinput {

    private Scanner sc = new Scanner(System.in);

    public int getIntStore(String msg, String outRangeMsg, int min, int max) {
        String input;
        int result;
        do {
            System.out.print(msg);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
            } else {
                try {
                    result = Integer.parseInt(input);
                    if (result >= min && result <= max) {
                        break;
                    } else {
                        System.out.println(outRangeMsg);
                        continue;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Input must be integer");
                }
            }
        } while (true);
        return result;
    }

    public int getInput(String msg) {
        int input;
        while (true) {
            try {
                System.out.print(msg);
                input = Integer.parseInt(sc.nextLine().trim());
                if (input < 1 || input > 6) {
                    System.out.println("Please enter a number from 1 - 5.");
                }
                return input;
            } catch (Exception e) {
                System.out.println("Please enter a number. ");
            }
        }
    }

    public int getInt(String msg, String outRangeMsg, int min, int max) {
        String input;
        int result;
        do {
            System.out.print(msg);
            input = sc.nextLine().trim().toUpperCase();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
            } else {
                try {
                    result = Integer.parseInt(input);
                    if (result >= min && result <= max) {
                        break;
                    } else {
                        System.out.println(outRangeMsg);
                        continue;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(outRangeMsg);
                }
            }
        } while (true);
        return result;
    }

    public double getDouble(String msg) {
        double input;
        while (true) {
            try {
                System.out.print(msg);
                input = Double.parseDouble(sc.nextLine().trim());
                if (input < 0) {
                    System.out.println("Please enter a price more than 0.");
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Please enter a pos number.");
            }
        }
    }

    public String getStringAdd(String msg, String formatMsg, String regex) {
        String input;
        do {
            System.out.print(msg);
            input = sc.nextLine().toUpperCase().trim();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
                continue;
            } else {
                if (regex.isEmpty()) {
                    break;
                } else if (input.matches(regex)) {
                    break;
                } else {
                    System.out.println(formatMsg);
                    continue;
                }
            }
        } while (true);
        return input;
    }

    public String getString(String msg) {
        String input = "";
        do {
            System.out.print(msg);
            input = sc.nextLine().toUpperCase().trim();
            if (input.length() == 0) {
                System.out.println("Don't see value.");
            }
        } while (input.length() == 0);
        return input;
    }

    public Date getDate(String msg) {
        String input;
        Date date;
        do {
            System.out.print(msg);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
                continue;
                // \\d{1,2} đại diện cho 1 hoặc 2 chữ số .... ( d = từ 0 - 9) 
            } else if (!input.matches("\\d{1,2}[/]\\d{1,2}[/]\\d{4}")) {
                System.out.println("Input is wrong format");
                continue;
            }
            try {
                date = dateFormat.parse(input);
                break;
            } catch (ParseException exception) {
                System.out.println("Date doesn't existed!!");
            }
        } while (true);
        return date;
    }

    public double Updatesalary(String msg, double oldprice) {
        String input = "";
        double data = 0;
        boolean isDigit;
        do {
            try {
                System.out.print(msg);
                input = sc.nextLine();
                if (input.isEmpty()) {
                    data = oldprice;
                    break;
                }
                data = Double.parseDouble(input);
                isDigit = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid value.");
                isDigit = false;

            }
        } while (isDigit == false);
        return data;
    }

    public String UpdateNamecate(String msg, String text, String formatMsg, String regex) {
        String input;
        do {
            System.out.print(msg);
            input = sc.nextLine().toUpperCase().trim();
            if (input.isEmpty()) {
                return text;
            } else {
                if (regex.isEmpty()) {
                    break;
                } else if (input.matches(regex)) {
                    break;
                } else {
                    System.out.println(formatMsg);
                    continue;
                }
            }
        } while (true);
        return input;
    }

    public Date UpdateDate(String msg, Date olddate) {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        String input = "";
        Date data = new Date();
        boolean isdate;
        do {
            try {
                System.out.print(msg);
                input = sc.nextLine();
                if (input.isEmpty()) {
                    return olddate;
                }
                if (!input.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                    System.out.println("Input is wrong format");
                    isdate = false;
                    continue;
                }
                data = sf.parse(input);
                isdate = true;
            } catch (ParseException e) {
                System.out.println("Invalid value.");
                isdate = false;
            }
        } while (isdate == false);
        return data;
    }
}
