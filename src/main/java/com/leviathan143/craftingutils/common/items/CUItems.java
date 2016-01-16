package com.leviathan143.craftingutils.common.items;

import net.minecraft.item.Item;

public class CUItems 
{
	public static Item ingredientList;
	
	public static void preInit()
	{
		ingredientList = new IngredientList();
	}
}
