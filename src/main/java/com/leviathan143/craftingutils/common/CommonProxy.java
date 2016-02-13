package com.leviathan143.craftingutils.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.leviathan143.craftingutils.client.gui.CUGuiHandler;
import com.leviathan143.craftingutils.common.items.CUItems;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event)
	{
		this.clientPreInit(event);
		CUItems.preInit();
	}
	
	public void init(FMLInitializationEvent event)
	{
		this.clientInit(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(CraftingUtils.instance, new  CUGuiHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		this.clientPostInit(event);
	}
	
	public void clientPreInit(FMLPreInitializationEvent event)
	{
		
	}
	
	public void clientInit(FMLInitializationEvent event)
	{
		
	}
	
	public void clientPostInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public void registerRenderers()
	{
	
	}
	
	public void registerSounds() 
	{
		
	}
}
