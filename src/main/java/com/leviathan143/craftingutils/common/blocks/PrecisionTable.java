package com.leviathan143.craftingutils.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.leviathan143.craftingutils.common.tileentities.TilePrecisionTable;

public class PrecisionTable extends Block 
{
	public PrecisionTable() 
	{
		super(Material.iron);
		GameRegistry.registerBlock(this, "precisionTable");
	}

	@Override
	public boolean hasTileEntity() 
	{
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) 
	{
		return new TilePrecisionTable();
	}
}
