package ch.hslu.sweng.group3;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionCheck {

    public static boolean isValuePositiveNumber(String stringToBeChecked){
        boolean returnVal = true;
        String regex = "^[1-9]+[0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToBeChecked);
        if(matcher.matches()==false){
            InfoBox.infoBox("Please enter a valid number value.", "Number is not valid");
            returnVal = false;
        }
        return returnVal;
    }

    public static boolean isValidDateFormat(String dateToCheck, String timeToCheck) {
        boolean returnVal;
        String regexDate = "^(?:[0-9][0-9])?[0-9][0-9].[0-1][0-9].[0-3][0-9]$";
        String regexTime = "^[0-2][0-9].[0-5][0-9]$";
        Pattern patternDate = Pattern.compile(regexDate);
        Pattern patternTime = Pattern.compile(regexTime);

        Matcher matcherDate = patternDate.matcher(dateToCheck);
        Matcher matcherTime = patternTime.matcher(timeToCheck);


        if(matcherDate.matches()==true && matcherTime.matches() == true){

            returnVal = true;
        }else{
            InfoBox.infoBox("The entered Date or Time is entered in a wrong format. Please enter it correctly.", "Wrong Date Format used");
            returnVal = false;
        }

        return returnVal;
    }

    public static boolean isDateInFuture(Date enteredDate, SimpleDateFormat format) {
        Date today = new Date();
        boolean returnVal;

        //compare both dates
        if(enteredDate.after(today) || (enteredDate.equals(today))){
            returnVal = true;
        }else{
            returnVal = false;
            InfoBox.infoBox("The entered Date or Time is not in the future. You can't plan a Show in the past", "Date must be in future.");
        }
        return returnVal;
    }

    public static boolean isValueAnEmail(String email){
        boolean returnVal = true;
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()==false){
            InfoBox.infoBox("Please enter a valid email address.", "Email invalid");
            returnVal = false;
        }
        return returnVal;
    }

}
