package com.leviathan143.craftingutils.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.leviathan143.craftingutils.common.libs.CUConstants;

@Mod(modid = CUConstants.MODID, name = CUConstants.MODNAME, version = CUConstants.VERSION)
public class CraftingUtils 
{
	@SidedProxy(clientSide = CUConstants.CLIENTPROXY_PATH,
			serverSide=CUConstants.COMMONPROXY_PATH)
	public static CommonProxy proxy;
	
	@Mod.Instance(CUConstants.MODID)
	public static CraftingUtils instance;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
