package com.leviathan143.craftingutils.client.gui;

import com.leviathan143.craftingutils.client.gui.ingredientList.GuiIngredientList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CUGuiHandler implements IGuiHandler 
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		switch (ID) 
		{
		case 1:
			break;

		default:
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		switch (ID) 
		{
		case 1:
			return new GuiIngredientList(Minecraft.getMinecraft());
			
		default:
			break;
		}
		return null;
	}

}
