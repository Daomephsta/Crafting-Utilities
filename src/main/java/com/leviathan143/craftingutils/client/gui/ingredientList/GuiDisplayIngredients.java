package com.leviathan143.craftingutils.client.gui.ingredientList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;

import com.leviathan143.craftingutils.client.gui.lib.GuiList;
import com.leviathan143.craftingutils.common.DummyContainer;
import com.leviathan143.craftingutils.common.CraftingUtils.Constants;

public class GuiDisplayIngredients extends GuiContainer
{
	private Minecraft mc;
	private GuiList list;
	private List<Ingredient> ingList = new ArrayList<Ingredient>();

	public GuiDisplayIngredients(Minecraft minecraft, List<Ingredient> ingredients) 
	{
		super(new DummyContainer());
		mc = minecraft;
		ingList = ingredients;
		ingList.add(new Ingredient(Blocks.bedrock));
	}

	@Override
	public void initGui() 
	{
		guiLeft = (this.width - xSize) / 2;
		guiTop = (this.height - ySize) / 2;
		xSize = 200;
		ySize = 180;
		list = new GuiList(ingList, guiLeft, guiTop);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		mc.getTextureManager().bindTexture(new ResourceLocation(Constants.MODID, "textures/gui/ingredientList.png"));
		drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, 200, 180);
		list.draw(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean doesGuiPauseGame() 
	{
		return false;
	}

	@Override
	public void handleMouseInput() throws IOException 
	{
		super.handleMouseInput();
		list.handleMouseInput();
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException 
	{
		super.keyTyped(typedChar, keyCode);
		if(Keyboard.getEventKeyState())
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_L))
				System.out.println("YAY!");
		}
	}
}
