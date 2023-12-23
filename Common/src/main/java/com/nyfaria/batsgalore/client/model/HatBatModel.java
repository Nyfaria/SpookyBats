package com.nyfaria.batsgalore.client.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.client.model.geom.ModelPart;

public class HatBatModel<T extends ModBat> extends ModBatModel<T> {

	public HatBatModel(ModelPart root) {
		super(root);
	}

	public void hideForPlayer(){
		this.body.visible = false;
	}


}