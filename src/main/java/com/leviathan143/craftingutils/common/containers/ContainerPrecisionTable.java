package com.leviathan143.craftingutils.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import com.leviathan143.craftingutils.common.tileentities.TilePrecisionTable;

public class ContainerPrecisionTable extends Container
{
	IInventory patternInv;
	TilePrecisionTable precisionTable;
	
	public ContainerPrecisionTable(InventoryPlayer playerInv, TilePrecisionTable table) 
	{
		precisionTable = table;
		for (int r = 0; r < 9; r++)
		{
			for (int s = 0; s < 3; s++)
			{
			this.addSlotToContainer(new Slot(playerInv, s, 0, 0));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return precisionTable.isUseableByPlayer(player);
	}
	
}
