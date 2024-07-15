package mudkipboy7.alien.world.item.functional.tool;

import java.util.function.Supplier;

import mudkipboy7.alien.world.item.AMItems;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

@SuppressWarnings("deprecation")
public enum AMTeirs implements Tier {
	ALIEN_WOOD(0, 59, 2.0F, 0.0F, 15, () -> {
		return Ingredient.of(AMItems.ALIEN_PLANKS.get());
	}), GELUSTONE(1, 131, 4.0F, 1.0F, 5, () -> {
		return Ingredient.of(AMItems.ALIEN_COBBLESTONE.get());
	}), ATSALI(2, 250, 6.0F, 2.0F, 14, () -> {
		return Ingredient.of(AMItems.ALIEN_METAL_INGOT.get());
	});

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;

	private final LazyLoadedValue<Ingredient> repairIngredient;

	private AMTeirs(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue,
			Supplier<Ingredient> pRepairIngredient) {
		this.level = pLevel;
		this.uses = pUses;
		this.speed = pSpeed;
		this.damage = pDamage;
		this.enchantmentValue = pEnchantmentValue;
		this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
	}

	@Override
	public int getUses() {
		return this.uses;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.damage;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
