package com.nyfaria.batsgalore.client.model.spooky;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.nyfaria.batsgalore.client.model.ModBatModel;
import com.nyfaria.batsgalore.entity.WingedTurmoil;
import net.minecraft.client.model.geom.ModelPart;
import org.joml.Vector3f;

public class WingedTurmoilModel<T extends WingedTurmoil> extends ModBatModel<T> {

	public WingedTurmoilModel(ModelPart root) {
		super(root);
	}

	@Override
	public Vector3f getHeadPos() {
		return super.getHeadPos().add(0,-1.2f,0);
	}
}