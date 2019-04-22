package net.blocklords.freunde.interfaces;

import java.util.List;

import net.blocklords.freunde.enums.EnumSetting;
import org.bukkit.entity.Player;

public abstract interface IUser extends Player {
    public abstract List<Player> getFriends();

    public abstract void addFriend(User paramUser);

    public abstract void removeFriend(User paramUser);

    public abstract boolean isFriend(User paramUser);

    public abstract void sendInvite(User paramUser);

    public abstract List<Player> getInvites();

    public abstract void removeInvite(User paramUser);

    public abstract boolean isSetting(EnumSetting paramEnumSetting);

    public abstract void setSetting(EnumSetting paramEnumSetting, boolean paramBoolean);

    public abstract String getUserName();

    public abstract String getUUID();

    public abstract boolean isOnline();

    public abstract String getServerName();

    public abstract void setOnline(boolean paramBoolean);
}
