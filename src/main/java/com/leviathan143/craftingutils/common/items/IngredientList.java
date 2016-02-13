package com.leviathan143.craftingutils.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.leviathan143.craftingutils.common.CraftingUtils;
import com.leviathan143.craftingutils.common.CraftingUtils.Constants;

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
			writeIngredientsToNBT(itemstack.getTagCompound());
			player.openGui(CraftingUtils.instance, 1, world
					, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		}
		return itemstack;
	}
	
	/*void readIngredientsFromNBT(NBTTagCompound nbt)
	{
		for(int i : nbt.getTag("Ingredients"))
		{
			
		}
	}*/
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) 
	{
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
	}
	
	void writeIngredientsToNBT(NBTTagCompound nbt)
	{
		NBTTagList nbtList = new NBTTagList();
		for(ItemStack i : ingredients)
		{
			NBTTagCompound itemNBT = new NBTTagCompound();
			i.writeToNBT(itemNBT);
			nbtList.appendTag(itemNBT);
		}
		nbt.setTag("Ingredients", nbtList);
	}
	
	/*public List<ItemStack> readIngredients()
	{
		
	}*/
}
