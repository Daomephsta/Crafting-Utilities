package com.leviathan143.craftingutils.client.gui.lib;

import java.io.IOException;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import org.lwjgl.input.Mouse;

public class GuiList extends Gui
{
	private Minecraft mc;

	private List<GuiListEntry> entries;
	private List<GuiListEntry> visibleEntries;
	private int[] visibleEntryCoords = new int[2];
	
	private final int ENTRYPADDING = 10;

	private int xPos, yPos, width, height;

	public GuiList(List<? extends GuiListEntry> entries, int x, int y) 
	{
		mc = Minecraft.getMinecraft();

		this.entries = (List<GuiListEntry>) entries;
		this.init();
		xPos = x;
		yPos = y;
	}

	private void init()
	{
		if (this.entries.size() < 5)
		{
			setVisible(0, entries.size());
		}
		else
		{
			setVisible(0, 5);
		}
		width = 0;
		for(GuiListEntry entry : entries)
		{
			if (entry.getWidth() > width) width = entry.getWidth();
		}
	}

	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		int entryY = this.yPos + 10;
		height = 0;
		for(GuiListEntry entry : visibleEntries)
		{

			entry.drawEntry(xPos + ENTRYPADDING, entryY);
			this.drawString(mc.fontRendererObj, "|", xPos + ENTRYPADDING, entryY, 0xC80000);
			this.drawString(mc.fontRendererObj, "|", xPos + width + ENTRYPADDING, entryY, 0xC80000);
			height += entry.getHeight();
			entryY += entry.getHeight();
		}
		this.drawString(mc.fontRendererObj, mouseX + ", " + mouseY, mouseX, mouseY, 0xC80000);
	}

	public void handleMouseInput() throws IOException 
	{
		//Handle Scrolling
		int delta = Mouse.getDWheel();
		if (delta > 0) scrollUp();
		else if (delta < 0) scrollDown();
		this.visibleEntries = entries.subList(visibleEntryCoords[0], visibleEntryCoords[1]);
		//Pass clicks to the entry that was clicked
		if (Mouse.getEventButtonState())
		{
			ScaledResolution sr = new ScaledResolution(mc);
			int mouseX = Mouse.getX() * sr.getScaledWidth() / mc.displayWidth;
			int mouseY = sr.getScaledHeight() - Mouse.getY() * sr.getScaledHeight() / mc.displayHeight - 1;
			if ((mouseX >= this.xPos && mouseX <= Math.addExact(this.xPos, width)) 
					&& (mouseY > this.yPos && mouseY < this.yPos + height))
			{
				GuiListEntry clickedEntry = getClickedEntryFromY(mouseY);
				if (clickedEntry != null)
				{
					clickedEntry.onClicked(Mouse.getEventButton());
				}
			}
		}
	}
	
	private void setVisible(int from, int to)
	{
		this.visibleEntryCoords[0] = from;
		this.visibleEntryCoords[1] = to;
		this.visibleEntries = entries.subList(from, to);
	}

	private GuiListEntry getClickedEntryFromY(int y)
	{
		int slotHeight = this.yPos;
		for(GuiListEntry entry : visibleEntries)
		{
			slotHeight += entry.getHeight();
			if (slotHeight >= y)
			{
				return entry;
			}
		}
		return null;
	}


	private void scrollUp()
	{
		if (visibleEntryCoords[0] != 0)
		{
			setVisible(visibleEntryCoords[0] - 1, visibleEntryCoords[1] - 1);
		}
	}

	private void scrollDown()
	{
		if (visibleEntryCoords[1] != entries.size())
		{
			setVisible(visibleEntryCoords[0] + 1, visibleEntryCoords[1] + 1);
		}
	}
}
