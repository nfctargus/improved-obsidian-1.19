package net.targus.improvedobsidianmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;
import net.targus.improvedobsidianmod.block.ModBlocks;

public class ModBlockEntities {
    public static BlockEntityType<ObsideriteInfusingBlockEntity> OBSIDERITE_INFUSING_STATION;

    public static void registerBlockEntities() {
        OBSIDERITE_INFUSING_STATION = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ImprovedObsidianMod.MOD_ID, "obsiderite_infusing_station"),
                FabricBlockEntityTypeBuilder.create(ObsideriteInfusingBlockEntity::new,
                        ModBlocks.OBSIDERITE_INFUSING_STATION).build(null));
    }
}