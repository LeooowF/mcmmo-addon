package io.github.leooowf.mcmmoaddon.views;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;
import io.github.leooowf.mcmmoaddon.McmmoAddonPlugin;
import io.github.leooowf.mcmmoaddon.utils.ItemBuilder;
import io.github.leooowf.mcmmoaddon.utils.McMMoUtils;
import io.github.leooowf.mcmmoaddon.utils.PrefixUtils;
import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class SkillsView extends View {

    public SkillsView() {

        super(5, "Skills");

        setCancelOnClick(true);
        setCancelOnDrag(true);
        setCancelOnDrop(true);
        setCancelOnPickup(true);
        setCancelOnShiftClick(true);
        setCloseOnOutsideClick(true);
    }

    @Override
    protected void onRender(@NotNull ViewContext context) {

        context.slot(12).withItem(new ItemBuilder(Material.SKULL_ITEM)
                .data(3)
                .name(PrefixUtils.getColoredPrefixFrom(context.getPlayer().getName(), true))
                .lore(
                        "§9[⚒]§f Nível total: §7" + ExperienceAPI.getPowerLevel(context.getPlayer()),
                        "§f1º Colocado no rank: " + McMMoUtils.getFirst()
                )
                .build()
        );

        context.slot(14).withItem(new ItemBuilder(Material.ANVIL)
                        .name("§aRanking de Habilidades")
                        .lore("§7Clique para abrir o ranking.")
                        .build())
                .onClick(clickContext -> McmmoAddonPlugin.getInstance().getViewFrame().open(RankingView.class, clickContext.getPlayer()));

        context.slot(20).withItem(new ItemBuilder(Material.DIAMOND_SWORD)
                .name("§eEspadas")
                .lore("§fNível: §7" + ExperienceAPI.getLevel(context.getPlayer(), SkillType.SWORDS.name()),
                        "§fBônus VIP: §7" + McMMoUtils.getBonusXP(context.getPlayer()),
                        "",
                        "§f1º Colocado no rank: " + McMMoUtils.getFirst(SkillType.SWORDS))
                .build());

        context.slot(21).withItem( new ItemBuilder(Material.DIAMOND_AXE)
                .name("§eMachados")
                .lore("§fNível: §7" + ExperienceAPI.getLevel(context.getPlayer(), SkillType.AXES.name()),
                        "§fBônus VIP: §7" + McMMoUtils.getBonusXP(context.getPlayer()),
                        "",
                        "§f1º Colocado no rank: " + McMMoUtils.getFirst(SkillType.AXES))
                .build());

        context.slot(22, new ItemBuilder(Material.DIAMOND_BOOTS)
                .name("§eAcrobacias")
                .lore("§fNível: §7" + ExperienceAPI.getLevel(context.getPlayer(), SkillType.ACROBATICS.name()),
                        "§fBônus VIP: §7" + McMMoUtils.getBonusXP(context.getPlayer()),
                        "",
                        "§f1º Colocado no rank: " + McMMoUtils.getFirst(SkillType.ACROBATICS))
                .build());

        context.slot(23, new ItemBuilder(Material.DIAMOND_PICKAXE)
                .name("§eMineração")
                .lore("§fNível: §7" + ExperienceAPI.getLevel(context.getPlayer(), SkillType.MINING.name()),
                        "§fBônus VIP: §7" + McMMoUtils.getBonusXP(context.getPlayer()),
                        "",
                        "§f1º Colocado no rank: " + McMMoUtils.getFirst(SkillType.MINING))
                .build());

        context.slot(24, new ItemBuilder(Material.DIAMOND_SPADE)
                .name("§eEscavação")
                .lore("§fNível: §7" + ExperienceAPI.getLevel(context.getPlayer(), SkillType.EXCAVATION.name()),
                        "§fBônus VIP: §7" + McMMoUtils.getBonusXP(context.getPlayer()),
                        "",
                        "§f1º Colocado no rank: " + McMMoUtils.getFirst(SkillType.EXCAVATION))
                .build());

        context.slot(31, new ItemBuilder(Material.SEEDS)
                .name("§eHerbalismo")
                .lore("§fNível: §7" + ExperienceAPI.getLevel(context.getPlayer(), SkillType.HERBALISM.name()),
                        "§fBônus VIP: §7" + McMMoUtils.getBonusXP(context.getPlayer()),
                        "",
                        "§f1º Colocado no rank: " + McMMoUtils.getFirst(SkillType.HERBALISM))
                .build());
    }
}