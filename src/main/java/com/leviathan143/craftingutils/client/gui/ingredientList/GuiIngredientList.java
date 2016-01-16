package com.leviathan143.craftingutils.client.gui.ingredientList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;

import com.leviathan143.craftingutils.common.libs.CUConstants;

public class GuiIngredientList extends GuiScreen
{
	private Minecraft mc;
	private GuiList list;
	
	public GuiIngredientList(Minecraft minecraft) 
	{
		mc = minecraft;
	}
	
	@Override
	public void initGui() 
	{
		List<Ingredient> ingList = new ArrayList<Ingredient>();
		ingList.add(new Ingredient(Blocks.obsidian));
		ingList.add(new Ingredient(Blocks.stone));
		ingList.add(new Ingredient(Blocks.wool));
		ingList.add(new Ingredient(Items.acacia_door));
		ingList.add(new Ingredient(Items.apple));
		ingList.add(new Ingredient(Items.arrow));
		ingList.add(new Ingredient(Blocks.obsidian));
		ingList.add(new Ingredient(Blocks.stone));
		ingList.add(new Ingredient(Blocks.wool));
		ingList.add(new Ingredient(Items.acacia_door));
		ingList.add(new Ingredient(Items.apple));
		ingList.add(new Ingredient(Items.arrow));
		list = new GuiList(ingList, 125, 40);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		mc.getTextureManager().bindTexture(new ResourceLocation(CUConstants.MODID, "textures/gui/ingredientList.png"));
		int w = (this.width - 200) / 2;
		int h = (this.height - 180) / 2;
		drawModalRectWithCustomSizedTexture(w, h, 0, 0, 200, 180, 200, 180);
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
}
