package net.targus.improvedobsidianmod.recipe;

import net.targus.improvedobsidianmod.ImprovedObsidianMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(ImprovedObsidianMod.MOD_ID, ObsideriteInfusingRecipe.Serializer.ID),
                ObsideriteInfusingRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(ImprovedObsidianMod.MOD_ID, ObsideriteInfusingRecipe.Type.ID),
                ObsideriteInfusingRecipe.Type.INSTANCE);
    }
}