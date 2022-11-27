package net.targus.improvedobsidianmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.targus.improvedobsidianmod.block.ModBlocks;
import net.targus.improvedobsidianmod.networking.ModMessages;
import net.targus.improvedobsidianmod.screen.ModScreenHandlers;
import net.targus.improvedobsidianmod.screen.ObsideriteChestScreen;
import net.targus.improvedobsidianmod.screen.ObsideriteInfusingScreen;
import net.targus.improvedobsidianmod.screen.ObsidianGrinderScreen;

public class ImprovedObsidianModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        HandledScreens.register(ModScreenHandlers.OBSIDERITE_INFUSING_SCREEN_HANDLER, ObsideriteInfusingScreen::new);
        HandledScreens.register(ModScreenHandlers.OBSIDERITE_CHEST_SCREEN_HANDLER, ObsideriteChestScreen::new);
        HandledScreens.register(ModScreenHandlers.OBSIDIAN_GRINDER_SCREEN_HANDLER, ObsidianGrinderScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_GLASS_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_VASE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_FLOWER_POT, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_GLASS_PANE_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_TABLE_LAMP, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_GRINDER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_END_TABLE, RenderLayer.getTranslucent());
    }
}
