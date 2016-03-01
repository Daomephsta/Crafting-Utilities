package com.leviathan143.craftingutils.client.event;

import mezz.jei.gui.RecipesGui;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.input.Mouse;

import com.leviathan143.craftingutils.client.gui.RecipeGuiOverlay;
import com.leviathan143.craftingutils.client.gui.ingredientList.GuiSelectOutput;
import com.leviathan143.craftingutils.common.items.CUItems;


public class ClientEventHandler 
{
	static RecipeGuiOverlay recipeOverlay = new RecipeGuiOverlay();

	@SubscribeEvent
	public void recipeGuiPostInit(GuiScreenEvent.InitGuiEvent.Post e)
	{
		if (e.gui instanceof RecipesGui)
		{

			if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null
					&& Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() == CUItems.ingredientList)
			{
				ItemStack list = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
				if(((RecipesGui) e.gui).getParentScreen() instanceof GuiSelectOutput)
				{
					recipeOverlay.activate((RecipesGui) e.gui, list);
				}
			}
		}
		else if(recipeOverlay.isActive())
		{
			recipeOverlay.deactivate();
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void mouseInput(GuiScreenEvent.MouseInputEvent.Pre e)
	{
		if (recipeOverlay.isActive() && Mouse.getEventButtonState() && Mouse.getEventButton() > -1)
		{
			int mouseX = Mouse.getX() * e.gui.width / e.gui.mc.displayWidth;
			int mouseY = e.gui.height - Mouse.getY() * e.gui.height / e.gui.mc.displayHeight - 1;;
			e.setCanceled(recipeOverlay.mouseClick(mouseX, mouseY, Mouse.getEventButton()));
		}
	}

	@SubscribeEvent
	public void renderTick(GuiScreenEvent.DrawScreenEvent.Post e)
	{
		if (recipeOverlay.isActive())
			recipeOverlay.render(e.mouseX, e.mouseY);
	}

	public static RecipeGuiOverlay getRecipeGuiOverlay()
	{
		return recipeOverlay;
	}
}
