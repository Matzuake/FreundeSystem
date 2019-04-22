package net.blocklords.freunde.lang;

public class English {
    public static String getMessage(String code)
    {
        String msg = "Can't find a Message for Code :" + code;
        if (code.equalsIgnoreCase("NO_PLAYER")) {
            msg = "You must be a Player to use this Command!";
        }
        if (code.equalsIgnoreCase("MESSAGE_REMOVE")) {
            msg = "§aYou have sucsessfully removed §e%FRIEND%§a from your Friendlist";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALLOWINVITES")) {
            msg = "§aYou can now get friend requests!";
        }
        if (code.equalsIgnoreCase("MESSAGE_DENYINVITES")) {
            msg = "§cYou can now get no more friend requests!";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALLOWJOINMSG")) {
            msg = "§aYou will now recive a Message, when a Friend joins/leavs the Server.";
        }
        if (code.equalsIgnoreCase("MESSAGE_DENYJOINMSG")) {
            msg = "§cYou will not recive a Message, when a Friend joins/leavs the Server..";
        }
        if (code.equalsIgnoreCase("MESSAGE_ALLOWMSG")) {
            msg = "§aYou can now get private messages!";
        }
        if (code.equalsIgnoreCase("MESSAGE_DENYMSG")) {
            msg = "§cYou can't get private messages now!";
        }
        if (code.equalsIgnoreCase("INVENTORY_FRIENDS")) {
            msg = "§6Your Friends";
        }
        if (code.equalsIgnoreCase("ITEM_BACK")) {
            msg = "§cBack";
        }
        if (code.equalsIgnoreCase("ITEM_REMOVE")) {
            msg = "§cRemove";
        }
        if (code.equalsIgnoreCase("ITEM_JUMP")) {
            msg = "§5Jump to Friend";
        }
        if (code.equalsIgnoreCase("ITEM_TOGGLEINV")) {
            msg = "§3Toggle Invites";
        }
        if (code.equalsIgnoreCase("ITEM_TOGGLEMSG")) {
            msg = "§3Toggle Private Messages";
        }
        if (code.equalsIgnoreCase("ITEM_TOGGLEJOINMSG")) {
            msg = "§3Toggle join/leave Messages";
        }
        if (code.equalsIgnoreCase("LORE_PLAYERINFO_NAME")) {
            msg = "§3Playername§8: §e%PLAYER%";
        }
        if (code.equalsIgnoreCase("LORE_PLAYERINFO_ONLINE")) {
            msg = "§3Online§8: §e";
        }
        if (code.equalsIgnoreCase("LORE_PLAYERINFO_SERVER")) {
            msg = "§3Server§8: §e%SERVER%";
        }
        if (code.equalsIgnoreCase("YES")) {
            msg = "§aYes";
        }
        if (code.equalsIgnoreCase("NO")) {
            msg = "§cNo";
        }
        return msg;
    }
}
