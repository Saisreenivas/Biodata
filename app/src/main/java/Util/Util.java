package Util;

import java.text.DecimalFormat;

/**
 * Created by Sai sreenivas on 3/5/2017.
 */

public class Util {
    public static String formatDate(String value){
        DecimalFormat formatter = new DecimalFormat("##-##-####");
        String formatted = formatter.format(value);
        return formatted;
    }
}
