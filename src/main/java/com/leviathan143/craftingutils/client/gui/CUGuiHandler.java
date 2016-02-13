package com.leviathan143.craftingutils.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.leviathan143.craftingutils.client.gui.ingredientList.GuiDisplayIngredients;
import com.leviathan143.craftingutils.client.gui.ingredientList.GuiSelectOutput;
import com.leviathan143.craftingutils.client.gui.ingredientList.Ingredient;

public class CUGuiHandler implements IGuiHandler 
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		switch (ID) 
		{
		case 1:
			NBTTagList ingTag = player.getHeldItem().getTagCompound().getTagList("Ingredients", NBT.TAG_COMPOUND);
			List<Ingredient> ingList = new ArrayList<Ingredient>(); 
			for(int t = 0; t <= ingTag.tagCount(); t++)
			{
				ItemStack item = ItemStack.loadItemStackFromNBT(ingTag.getCompoundTagAt(t));
				if (item != null)
				{
					ingList.add(new Ingredient(item));
				}	
			}
			return new GuiDisplayIngredients(Minecraft.getMinecraft(), ingList);

		case 2:
			return new GuiSelectOutput(Minecraft.getMinecraft());
			
		default:
			break;
		}
		return null;
	}

}
