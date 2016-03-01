package com.leviathan143.craftingutils.common.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.leviathan143.craftingutils.client.gui.ingredientList.Ingredient;
import com.leviathan143.craftingutils.common.CraftingUtils;
import com.leviathan143.craftingutils.common.CraftingUtils.Constants;
import com.leviathan143.craftingutils.common.packets.CUPacketHandler;
import com.leviathan143.craftingutils.common.packets.PacketRequestIngredientsUpdate;
import com.leviathan143.craftingutils.common.packets.PacketUpdateIngredients;

public class IngredientList extends Item
{
	private List<ItemStack> ingredients = new ArrayList<ItemStack>(); 

	public IngredientList() 
	{
		GameRegistry.registerItem(this, "ingredientList");
		this.setUnlocalizedName(Constants.MODID + "" + "ingredientList");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) 
	{
		if (player.isSneaking()) 
		{
			player.openGui(CraftingUtils.instance, 2, world
					, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		}
		else
		{
			player.openGui(CraftingUtils.instance, 1, world
					, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		}
		return itemstack;
	}

	public static void writeIngredientsToNBT(ItemStack list, List<ItemStack> ingredients)
	{
		if(list.getTagCompound() == null) list.setTagCompound(new NBTTagCompound());
		NBTTagList nbtList = new NBTTagList();
		for(ItemStack i : ingredients)
		{
			NBTTagCompound itemNBT = new NBTTagCompound();
			i.writeToNBT(itemNBT);
			nbtList.appendTag(itemNBT);
		}
		list.getTagCompound().setTag("Ingredients", nbtList);
		CUPacketHandler.CHANNEL.sendToServer(new PacketUpdateIngredients(list));
	}

	void readIngredientsFromNBT(NBTTagCompound nbt, ItemStack list)
	{
		ingredients.clear();
		CUPacketHandler.CHANNEL.sendToServer(new PacketRequestIngredientsUpdate(list));
		NBTTagList ingTag = nbt.getTagList("Ingredients", NBT.TAG_COMPOUND); 
		for(int t = 0; t <= ingTag.tagCount(); t++)
		{
			ItemStack item = ItemStack.loadItemStackFromNBT(ingTag.getCompoundTagAt(t));
			if (item != null)
			{
				ingredients.add(item);
			}	
		}
	}
	
	void readIngredientsFromNBT(NBTTagCompound nbt)
	{
		ingredients.clear();
		NBTTagList ingTag = nbt.getTagList("Ingredients", NBT.TAG_COMPOUND); 
		for(int t = 0; t <= ingTag.tagCount(); t++)
		{
			ItemStack item = ItemStack.loadItemStackFromNBT(ingTag.getCompoundTagAt(t));
			System.out.println(ingTag.getCompoundTagAt(t));
			if (item != null)
			{
				ingredients.add(item);
				System.out.println(ingredients);
			}	
		}
	}

	public List<Ingredient> getIngredientsInGuiWrapper(ItemStack list) 
	{
		if(list.getTagCompound() == null) list.setTagCompound(new NBTTagCompound());
		readIngredientsFromNBT(list.getTagCompound(), list);
		List<Ingredient> wrappedIngredients = new ArrayList<Ingredient>();
		for(ItemStack stack : ingredients) 
		{
			wrappedIngredients.add(new Ingredient(stack));
		}
		return wrappedIngredients;
	}
	
	@Override
	public boolean updateItemStackNBT(NBTTagCompound nbt) 
	{
		readIngredientsFromNBT(nbt);
		return true;
	}
}
