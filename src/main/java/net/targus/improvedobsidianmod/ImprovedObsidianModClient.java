package net.targus.improvedobsidianmod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.targus.improvedobsidianmod.screen.ModScreenHandlers;
import net.targus.improvedobsidianmod.screen.ObsideriteInfusingScreen;

public class ImprovedObsidianModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.OBSIDIAN_INFUSING_SCREEN_HANDLER, ObsideriteInfusingScreen::new);
    }
}
