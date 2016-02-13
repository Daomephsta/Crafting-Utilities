package com.leviathan143.craftingutils.client;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.leviathan143.craftingutils.client.event.ClientEventHandler;
import com.leviathan143.craftingutils.common.CommonProxy;

public class ClientProxy extends CommonProxy 
{
	public void clientPreInit(FMLPreInitializationEvent event)
	{
		
	}
	
	public void clientInit(FMLInitializationEvent event)
	{
		
	}
	
	public void clientPostInit(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
	}
	
	public void registerRenderers()
	{

	}

	public void registerSounds() 
	{
		
	}
}
