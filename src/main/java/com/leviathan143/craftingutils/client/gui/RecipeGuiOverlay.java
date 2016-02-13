package com.leviathan143.craftingutils.client.gui;

import java.awt.Rectangle;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.gui.IAdvancedGuiHandler;
import mezz.jei.gui.IRecipeGuiLogic;
import mezz.jei.gui.RecipesGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.leviathan143.craftingutils.client.event.ClientEventHandler;
import com.leviathan143.craftingutils.client.gui.ingredientList.GuiSelectOutput;
import com.leviathan143.craftingutils.client.gui.lib.GuiIconButton;

public class RecipeGuiOverlay extends GuiScreen
{
	private final Minecraft mc;

	private boolean isActive;
	private RecipesGui recipesGui;
	private IRecipeGuiLogic logic;
	private int recipeGuiLeft;
	
	private List<GuiButton> buttons = new ArrayList<GuiButton>();
	private List<ItemStack> ingredients = new ArrayList<ItemStack>();
	private List<ItemStack> pendingIngredients = new ArrayList<ItemStack>();

	public RecipeGuiOverlay() 
	{
		mc = Minecraft.getMinecraft();
	}

	public void activate(RecipesGui recipesGui)
	{
		if (isActive) return;
		this.doReflection();
		isActive = true;
		this.recipesGui = recipesGui;
		int buttonHeight = recipesGui.height / 2;
		int buttonX = recipeGuiLeft - 30;
		//Accept recipe button 
		buttons.add(new GuiIconButton(0, buttonX, buttonHeight + 15, 20, 20, new ResourceLocation("textures/blocks/dirt.png"), 16, 16));
		//End trail button
		buttons.add(new GuiIconButton(1, buttonX, buttonHeight - 15, 20, 20, new ResourceLocation("textures/blocks/stone.png"), 16, 16));
		
	}
	
	private void doReflection()
	{
		try {
			Class r = recipesGui.getClass();
			Field logic = r.getDeclaredField("logic");
			logic.setAccessible(true);
			this.logic = (IRecipeGuiLogic) logic.get(recipesGui);
			Field guiLeft = r.getDeclaredField("guiLeft");
			recipeGuiLeft = guiLeft.getInt(recipesGui);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deactivate()
	{
		isActive = false;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void render(int mouseX, int mouseY)
	{
		for(GuiButton button : buttons)
		{
			button.drawButton(mc, mouseX, mouseY);
		}
		for(Rectangle r : this.getExtraAreas())
		{
			this.drawString(mc.fontRendererObj, "Rect", r.x, r.y, 0x0000000);
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
			throws IOException 
	{
		System.out.println("Click");
	}

	private void buttonPressed(int id)
	{
		switch (id) 
		{
		case 0:
			System.out.println(recipesGui);
			break;

		case 1:
			break;

		default:
			break;
		}
	}

	public List<Rectangle> getExtraAreas()
	{
		List<Rectangle> extraAreas = new ArrayList<Rectangle>();
		for(GuiButton button : buttons)
		{
			extraAreas.add(new Rectangle(recipeGuiLeft - 30, button.yPosition, button.width, button.height));
		}
		for(Rectangle r : extraAreas)
		{
			//System.out.println(r.toString());
		}
		return extraAreas;
	}
}
