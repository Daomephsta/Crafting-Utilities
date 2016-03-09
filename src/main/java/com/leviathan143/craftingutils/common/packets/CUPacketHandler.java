package com.leviathan143.craftingutils.common.packets;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.leviathan143.craftingutils.common.CraftingUtils.Constants;
import com.leviathan143.craftingutils.common.packets.PacketUpdateIngredients.PacketUpdateIngredientsHandler;

public class CUPacketHandler 
{
	public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MODID);
	private static int packetID = 0;
	
	public static void registerPackets()
	{
		registerPacket(PacketUpdateIngredientsHandler.class, PacketUpdateIngredients.class, Side.SERVER);
	}
	
	private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side)
	{
		CHANNEL.registerMessage(messageHandler, requestMessageType, packetID, side);
		packetID++;
	}
}
