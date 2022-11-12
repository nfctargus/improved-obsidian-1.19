package net.targus.improvedobsidianmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;

public class ModItems {

    public static final Item OBSIDIAN_SHARD = registerItem("obsidian_shard",
            new Item(new FabricItemSettings().group(ModItemGroup.OBSIDIAN)));
    public static final Item OBSIDERITE_INGOT = registerItem("obsiderite_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.OBSIDIAN)));
    public static final Item OBSIDERITE_DUST = registerItem("obsiderite_dust",
            new Item(new FabricItemSettings().group(ModItemGroup.OBSIDIAN))); //obsiderite_dust
    //Obsidian Tools
    public static final Item OBSIDIAN_PICKAXE = registerItem("obsidian_pickaxe",
            new PickaxeItem(ToolMaterials.DIAMOND,3,-2.8f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));

    public static final Item OBSIDIAN_SWORD = registerItem("obsidian_sword",
            new SwordItem(ToolMaterials.DIAMOND,5,-2.3f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));

    public static final Item OBSIDIAN_AXE = registerItem("obsidian_axe",
            new AxeItem(ToolMaterials.DIAMOND,6,-3f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static final Item OBSIDIAN_SHOVEL = registerItem("obsidian_shovel",
            new ShovelItem(ToolMaterials.DIAMOND,3,-3f,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));

    //Obsidian Armor
    public static final Item OBSIDIAN_HELMET = registerItem("obsidian_helmet",
            new ArmorItem(ModArmorMaterials.OBSIDIAN, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static final Item OBSIDERITE_CHESTPLATE = registerItem("obsiderite_chestplate",
            new ArmorItem(ModArmorMaterials.OBSIDIAN, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static final Item OBSIDIAN_LEGGINGS = registerItem("obsidian_leggings",
            new ArmorItem(ModArmorMaterials.OBSIDIAN, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static final Item OBSIDIAN_BOOTS = registerItem("obsidian_boots",
            new ArmorItem(ModArmorMaterials.OBSIDIAN, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.OBSIDIAN).maxCount(1).fireproof()));
    public static void registerModItems() {
        ImprovedObsidianMod.LOGGER.debug("Registering Mod Items for " + ImprovedObsidianMod.MOD_ID);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ImprovedObsidianMod.MOD_ID,name),item);
    }
}
