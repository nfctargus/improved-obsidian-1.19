package net.targus.improvedobsidianmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;
import net.targus.improvedobsidianmod.block.custom.*;
import net.targus.improvedobsidianmod.item.ModItemGroup;

public class ModBlocks {

/* --------------------- Decorative Blocks --------------------- */
    public static final Block OBSIDIAN_GLASS_BLOCK = registerBlock("obsidian_glass",
            new ObsidianGlassBlock(FabricBlockSettings.of(Material.GLASS)
                    .strength(0.2F,600.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()),ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_GLASS_PANE_BLOCK = registerBlock("obsidian_glass_pane",
            new PaneBlock(FabricBlockSettings.of(Material.GLASS)
                    .strength(0.2F,600.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()),ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_BLOCK = registerBlock("obsidian_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F,600.0F).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDERITE_BLOCK = registerBlock("obsiderite_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F,600.0F).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block COMPRESSED_OBSIDIAN = registerBlock("compressed_obsidian",
            new Block(FabricBlockSettings.of(Material.STONE).strength(5.0F,600.0F).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_TABLE_LAMP = registerBlock("obsidian_table_lamp",
            new Block(FabricBlockSettings.of(Material.STONE).strength(0.2F,600.0F).requiresTool().sounds(BlockSoundGroup.GLASS)
                    .luminance(15).nonOpaque()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_END_TABLE = registerBlock("obsidian_end_table",
            new Block(FabricBlockSettings.of(Material.STONE).strength(2.0F,600.0F).nonOpaque().requiresTool()), ModItemGroup.OBSIDIAN);

    public static final Block OBSIDIAN_FLOWER_POT = registerBlock("obsidian_flower_pot",
            new ObsidianFlowerPotBlock(Blocks.AIR,FabricBlockSettings.of(Material.STONE)
                    .nonOpaque()), ModItemGroup.OBSIDIAN);
    //Stonecutter blocks
    public static final Block OBSIDIAN_STAIRS = registerBlock("obsidian_stairs",
            new StairsBlock(ModBlocks.OBSIDIAN_BLOCK.getDefaultState(),
                    FabricBlockSettings.of(Material.STONE).strength(2.0F,600.0F).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_SLAB = registerBlock("obsidian_slab",
            new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(2.0F,600.0F).requiresTool()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDIAN_WALL = registerBlock("obsidian_wall",
            new WallBlock(FabricBlockSettings.of(Material.STONE).strength(2.0F,600.0F).requiresTool()), ModItemGroup.OBSIDIAN);
    /* --------------------- [End] Decorative Blocks --------------------- */

    //Obsidian Infusing Station
    public static final Block OBSIDERITE_INFUSING_STATION = registerBlock("obsiderite_infusing_station",
            new ObsideriteInfusingStationBlock(FabricBlockSettings.of(Material.STONE)
                    .strength(3.0F,600.0F).requiresTool().nonOpaque()), ModItemGroup.OBSIDIAN);
    public static final Block OBSIDERITE_CHEST = registerBlock("obsiderite_chest",
            new ObsideriteChestBlock(FabricBlockSettings.of(Material.STONE)
                    .strength(3.0F,600.0F).requiresTool().nonOpaque()), ModItemGroup.OBSIDIAN);



    public static final Block OBSIDIAN_GRINDER = registerBlock("obsidian_grinder",
            new ObsidianGrinderBlock(FabricBlockSettings.of(Material.STONE)
                    .strength(3.0F,600.0F).requiresTool().nonOpaque()), ModItemGroup.OBSIDIAN);

    public static final Block OBSIDIAN_LAMP = registerBlock("obsidian_lamp",
            new ObsidianLampBlock(FabricBlockSettings.of(Material.STONE).strength(0.2F,600.0F).requiresTool()
                    .luminance(state -> state.get(ObsidianLampBlock.LIT) ? 15 : 0).sounds(BlockSoundGroup.GLASS)), ModItemGroup.OBSIDIAN);



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
