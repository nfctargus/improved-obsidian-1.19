package net.targus.improvedobsidianmod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;


public class ModScreenHandlers {
    public static ScreenHandlerType<ObsideriteInfusingScreenHandler> OBSIDERITE_INFUSING_SCREEN_HANDLER =
            new ExtendedScreenHandlerType<>(ObsideriteInfusingScreenHandler::new);

    public static ScreenHandlerType<ObsideriteChestScreenHandler> OBSIDERITE_CHEST_SCREEN_HANDLER =
            new ExtendedScreenHandlerType<>(ObsideriteChestScreenHandler::new);

    public static void registerAllScreenHandlers() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier(ImprovedObsidianMod.MOD_ID, "obsiderite_infusing"),
                OBSIDERITE_INFUSING_SCREEN_HANDLER);

        Registry.register(Registry.SCREEN_HANDLER, new Identifier(ImprovedObsidianMod.MOD_ID, "obsiderite_chest"),
                OBSIDERITE_CHEST_SCREEN_HANDLER);
    }
}