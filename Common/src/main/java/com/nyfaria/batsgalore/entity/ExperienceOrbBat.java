package com.nyfaria.batsgalore.entity;

import com.nyfaria.batsgalore.entity.api.ModBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class ExperienceOrbBat extends ModBat {
	public ExperienceOrbBat(EntityType<? extends ModBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createXPOrbBatAttributes() {
		return ModBat
			.createBatAttributes()
			.add(Attributes.MAX_HEALTH, 1);
	}
}
