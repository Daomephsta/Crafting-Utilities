package com.leviathan143.craftingutils.client.event;

import java.io.IOException;

import org.lwjgl.input.Mouse;

import com.leviathan143.craftingutils.client.gui.RecipeGuiOverlay;
import com.leviathan143.craftingutils.client.gui.ingredientList.GuiSelectOutput;
import com.leviathan143.craftingutils.common.items.CUItems;

import mezz.jei.gui.RecipesGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class ClientEventHandler 
{
	static RecipeGuiOverlay recipeOverlay = new RecipeGuiOverlay();

	@SubscribeEvent
	public void recipeGuiOpened(GuiOpenEvent e)
	{
		if (e.gui instanceof RecipesGui)
		{
			if(((RecipesGui) e.gui).getParentScreen() instanceof GuiSelectOutput
					&& Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null
					&& Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() == CUItems.ingredientList)
			{
				recipeOverlay.activate((RecipesGui) e.gui);
				
			}
		}
		else if(recipeOverlay.isActive())
		{
			recipeOverlay.deactivate();
		}
	}

	@SubscribeEvent
	public void renderTick(GuiScreenEvent.BackgroundDrawnEvent e)
	{
		if (recipeOverlay.isActive())
			recipeOverlay.render(e.getMouseX(), e.getMouseY());
	}
	
	public static RecipeGuiOverlay getRecipeGuiOverlay()
	{
		return recipeOverlay;
	}
}
