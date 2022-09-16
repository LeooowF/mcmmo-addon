package io.github.leooowf.mcmmoaddon.utils;


import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.ChatColor;

public class PrefixUtils {

    private static final LuckPerms PROVIDER;

    public static String getColoredPrefixFrom(String name, final boolean withName) {

        CachedMetaData metaData = getCachedMetadata(name);

        if (metaData == null) {
            return "§7";
        }

        if (metaData.getPrefix() == null) {
            return "§7";
        }

        return ChatColor.translateAlternateColorCodes('&', metaData.getPrefix() + (withName ? name : ""));
    }

    private static CachedMetaData getCachedMetadata(String userName) {

        User user = PROVIDER.getUserManager().getUser(userName);

        if (user == null) {
            return null;
        }

        return user.getCachedData().getMetaData(QueryOptions.defaultContextualOptions());
    }

    static {
        PROVIDER = LuckPermsProvider.get();
    }
}