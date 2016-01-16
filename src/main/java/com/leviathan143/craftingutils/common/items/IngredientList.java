package com.leviathan143.craftingutils.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.leviathan143.craftingutils.common.CraftingUtils;

public class IngredientList extends Item 
{
	public IngredientList() 
	{
		GameRegistry.registerItem(this, "ingredientList");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) 
	{
		if (player.isSneaking()) 
		{
			player.openGui(CraftingUtils.instance, 1, world
					, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		}
		
		return itemstack;
	}
}
