package net.blocklords.freunde.lang;

import net.blocklords.freunde.Main;

public class MessageHandler {
    public static String getMessage(String code)
    {
        String message = "Cant find language! Please select 'English' or 'German'";
        if (Main.lang.equalsIgnoreCase("English")) {
            message = English.getMessage(code);
        }
        if (Main.lang.equalsIgnoreCase("German")) {
            message = German.getMessage(code);
        }
        return message;
    }
}
