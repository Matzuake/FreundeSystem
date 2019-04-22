package net.blocklords.freunde.lang;

public class German {
    public static String getMessage(String code)
    {
        String msg = "Konnte keine Nachricht f§r den Code " + code + " finden!";
        if (code.equalsIgnoreCase("NO_PLAYER")) {
            msg = "Du musst ein Spieler sein, um diesen Befehl auszuf§hren!";
        }
        if (code.equalsIgnoreCase("INVENTORY_FRIENDS")) {
            msg = "§6Deine Freunde";
        }
        if (code.equalsIgnoreCase("MESSAGE_REMOVE")) {
            msg = "§aDu hast erfolgreich §e%FRIEND%§a aus deiner Freundesliste entfernt!";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALLOWINVITES")) {
            msg = "§aDu kannst nun Freundesanfragen bekommen!";
        }
        if (code.equalsIgnoreCase("MESSAGE_DENYINVITES")) {
            msg = "§cDu kannst nun keine Freundesanfragen mehr bekommen!";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALLOWJOINMSG")) {
            msg = "§aDu bekommst nun Nachrichten, wenn ein Freund den Server betretet/verl§sst.";
        }
        if (code.equalsIgnoreCase("MESSAGE_DENYJOINMSG")) {
            msg = "§cDu bekommst nun keine Nachrichten, wenn ein Freund den Server betretet/verl§sst.";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALLOWMSG")) {
            msg = "§aDu kannst nun private Nachrichten bekommen!";
        }
        if (code.equalsIgnoreCase("MESSAGE_DENYMSG")) {
            msg = "§cDu kannst nun keine privaten Nachrichten bekommen!";
        }
        if (code.equalsIgnoreCase("ITEM_BACK")) {
            msg = "§cZur§ck";
        }
        if (code.equalsIgnoreCase("ITEM_REMOVE")) {
            msg = "§cEntfernen";
        }
        if (code.equalsIgnoreCase("ITEM_JUMP")) {
            msg = "§5Zum Freund springen";
        }
        if (code.equalsIgnoreCase("ITEM_TOGGLEINV")) {
            msg = "§3Anfragen togglen";
        }
        if (code.equalsIgnoreCase("ITEM_TOGGLEMSG")) {
            msg = "§3Private Nachrichten togglen";
        }
        if (code.equalsIgnoreCase("ITEM_TOGGLEJOINMSG")) {
            msg = "§3Betreten/Verlassens Nachrichten togglen";
        }
        if (code.equalsIgnoreCase("LORE_PLAYERINFO_NAME")) {
            msg = "§3Name§8: §e%PLAYER%";
        }
        if (code.equalsIgnoreCase("LORE_PLAYERINFO_ONLINE")) {
            msg = "§3Online§8: §e";
        }
        if (code.equalsIgnoreCase("LORE_PLAYERINFO_SERVER")) {
            msg = "§3Server§8: §e%SERVER%";
        }
        if (code.equalsIgnoreCase("YES")) {
            msg = "§aJa";
        }
        if (code.equalsIgnoreCase("NO")) {
            msg = "§cNein";
        }
        return msg;
    }
}
