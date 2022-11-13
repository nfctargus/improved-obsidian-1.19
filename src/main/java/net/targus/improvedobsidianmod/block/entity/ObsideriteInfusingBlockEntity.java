package net.targus.improvedobsidianmod.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.targus.improvedobsidianmod.item.ModItems;
import net.targus.improvedobsidianmod.networking.ModMessages;
import net.targus.improvedobsidianmod.screen.ObsideriteInfusingScreenHandler;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class ObsideriteInfusingBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(5000,32,32) {
        @Override
        protected void onFinalCommit() {
            markDirty();
            if(!world.isClient()) {
                PacketByteBuf data = PacketByteBufs.create();
                data.writeLong(amount);
                data.writeBlockPos(getPos());

                for(ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
                    ServerPlayNetworking.send(player, ModMessages.ENERGY_SYNC, data);
                }
            }
        }
    };
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
                return 4;
            }
        };
    }
    public void setEnergyLevel(long energyLevel) { this.energyStorage.amount = energyLevel; }
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
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("obsiderite_infusing_station.progress", progress);
        nbt.putLong("obsiderite_infusing_station.energy",energyStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("obsiderite_infusing_station.progress");
        energyStorage.amount = nbt.getLong("obsiderite_infusing_station.energy");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, ObsideriteInfusingBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasEnergyItem(entity)) {
            try(Transaction transaction = Transaction.openOuter()) {
                entity.energyStorage.insert(32, transaction);
                transaction.commit();
                entity.setStack(0,new ItemStack(Items.BUCKET));
            }
        }

        if(hasRecipe(entity) && hasEnoughEnergy(entity)) {
            entity.progress++;

            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                extractEnergy(entity);
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    // Energy
    private static void extractEnergy(ObsideriteInfusingBlockEntity entity) {
        try(Transaction transaction = Transaction.openOuter()) {
            entity.energyStorage.extract(32, transaction);
            transaction.commit();

        }
    }

    private static boolean hasEnoughEnergy(ObsideriteInfusingBlockEntity entity) {
        return entity.energyStorage.amount >= 32;
    }

    private static boolean hasEnergyItem(ObsideriteInfusingBlockEntity entity) {
        return entity.getStack(0).getItem() == Items.LAVA_BUCKET;
    }
    // Energy End

    private static void craftItem(ObsideriteInfusingBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if(hasRecipe(entity)) {
            entity.removeStack(1, 1);
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

        //Check if we have the correct ingredient in the correct slot
        if(entity.getStack(1).getItem() == ModItems.OBSIDERITE_DUST) {
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