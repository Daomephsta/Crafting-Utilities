package com.leviathan143.craftingutils.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.leviathan143.craftingutils.common.CraftingUtils.Constants;


@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION)
public class CraftingUtils 
{
	public class Constants
	{
		public static final String MODNAME = "Crafting Utils";
		public static final  String  MODID = "craftingutils";
		public static final  String  VERSION = "0.1";
		public static final String COMMONPROXY_PATH="com.leviathan143.craftingutils.common.CommonProxy";
		public static final String CLIENTPROXY_PATH="com.leviathan143.craftingutils.client.ClientProxy";
	}

	@SidedProxy(clientSide = Constants.CLIENTPROXY_PATH,
			serverSide=Constants.COMMONPROXY_PATH)
	public static CommonProxy proxy;

	@Mod.Instance(Constants.MODID)
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
