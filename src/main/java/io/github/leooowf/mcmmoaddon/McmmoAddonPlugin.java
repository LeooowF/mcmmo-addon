package io.github.leooowf.mcmmoaddon;

import io.github.leooowf.mcmmoaddon.command.SkillsCommand;
import io.github.leooowf.mcmmoaddon.listeners.McMMoListeners;
import io.github.leooowf.mcmmoaddon.views.RankingView;
import io.github.leooowf.mcmmoaddon.views.SkillsView;
import lombok.Getter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.ViewFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class McmmoAddonPlugin extends JavaPlugin {

    @Getter private static McmmoAddonPlugin instance;

    private ViewFrame viewFrame;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        try {

            viewFrame = ViewFrame.of(this, new SkillsView(), new RankingView()).register();

            registerCommands();

            Bukkit.getPluginManager().registerEvents(new McMMoListeners(), this);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            getLogger().severe("Ocorreu um erro na inicialização do plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {

        BukkitFrame bukkitFrame = new BukkitFrame(this);

        bukkitFrame.registerCommands(
                new SkillsCommand()
        );

        MessageHolder messageHolder = bukkitFrame.getMessageHolder();

        messageHolder.setMessage(MessageType.NO_PERMISSION, "§cVocê não tem permissão para executar este comando.");
        messageHolder.setMessage(MessageType.ERROR, "§cUm erro ocorreu! {error}");
        messageHolder.setMessage(MessageType.INCORRECT_USAGE, "§cUtilize /{usage}");
        messageHolder.setMessage(MessageType.INCORRECT_TARGET, "§cVocê não pode utilizar este comando pois ele é direcioado apenas para {target}.");
    }
}