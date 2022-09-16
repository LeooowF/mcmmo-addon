package io.github.leooowf.mcmmoaddon.utils;

import com.gmail.nossr50.datatypes.skills.*;
import com.gmail.nossr50.datatypes.database.*;
import com.gmail.nossr50.database.*;
import org.bukkit.entity.Player;

import java.util.*;

public class McMMoUtils {

    private static final DatabaseManager DATABASE_MANAGER = DatabaseManagerFactory.getDatabaseManager();

    public static List<String> getTopLore(SkillType skillType) {

        List<String> lore = new ArrayList<>();

        readTop(skillType, 10).stream().sorted((o1, o2) -> Integer.compare(o2.statVal, o1.statVal)).forEach($ -> lore.add(PrefixUtils.getColoredPrefixFrom($.name, false)+ $.name + ": §f" + $.statVal));

        return lore;
    }

    public static int getPlayerPosition(String name, SkillType skillType) {

        Map<SkillType, Integer> skillTypeIntegerMap = DATABASE_MANAGER.readRank(name);

        if (skillTypeIntegerMap == null
                || !skillTypeIntegerMap.containsKey(skillType)) {
            return 0;
        }

        return skillTypeIntegerMap.get(skillType);
    }

    private static List<PlayerStat> readTop(SkillType skillType, int range) {
        return DATABASE_MANAGER.readLeaderboard(skillType, 1, range);
    }

    public static String getFirst(SkillType skillType) {

        Optional<PlayerStat> first = Optional.empty();

        Iterator<PlayerStat> iterator = readTop(skillType, 1).iterator();

        if (iterator.hasNext()) {
            PlayerStat playerStat = iterator.next();
            first = Optional.of(playerStat);
        }

        return first.map(playerStat -> PrefixUtils.getColoredPrefixFrom(playerStat.name, false) + playerStat.name).orElse("§7Ninguém");
    }

    public static String getFirst() {

        Optional<PlayerStat> first = Optional.empty();

        Iterator<PlayerStat> iterator = readTop(null, 1).iterator();

        if (iterator.hasNext()) {
            PlayerStat playerStat = iterator.next();
            first = Optional.of(playerStat);
        }

        return first.map(playerStat -> PrefixUtils.getColoredPrefixFrom(playerStat.name, false) + playerStat.name).orElse("§7Ninguém");
    }

    public static Double getBonusXP(Player player) {

        if (player.hasPermission("bonus.celestial")) { return 2.0; }

        if (player.hasPermission("bonus.mestre")) { return 1.75; }

        if (player.hasPermission("bonus.imperador")) { return 1.25; }

        if (player.hasPermission("group.desafiante")) { return 1.5; } else { return 1.0; }
    }
}