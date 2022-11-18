package net.targus.improvedobsidianmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.targus.improvedobsidianmod.block.ModBlocks;
import net.targus.improvedobsidianmod.networking.ModMessages;
import net.targus.improvedobsidianmod.screen.ModScreenHandlers;
import net.targus.improvedobsidianmod.screen.ObsideriteChestScreen;
import net.targus.improvedobsidianmod.screen.ObsideriteChestScreenHandler;
import net.targus.improvedobsidianmod.screen.ObsideriteInfusingScreen;

public class ImprovedObsidianModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        HandledScreens.register(ModScreenHandlers.OBSIDERITE_INFUSING_SCREEN_HANDLER, ObsideriteInfusingScreen::new);
        HandledScreens.register(ModScreenHandlers.OBSIDERITE_CHEST_SCREEN_HANDLER, ObsideriteChestScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_GLASS_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OBSIDIAN_GLASS_PANE_BLOCK, RenderLayer.getTranslucent());
    }
}
