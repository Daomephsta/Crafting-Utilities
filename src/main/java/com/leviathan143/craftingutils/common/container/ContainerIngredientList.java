package com.leviathan143.craftingutils.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerIngredientList extends Container
{
	public ContainerIngredientList() 
	{
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return true;
	}
	
	
}
