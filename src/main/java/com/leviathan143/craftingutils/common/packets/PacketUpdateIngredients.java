package com.leviathan143.craftingutils.common.packets;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.leviathan143.craftingutils.common.items.IngredientList;

public class PacketUpdateIngredients implements IMessage 
{
	private ItemStack list;
	private List<ItemStack> ingredients;

	public PacketUpdateIngredients() {}

	public PacketUpdateIngredients(ItemStack list, List<ItemStack> ingredients) 
	{
		this.list = list;
		this.ingredients = ingredients; 
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		ingredients = new ArrayList<ItemStack>();
		list = ByteBufUtils.readItemStack(buf);
		int listSize = buf.readInt();
		for(int is = 0; is < listSize; is++)
		{
			ingredients.add(ByteBufUtils.readItemStack(buf));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeItemStack(buf, list);
		buf.writeInt(ingredients.size());
		for(ItemStack ingredient : ingredients)
		{
			ByteBufUtils.writeItemStack(buf, ingredient);
		}
	}

	public ItemStack getList() 
	{
		return list;
	}

	public List<ItemStack> getIngredients() 
	{
		return ingredients;
	}

	public static class PacketUpdateIngredientsHandler implements IMessageHandler<PacketUpdateIngredients, IMessage>
	{
		@Override
		public IMessage onMessage(PacketUpdateIngredients message, final MessageContext ctx) 
		{
			final ItemStack list = message.getList();
			final List<ItemStack> ingredients = message.getIngredients();
				MinecraftServer.getServer().addScheduledTask(new Runnable() 
				{
					@Override
					public void run() 
					{
						processMessage(ctx.getServerHandler().playerEntity, list, ingredients);
					}
				});
			System.out.println("PacketUpdateIngredients handled on " + ctx.side.toString() + message.getIngredients());
			return null;
		}

		public void processMessage(EntityPlayerMP player, ItemStack list, List<ItemStack> ingredients)
		{
			IngredientList.writeIngredientsToNBT(player.getHeldItem(), ingredients);
		}
	}
}
