package net.targus.improvedobsidianmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;
import net.targus.improvedobsidianmod.item.ModItemGroup;
import net.targus.improvedobsidianmod.item.custom.ObsidianLampBlock;

public class ModBlocks {
    public static final Block OBSIDIAN_BLOCK = registerBlock("obsidian_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(10f).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block REFINED_OBSIDIAN = registerBlock("refined_obsidian",
            new Block(FabricBlockSettings.of(Material.STONE).strength(10f).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_LAMP = registerBlock("obsidian_lamp",
            new ObsidianLampBlock(FabricBlockSettings.of(Material.STONE).strength(10f).requiresTool()
                    .luminance(state -> state.get(ObsidianLampBlock.LIT) ? 15 : 0)), ModItemGroup.OBSIDIAN);
    //Stonecutter blocks
    public static final Block OBSIDIAN_STAIRS = registerBlock("obsidian_stairs",
            new StairsBlock(ModBlocks.OBSIDIAN_BLOCK.getDefaultState(),
                    FabricBlockSettings.of(Material.STONE).strength(10f).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_SLAB = registerBlock("obsidian_slab",
            new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(10f).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_WALL = registerBlock("obsidian_wall",
            new WallBlock(FabricBlockSettings.of(Material.STONE).strength(10f).requiresTool()), ModItemGroup.OBSIDIAN);
    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name,block,tab);
        return Registry.register(Registry.BLOCK, new Identifier(ImprovedObsidianMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(ImprovedObsidianMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }
    public static void registerModBlocks() {
        ImprovedObsidianMod.LOGGER.debug("Registering ModBlocks for "+ ImprovedObsidianMod.MOD_ID);
    }
}
