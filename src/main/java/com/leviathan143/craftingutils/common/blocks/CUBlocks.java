package com.leviathan143.craftingutils.common.blocks;

import net.minecraft.block.Block;

public class CUBlocks 
{
	public static Block precisionTable;
			
	public static void preInit()
	{
		precisionTable = new PrecisionTable();;
	}
}
