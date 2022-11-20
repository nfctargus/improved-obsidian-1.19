package net.targus.improvedobsidianmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;
import net.targus.improvedobsidianmod.item.custom.ObsideriteHoeItem;

public class ModItems {

    public static final Item OBSIDIAN_SHARD = registerItem("obsidian_shard",
            new Item(new FabricItemSettings().group(ModItemGroup.OBSIDIAN)));
    public static final Item OBSIDERITE_INGOT = registerItem("obsiderite_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.OBSIDIAN)));
    public static final Item OBSIDERITE_DUST = registerItem("obsiderite_dust",
            new Item(new FabricItemSettings().group(ModItemGroup.OBSIDIAN)));
    public static final Item OBSIDIAN_DUST = registerItem("obsidian_dust",
            new Item(new FabricItemSettings().group(ModItemGroup.OBSIDIAN)));
    //Obsidian Tools
    public static final Item OBSIDERITE_PICKAXE = registerItem("obsiderite_pickaxe",
            new PickaxeItem(ToolMaterials.DIAMOND,3,-2.8f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));

    public static final Item OBSIDERITE_SWORD = registerItem("obsiderite_sword",
            new SwordItem(ToolMaterials.DIAMOND,5,-2.3f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));

    public static final Item OBSIDERITE_HOE = registerItem("obsiderite_hoe",
            new ObsideriteHoeItem(ToolMaterials.DIAMOND,5,-2.3f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));

    public static final Item OBSIDERITE_AXE = registerItem("obsiderite_axe",
            new AxeItem(ToolMaterials.DIAMOND,6,-3f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static final Item OBSIDERITE_SHOVEL = registerItem("obsiderite_shovel",
            new ShovelItem(ToolMaterials.DIAMOND,3,-3f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));

    //Obsidian Armor
    public static final Item OBSIDERITE_HELMET = registerItem("obsiderite_helmet",
            new ArmorItem(ModArmorMaterials.OBSIDERITE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static final Item OBSIDERITE_CHESTPLATE = registerItem("obsiderite_chestplate",
            new ArmorItem(ModArmorMaterials.OBSIDERITE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static final Item OBSIDERITE_LEGGINGS = registerItem("obsiderite_leggings",
            new ArmorItem(ModArmorMaterials.OBSIDERITE, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static final Item OBSIDERITE_BOOTS = registerItem("obsiderite_boots",
            new ArmorItem(ModArmorMaterials.OBSIDERITE, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static void registerModItems() {
        ImprovedObsidianMod.LOGGER.debug("Registering Mod Items for " + ImprovedObsidianMod.MOD_ID);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ImprovedObsidianMod.MOD_ID,name),item);
    }
}
