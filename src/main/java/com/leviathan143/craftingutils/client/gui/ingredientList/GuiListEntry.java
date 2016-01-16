package com.leviathan143.craftingutils.client.gui.ingredientList;

import net.minecraft.client.Minecraft;


public abstract class GuiListEntry 
{	
	protected Minecraft mc = Minecraft.getMinecraft();
	
	public boolean checked;
	
	public GuiListEntry() 
	{
		
	}
	
	public abstract void drawEntry(int x, int y);
	
	public abstract int getHeight();
	
	public abstract int getWidth();
	
	public abstract void onClicked(int button);
}
