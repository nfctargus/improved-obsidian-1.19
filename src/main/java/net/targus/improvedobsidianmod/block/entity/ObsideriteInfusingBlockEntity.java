package net.targus.improvedobsidianmod.block.entity;

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
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.targus.improvedobsidianmod.item.ModItems;
import net.targus.improvedobsidianmod.screen.ObsideriteInfusingScreenHandler;
import org.jetbrains.annotations.Nullable;

public class ObsideriteInfusingBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public ObsideriteInfusingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OBSIDERITE_INFUSING_STATION, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return ObsideriteInfusingBlockEntity.this.progress;
                    case 1: return ObsideriteInfusingBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: ObsideriteInfusingBlockEntity.this.progress = value; break;
                    case 1: ObsideriteInfusingBlockEntity.this.maxProgress = value; break;
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
        return Text.literal("Obsiderite Infusing Station");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new ObsideriteInfusingScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("obsiderite_infusing_station.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("obsiderite_infusing_station.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, ObsideriteInfusingBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
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

    private static void craftItem(ObsideriteInfusingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        //Check if the correct crafting materials are in the slots
        if(hasRecipe(entity)) {
            entity.removeStack(1, 1);

            // Check which slot the lava bucket is in and replace it with a regular bucket
            if(entity.getStack(0).getItem() == Items.LAVA_BUCKET) {
                entity.removeStack(0, 1);
                entity.setStack(0, new ItemStack(Items.BUCKET,
                        entity.getStack(0).getCount() + 1));
            }
            else {
                entity.removeStack(1, 1);
                entity.setStack(1, new ItemStack(Items.BUCKET,
                        entity.getStack(1).getCount() + 1));
            }
            //Create the crafted item in the desired slot
            entity.setStack(2, new ItemStack(ModItems.OBSIDERITE_INGOT,
                    entity.getStack(2).getCount() + 1));

            //Reset the crafting progress
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(ObsideriteInfusingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }


        if(entity.getStack(0).getItem() == ModItems.OBSIDERITE_DUST && entity.getStack(1).getItem() == Items.LAVA_BUCKET
        || entity.getStack(1).getItem() == ModItems.OBSIDERITE_DUST && entity.getStack(0).getItem() == Items.LAVA_BUCKET) {
            return canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, ModItems.OBSIDERITE_INGOT);
        } else {
            return false;
        }

    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }
}