package ch.hslu.sweng.group3;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionCheck {

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
}
