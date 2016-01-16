package com.leviathan143.craftingutils.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.leviathan143.craftingutils.client.gui.CUGuiHandler;
import com.leviathan143.craftingutils.common.blocks.CUBlocks;
import com.leviathan143.craftingutils.common.items.CUItems;
import com.leviathan143.craftingutils.common.tileentities.TilePrecisionTable;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event)
	{
		CUBlocks.preInit();
		CUItems.preInit();
	}
	
	public void init(FMLInitializationEvent event)
	{
		registerTEs();
		NetworkRegistry.INSTANCE.registerGuiHandler(CraftingUtils.instance, new  CUGuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public void registerRenderers()
	{
	
	}
	
	public void registerSounds() 
	{
		
	}
	
	public void registerTEs()
	{
		GameRegistry.registerTileEntity(TilePrecisionTable.class, "tilePrecisonTable");
	}
}
