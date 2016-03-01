package com.leviathan143.craftingutils.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.leviathan143.craftingutils.client.gui.ingredientList.GuiDisplayIngredients;
import com.leviathan143.craftingutils.client.gui.ingredientList.GuiSelectOutput;
import com.leviathan143.craftingutils.common.items.IngredientList;

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
			return new GuiDisplayIngredients(Minecraft.getMinecraft(), ((IngredientList)player.getHeldItem().getItem()).getIngredientsInGuiWrapper(player.getHeldItem()));

		case 2:
			return new GuiSelectOutput(Minecraft.getMinecraft());
			
		default:
			break;
		}
		return null;
	}

}
