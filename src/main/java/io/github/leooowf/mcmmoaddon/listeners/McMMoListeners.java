package io.github.leooowf.mcmmoaddon.listeners;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.util.player.UserManager;
import io.github.leooowf.mcmmoaddon.utils.ActionBar;
import io.github.leooowf.mcmmoaddon.utils.McMMoUtils;
import io.github.leooowf.mcmmoaddon.utils.PrefixUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.github.paperspigot.Title;

public class McMMoListeners implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    void onMcMMOPlayerXpGain(McMMOPlayerXpGainEvent event) {

        int xp = (int)((double)event.getRawXpGained() * McMMoUtils.getBonusXP(event.getPlayer()));

        event.setRawXpGained(xp);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    void onMcMMOPlayerLevelUp(McMMOPlayerLevelUpEvent event) {

        Player player = event.getPlayer();

        String level = String.valueOf(event.getSkillLevel());

        player.sendTitle(Title.builder()
                .fadeIn(5)
                .fadeOut(15)
                .stay(20)
                .title("§e§l" + event.getSkill().getName().toUpperCase())
                .subtitle("§fSeu nível aumentou em 1!")
                .build());

        if (level.endsWith("00")) {
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(PrefixUtils.getColoredPrefixFrom(player.getName(), true) + "§f atingiu o nível §7" + event.getSkillLevel() + "§f em " + event.getSkill().getName() + ".");
            Bukkit.broadcastMessage("");
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    void onMcMMOPlayerXpGain2(McMMOPlayerXpGainEvent event) {

        Player player = event.getPlayer();

        SkillType skill = event.getSkill();

        if (player.isOnline()) {

            McMMOPlayer mcPlayer = UserManager.getPlayer(player);

            String skillName = skill.getName().replace("Mineracao", "Mineração");

            int skillLevel = event.getSkillLevel();
            int skillXpLevel = mcPlayer.getSkillXpLevel(skill);
            int xpToLevel = mcPlayer.getXpToLevel(skill);

            double finalExp = event.getXpGained() * McMMoUtils.getBonusXP(event.getPlayer());

            String actionBarText = String.format(
                    "%s%s: %s (%s/%s) +%s XP",
                    ChatColor.GREEN,
                    skillName,
                    skillLevel,
                    skillXpLevel,
                    xpToLevel,
                    finalExp
            );

            ActionBar.sendActionBar(player, actionBarText);
        }
    }
}