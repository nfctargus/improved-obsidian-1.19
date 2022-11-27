package net.targus.improvedobsidianmod.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.targus.improvedobsidianmod.item.ModItems;
import net.targus.improvedobsidianmod.screen.ObsidianGrinderScreenHandler;
import org.jetbrains.annotations.Nullable;

public class ObsidianGrinderBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public ObsidianGrinderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OBSIDIAN_GRINDER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return ObsidianGrinderBlockEntity.this.progress;
                    case 1: return ObsidianGrinderBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: ObsidianGrinderBlockEntity.this.progress = value; break;
                    case 1: ObsidianGrinderBlockEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Obsidian  Grinder");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new ObsidianGrinderScreenHandler(syncId, inv, this, this.propertyDelegate);
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("obsidian_grinder.progress", progress);

    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("obsidian_grinder.progress");

    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, ObsidianGrinderBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        //world.setBlockState(blockPos, (BlockState)state.with(ObsidianGrinderBlock.LIT, entity.isBurning()), 2);

        if(hasRecipe(entity) > 0) {
            entity.progress++;

            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {

                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static void craftItem(ObsidianGrinderBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if(hasRecipe(entity) == 1) {
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            //Create the crafted item in the desired slot
            entity.setStack(2, new ItemStack(ModItems.OBSIDIAN_DUST,
                    entity.getStack(2).getCount() + 1));
            //Reset the crafting progress
            entity.resetProgress();
        } else if (hasRecipe(entity) == 2) {
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            //Create the crafted item in the desired slot
            entity.setStack(2, new ItemStack(ModItems.OBSIDERITE_DUST,
                    entity.getStack(2).getCount() + 1));
            //Reset the crafting progress
            entity.resetProgress();
        }
    }

    private static int hasRecipe(ObsidianGrinderBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        //Checking recipe for Obsidian Dust
        if(canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, ModItems.OBSIDIAN_DUST)) {
            if(entity.getStack(0).getItem() == ModItems.OBSIDIAN_SHARD && entity.getStack(1).isEmpty() ||
                    entity.getStack(1).getItem() == ModItems.OBSIDIAN_SHARD && entity.getStack(0).isEmpty()) {
                return 1;
            }
        }

        //Checking recipe for Obsiderite Dust
        if(entity.getStack(0).getCount() >= 4 || entity.getStack(1).getCount() >= 4) {
            if(canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, ModItems.OBSIDERITE_DUST)) {
                if(entity.getStack(0).getItem() == ModItems.OBSIDIAN_SHARD && entity.getStack(1).getItem() == Items.NETHERITE_INGOT  ||
                        entity.getStack(1).getItem() == ModItems.OBSIDIAN_SHARD && entity.getStack(0).getItem() == Items.NETHERITE_INGOT ) {
                    return 2;
                }
            }
        }
        return 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }


}