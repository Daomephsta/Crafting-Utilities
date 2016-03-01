package com.leviathan143.craftingutils.client.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import mezz.jei.gui.Focus;
import mezz.jei.gui.IRecipeGuiLogic;
import mezz.jei.gui.RecipeLayout;
import mezz.jei.gui.RecipesGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import com.leviathan143.craftingutils.client.gui.lib.GuiIconButton;
import com.leviathan143.craftingutils.common.CraftingUtils;
import com.leviathan143.craftingutils.common.items.IngredientList;

public class RecipeGuiOverlay extends GuiScreen
{
	private final Minecraft mc;
	private static final ResourceLocation buttonTextures = new ResourceLocation(CraftingUtils.Constants.MODID, "/textures/gui/recipeGuiOverlayButtons.png");

	private RecipesGui recipesGui;
	private IRecipeGuiLogic logic;
	private List<RecipeLayout> recipeLayouts;
	private Integer recipeGuiLeft, recipeGuiTop, recipeGuiYSize;

	private boolean isActive;
	private List<GuiButton> buttons = new ArrayList<GuiButton>();
	private List<ItemStack> ingredients = new ArrayList<ItemStack>();
	private List<ItemStack> pendingIngredients = new ArrayList<ItemStack>();
	//This is used to prevent a recipe being added 
	private boolean flag;

	private ItemStack ingredientList;

	public RecipeGuiOverlay() 
	{
		mc = Minecraft.getMinecraft();
	}

	public void activate(RecipesGui recipesGui, ItemStack list)
	{
		buttons.clear();
		if(!isActive)
		{
			pendingIngredients.clear();
			ingredients.clear();
			System.out.println("Lists wiped");
		}

		isActive = true;
		this.recipesGui = recipesGui;
		this.ingredientList = list;
		recipeGuiLeft = recipesGui.getGuiLeft();

		this.doReflection();
		//Accept all button 
		buttons.add(new GuiIconButton(0, "Accept all recipes", recipeGuiLeft - 25, recipeGuiTop + recipeGuiYSize - 25, 20, 20
				, buttonTextures, 0, 0, 40, 40));
		//End trail button
		buttons.add(new GuiIconButton(1, "End recipe trail",recipeGuiLeft - 25, recipeGuiTop + recipeGuiYSize - 50, 20, 20
				, buttonTextures, 20, 40, 40, 40));
		//Accept recipe buttons 
		int buttonID = 2;
		for(RecipeLayout layout : recipeLayouts)
		{
			int layoutY = recipeGuiTop + layout.getPosY() + 17;
			buttons.add(new GuiIconButton(buttonID, "Select recipe", recipeGuiLeft + 6, layoutY, 20, 20
					, buttonTextures, 40, 20, 40, 40));
			buttonID++;
		}
	}

	private void doReflection()
	{
		try 
		{
			Class r = recipesGui.getClass();
			Field logic = r.getDeclaredField("logic");
			logic.setAccessible(true);
			this.logic = (IRecipeGuiLogic) logic.get(recipesGui);

			Field layouts = r.getDeclaredField("recipeLayouts");
			layouts.setAccessible(true);
			this.recipeLayouts = (List<RecipeLayout>) layouts.get(recipesGui);

			Field recipeGuiTop = r.getDeclaredField("guiTop");
			recipeGuiTop.setAccessible(true);
			this.recipeGuiTop = (Integer) recipeGuiTop.get(recipesGui);

			Field recipeGuiYSize = r.getDeclaredField("ySize");
			recipeGuiYSize.setAccessible(true);
			this.recipeGuiYSize = (Integer) recipeGuiYSize.get(recipesGui);
		} 
		catch (NoSuchFieldException e) 
		{
			e.printStackTrace();
		} 
		catch (SecurityException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e)
		{
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
		GlStateManager.color(255, 255, 255);
		RenderHelper.disableStandardItemLighting();
		for(GuiButton button : buttons)
		{
			button.drawButton(mc, mouseX, mouseY);
		}
		RenderHelper.enableStandardItemLighting();
		GlStateManager.resetColor();
	}

	public boolean mouseClick(int mouseX, int mouseY, int mouseButton) 
	{
		for(GuiButton b : buttons)
		{
			if(b.mousePressed(mc, mouseX, mouseY))
			{
				this.buttonPressed(b.id);
				return true;
			}
		}	
		return false;
	}

	private void buttonPressed(int id)
	{
		//System.out.println("Button: " + id);
		if(id == 0)
		{
			List<ItemStack> allIngredients = new ArrayList<ItemStack>();
			allIngredients.addAll(pendingIngredients);
			allIngredients.addAll(ingredients);
			IngredientList.writeIngredientsToNBT(Minecraft.getMinecraft().thePlayer.getHeldItem(), allIngredients);
		}
		else if(id == 1)
		{

		}
		else if (id >= 2)
		{
			List selectedRecipeInputs = recipeLayouts.get(id - 2).getRecipeWrapper().getInputs();
			for(Object obj : selectedRecipeInputs)
			{
				if(obj instanceof List)
				{
					pendingIngredients.addAll((Collection<? extends ItemStack>) obj);
				}
				if(obj instanceof ItemStack)
				{
					ItemStack stack = (ItemStack) obj;
					if (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE)
					{
						stack.setItemDamage(0);
						pendingIngredients.add(stack);
					}
					else
					{
						pendingIngredients.add(stack);
					}
				}
			}
		}

	}
}
