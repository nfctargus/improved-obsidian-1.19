package net.targus.improvedobsidianmod;

import net.fabricmc.api.ModInitializer;
import net.targus.improvedobsidianmod.block.ModBlocks;
import net.targus.improvedobsidianmod.block.entity.ModBlockEntities;
import net.targus.improvedobsidianmod.item.ModItems;
import net.targus.improvedobsidianmod.screen.ModScreenHandlers;
import net.targus.improvedobsidianmod.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImprovedObsidianMod implements ModInitializer {
	public static final String MOD_ID = "improvedobsidianmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModLootTableModifiers.modifyLootTables();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerAllScreenHandlers();
	}
}
