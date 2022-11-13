package net.targus.improvedobsidianmod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.targus.improvedobsidianmod.networking.packet.EnergySyncS2CPacket;
import net.minecraft.util.Identifier;
import net.targus.improvedobsidianmod.ImprovedObsidianMod;


public class ModMessages {


    public static final Identifier ENERGY_SYNC = new Identifier(ImprovedObsidianMod.MOD_ID, "energy_sync");

    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ENERGY_SYNC, EnergySyncS2CPacket::receive);

    }
}