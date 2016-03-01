package com.leviathan143.craftingutils.common.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestIngredientsUpdate implements IMessage
{
	private ItemStack list;
	
	public PacketRequestIngredientsUpdate() {}
	
	public PacketRequestIngredientsUpdate(ItemStack list) 
	{
		this.list = list; 
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		list = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeItemStack(buf, list);
	}
	
	public ItemStack getList() 
	{
		return list;
	}
	
	public static class PacketRequestIngredientsUpdateHandler implements IMessageHandler<PacketRequestIngredientsUpdate, IMessage>
	{
		@Override
		public IMessage onMessage(PacketRequestIngredientsUpdate message, MessageContext ctx) 
		{
			final ItemStack list = message.getList();
			final MessageContext context = ctx;
			Minecraft.getMinecraft().addScheduledTask(new Runnable() 
			{
				@Override
				public void run() 
				{
					processMessage(list, context);
				}
			});
			System.out.println("PacketRequestIngredientsUpdate handled on " + ctx.side.toString());
			return null;
		}
		
		public void processMessage(ItemStack list, MessageContext context)
		{
			CUPacketHandler.CHANNEL.sendTo(new PacketUpdateIngredients(list), context.getServerHandler().playerEntity);
		}
	}
}
