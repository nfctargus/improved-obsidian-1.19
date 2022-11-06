package net.targus.improvedobsidianmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;

public class ModItemGroup {
    public static final ItemGroup OBSIDIAN = FabricItemGroupBuilder.build(new Identifier(ImprovedObsidianMod.MOD_ID,"obsidian"),
            () -> new ItemStack(ModItems.OBSIDIAN_PICKAXE));
}
