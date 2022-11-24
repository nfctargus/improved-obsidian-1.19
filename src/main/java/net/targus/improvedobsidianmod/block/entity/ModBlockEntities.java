package net.targus.improvedobsidianmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;
import net.targus.improvedobsidianmod.block.ModBlocks;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntities {
    public static BlockEntityType<ObsideriteInfusingBlockEntity> OBSIDERITE_INFUSING_STATION;
    public static BlockEntityType<ObsideriteChestBlockEntity> OBSIDERITE_CHEST;
    public static BlockEntityType<ObsidianGrinderBlockEntity> OBSIDIAN_GRINDER;

    public static void registerBlockEntities() {
        OBSIDERITE_INFUSING_STATION = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ImprovedObsidianMod.MOD_ID, "obsiderite_infusing_station"),
                FabricBlockEntityTypeBuilder.create(ObsideriteInfusingBlockEntity::new,
                        ModBlocks.OBSIDERITE_INFUSING_STATION).build(null));
        OBSIDERITE_CHEST = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ImprovedObsidianMod.MOD_ID, "obsiderite_chest"),
                FabricBlockEntityTypeBuilder.create(ObsideriteChestBlockEntity::new,
                        ModBlocks.OBSIDERITE_CHEST).build(null));
        OBSIDIAN_GRINDER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ImprovedObsidianMod.MOD_ID, "obsidian_grinder"),
                FabricBlockEntityTypeBuilder.create(ObsidianGrinderBlockEntity::new,
                        ModBlocks.OBSIDIAN_GRINDER).build(null));

        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage,OBSIDERITE_INFUSING_STATION);
    }
}