package io.github.leooowf.mcmmoaddon.views;

import com.gmail.nossr50.datatypes.skills.SkillType;
import io.github.leooowf.mcmmoaddon.McmmoAddonPlugin;
import io.github.leooowf.mcmmoaddon.utils.ItemBuilder;
import io.github.leooowf.mcmmoaddon.utils.McMMoUtils;
import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class RankingView extends View {

    public RankingView() {

        super(6, "Ranking de Skills");

        setCancelOnClick(true);
        setCancelOnDrag(true);
        setCancelOnDrop(true);
        setCancelOnPickup(true);
        setCancelOnShiftClick(true);
        setCloseOnOutsideClick(true);
    }

    @Override
    protected void onRender(@NotNull ViewContext context) {

        context.slot(13, new ItemBuilder(Material.BOOK)
                .name("§eRanking Global")
                .lore(McMMoUtils.getTopLore(null))
                .build());

        context.slot(20, new ItemBuilder(Material.DIAMOND_SWORD)
                .name("§eEspadas")
                .lore(McMMoUtils.getTopLore(SkillType.SWORDS))
                .build());

        context.slot(21, new ItemBuilder(Material.DIAMOND_AXE)
                .name("§eMachados")
                .lore(McMMoUtils.getTopLore(SkillType.AXES))
                .build());

        context.slot(22, new ItemBuilder(Material.DIAMOND_BOOTS)
                .name("§eAcrobacias")
                .lore(McMMoUtils.getTopLore(SkillType.ACROBATICS))
                .build());

        context.slot(23, new ItemBuilder(Material.DIAMOND_PICKAXE)
                .name("§eMineração")
                .lore(McMMoUtils.getTopLore(SkillType.MINING))
                .build());

        context.slot(24, new ItemBuilder(Material.DIAMOND_SPADE)
                .name("§eEscavação")
                .lore(McMMoUtils.getTopLore(SkillType.EXCAVATION))
                .build());

        context.slot(31, new ItemBuilder(Material.SEEDS)
                .name("§eHerbalismo")
                .lore(McMMoUtils.getTopLore(SkillType.HERBALISM))
                .build());

        context.slot(49, new ItemBuilder(Material.ARROW)
                        .name("§aVoltar")
                        .build())
                .onClick(clickContext -> McmmoAddonPlugin.getInstance().getViewFrame().open(SkillsView.class, clickContext.getPlayer()));
    }
}