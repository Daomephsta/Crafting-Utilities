package com.leviathan143.craftingutils.client.gui.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class GuiIconButton extends GuiButton 
{
	private ResourceLocation icon;
	private int minU, minV, maxU, maxV;

	public GuiIconButton(int buttonId, String hoverText , int x, int y, int width, int height, ResourceLocation icon
			, int minU, int minV, int maxU, int maxV) 
	{
		super(buttonId, x, y, width, height, hoverText);
		this.icon = icon;
		this.minU = minU;
		this.minV = minV;
		this.maxU = maxU;
		this.maxV = maxV;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) 
	{
		mc.getTextureManager().bindTexture(icon);
		this.drawModalRectWithCustomSizedTexture(xPosition, yPosition, minU, minV, width, height, maxU, maxV);
		if(this.hovered)
		{
			Gui.drawRect(mouseX, mouseY, 10, 10, 0x000000);
			mc.fontRendererObj.drawString(this.displayString, mouseX, mouseY, 0xFFFFFF);
		}
	}
}
