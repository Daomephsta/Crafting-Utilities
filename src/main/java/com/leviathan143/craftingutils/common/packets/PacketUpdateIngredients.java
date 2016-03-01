package com.leviathan143.craftingutils.common.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketUpdateIngredients implements IMessage 
{
	private ItemStack list;
	private NBTTagCompound nbt;

	public PacketUpdateIngredients() {}

	public PacketUpdateIngredients(ItemStack list) 
	{
		this.list = list;
		this.nbt = list.getTagCompound(); 
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		list = ByteBufUtils.readItemStack(buf);
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeItemStack(buf, list);
		ByteBufUtils.writeTag(buf, nbt);
	}

	public ItemStack getList() 
	{
		return list;
	}

	public NBTTagCompound getNbt() 
	{
		return nbt;
	}

	public static class PacketUpdateIngredientsHandler implements IMessageHandler<PacketUpdateIngredients, IMessage>
	{
		@Override
		public IMessage onMessage(PacketUpdateIngredients message, MessageContext ctx) 
		{
			final ItemStack list = message.getList();
			final NBTTagCompound nbt = message.getNbt();
			Runnable r = new Runnable() 
			{
				@Override
				public void run() 
				{
					processMessage(list, nbt);
				}
			};

			if(ctx.side.isClient())
			{
				Minecraft.getMinecraft().addScheduledTask(r);
			}
			else if (ctx.side.isServer()) 
			{
				MinecraftServer.getServer().addScheduledTask(r);
			}
			System.out.println("PacketUpdateIngredients handled on " + ctx.side.toString());
			return null;
		}

		public void processMessage(ItemStack list, NBTTagCompound nbt)
		{
			list.readFromNBT(nbt);
		}
	}
}
