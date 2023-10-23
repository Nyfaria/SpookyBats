package com.nyfaria.spookybats.entity;

import com.nyfaria.spookybats.entity.api.SpookyBat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public class ExperienceOrbBat extends SpookyBat {
	public ExperienceOrbBat(EntityType<? extends SpookyBat> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		this.xpReward = 20;
	}

	public static AttributeSupplier.Builder createXPOrbBatAttributes() {
		return SpookyBat
			.createBatAttributes()
			.add(Attributes.MAX_HEALTH, 1);
	}
}
