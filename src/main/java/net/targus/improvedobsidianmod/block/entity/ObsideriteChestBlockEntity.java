package net.targus.improvedobsidianmod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.targus.improvedobsidianmod.screen.ObsideriteChestScreenHandler;
import org.jetbrains.annotations.Nullable;

public class ObsideriteChestBlockEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory;
    private static final int INVENTORY_SIZE = 54; // 9 * 6 = 54
    public ObsideriteChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.OBSIDERITE_CHEST,blockPos, blockState);
        this.inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() { return this.inventory; }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) { this.inventory = list; }

    @Override
    protected Text getContainerName() { return Text.translatable("obsiderite_chest"); }

    @Override
    public int size() { return INVENTORY_SIZE; }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new ObsideriteChestScreenHandler(syncId, playerInventory, this);
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new ObsideriteChestScreenHandler(syncId, inv, this);
    }



}
