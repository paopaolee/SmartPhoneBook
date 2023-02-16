package utils;

import beans.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    public static final String NUMBER_REGEX = "^\\d+(\\.\\d)?$";
    public static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    public static final String URL_REGEX = "<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>";
    public static Validator<String> getEmailValidator() {
        return val -> {
            Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        };
    }

    public static Validator<String> getUrlValidator() {
        return val -> {
            Pattern pattern = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        };
    }
}
