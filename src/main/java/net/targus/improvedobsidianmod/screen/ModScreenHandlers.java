package net.targus.improvedobsidianmod.screen;

import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<ObsideriteInfusingScreenHandler> OBSIDIAN_INFUSING_SCREEN_HANDLER;
    public static void registerAllScreenHandlers() {
        OBSIDIAN_INFUSING_SCREEN_HANDLER = new ScreenHandlerType<>(ObsideriteInfusingScreenHandler::new);
    }
}
