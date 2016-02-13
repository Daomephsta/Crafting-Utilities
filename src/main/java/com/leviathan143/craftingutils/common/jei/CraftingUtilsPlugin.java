package com.leviathan143.craftingutils.common.jei;

import java.awt.Rectangle;
import java.util.List;

import com.leviathan143.craftingutils.client.event.ClientEventHandler;
import com.leviathan143.craftingutils.client.gui.RecipeGuiOverlay;

import mezz.jei.JeiRuntime;
import mezz.jei.api.IItemRegistry;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IAdvancedGuiHandler;
import mezz.jei.gui.RecipesGui;

@JEIPlugin
public class CraftingUtilsPlugin implements IModPlugin
{
	public static JeiRuntime jeiRuntime;

	@Override
	public void onJeiHelpersAvailable(IJeiHelpers jeiHelpers) 
	{

	}

	@Override
	public void onItemRegistryAvailable(IItemRegistry itemRegistry) {
		// TODO Auto-generated method stub

	}

	@Override
	public void register(IModRegistry registry) 
	{
		
	}

	@Override
	public void onRecipeRegistryAvailable(IRecipeRegistry recipeRegistry) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) 
	{
		CraftingUtilsPlugin.jeiRuntime = (JeiRuntime) jeiRuntime;
	}

}
