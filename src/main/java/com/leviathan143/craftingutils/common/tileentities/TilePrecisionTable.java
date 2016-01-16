package com.leviathan143.craftingutils.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TilePrecisionTable extends TileEntity implements IInventory
{
	private ItemStack[] patternInv = new ItemStack[9];
	private ItemStack[] ingredients = new ItemStack[9];
	
	@Override
	public int getSizeInventory() 
	{
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int slotIn) 
	{
		return slotIn < 9 ? this.ingredients[slotIn] : this.patternInv[slotIn];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		if (index < 9)
			this.ingredients[index] = stack;
		else
			this.patternInv[index] = stack;
	}


	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return true;
	}

	@Override
	public String getName() 
	{
		return "container.precisionTable";
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

	@Override
	public IChatComponent getDisplayName() 
	{
		return new ChatComponentText(getName());
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
