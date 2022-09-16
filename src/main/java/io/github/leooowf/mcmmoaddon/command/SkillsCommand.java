package io.github.leooowf.mcmmoaddon.command;

import io.github.leooowf.mcmmoaddon.McmmoAddonPlugin;
import io.github.leooowf.mcmmoaddon.views.SkillsView;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public class SkillsCommand {

    @Command(
            name = "skills",
            aliases = {"mctop", "mcmmotop", "mcrank", "mcmmorank"},
            target = CommandTarget.PLAYER
    )
    public void skillsCommand(Context<Player> context) {
        McmmoAddonPlugin.getInstance().getViewFrame().open(SkillsView.class, context.getSender());
    }
}