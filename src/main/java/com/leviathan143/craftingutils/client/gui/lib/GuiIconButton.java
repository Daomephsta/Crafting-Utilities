package com.leviathan143.craftingutils.client.gui.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiIconButton extends GuiButton 
{
	private ResourceLocation icon;
	private int iconWidth, iconHeight;

	public GuiIconButton(int buttonId, int x, int y, int widthIn, int heightIn, ResourceLocation icon
			, int iconWidth, int iconHeight) 
	{
		super(buttonId, x, y, widthIn, heightIn, "");
		this.icon = icon;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) 
	{
		mc.getTextureManager().bindTexture(icon);
		drawModalRectWithCustomSizedTexture(xPosition, yPosition, 0, 0, width, height, iconWidth, iconHeight);
	}
}
