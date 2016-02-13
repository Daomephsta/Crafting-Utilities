package com.leviathan143.craftingutils.client.gui.ingredientList;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import com.leviathan143.craftingutils.common.DummyContainer;
import com.leviathan143.craftingutils.common.jei.CraftingUtilsPlugin;

public class GuiSelectOutput extends GuiContainer
{
	private Minecraft mc;
	public GuiSelectOutput(Minecraft minecraft) 
	{
		super(new DummyContainer());
		mc = minecraft;	
	}

	@Override
	public void initGui() 
	{
		guiLeft = this.width / 40;
		xSize = guiLeft + Math.max(mc.fontRendererObj.getStringWidth("Hover over the desired")
				, mc.fontRendererObj.getStringWidth("output item, and left click"));
	}

	@Override
	public boolean doesGuiPauseGame() 
	{
		return false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		mc.fontRendererObj.drawString("Hover over the desired"
				, guiLeft, (this.height / 2) - 5, 0xFF0000);
		mc.fontRendererObj.drawString("output item, and left click"
				, guiLeft, (this.height / 2) + 5, 0xFF0000);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException 
	{
		System.out.println("Click");
	}
}
