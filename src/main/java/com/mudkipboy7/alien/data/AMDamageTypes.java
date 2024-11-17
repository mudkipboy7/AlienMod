package com.mudkipboy7.alien.data;

import javax.annotation.Nullable;

import com.mudkipboy7.alien.AlienMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class AMDamageTypes {
	
	 
	public static final ResourceKey<DamageType> NO_OXYGEN = createKey("no_oxygen");

	public static void bootstrap(BootstapContext<DamageType> context) {
		context.register(NO_OXYGEN, new DamageType(AlienMod.MODID + ".no_oxygen" , 0.1F, DamageEffects.FREEZING));
	}

	private static ResourceKey<DamageType> createKey(String name) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(AlienMod.MODID, name));
	}

	public static DamageSource damageSource(Level level, ResourceKey<DamageType> key) {
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
	}

	public static DamageSource entityDamageSource(Level level, ResourceKey<DamageType> key, @Nullable Entity entity) {
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key),
				entity);
	}

	public static DamageSource indirectEntityDamageSource(Level level, ResourceKey<DamageType> key,
			@Nullable Entity source, @Nullable Entity trueSource) {
		return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key),
				source, trueSource);
	}

}
