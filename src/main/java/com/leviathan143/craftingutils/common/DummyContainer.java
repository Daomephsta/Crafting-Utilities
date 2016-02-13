package com.leviathan143.craftingutils.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class DummyContainer extends Container 
{
	public DummyContainer() {}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO Auto-generated method stub
		return true;
	}

}
