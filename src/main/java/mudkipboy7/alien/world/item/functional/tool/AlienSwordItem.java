package mudkipboy7.alien.world.item.functional.tool;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import mudkipboy7.alien.data.tags.AMBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class AlienSwordItem extends TieredItem implements Vanishable {
	   private final float attackDamage;
	   private final Multimap<Attribute, AttributeModifier> defaultModifiers;

	   public AlienSwordItem(Tier tier, int damage, float speed, Item.Properties properties) {
	      super(tier, properties);
	      this.attackDamage = (float)damage + tier.getAttackDamageBonus();
	      ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
	      builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
	      builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)speed, AttributeModifier.Operation.ADDITION));
	      this.defaultModifiers = builder.build();
	   }

	   public float getDamage() {
	      return this.attackDamage;
	   }
	   

	   public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
	      return !p_43294_.isCreative();
	   }

	   public float getDestroySpeed(ItemStack p_43288_, BlockState block) {
	      if (block.is(Blocks.COBWEB)) {
	         return 15.0F;
	      } else {
	         return block.is(AMBlockTags.MINEABLE_WITH_ALIEN_SWORD) ? 1.5F : 1.0F;
	      }
	   }

	   public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
	      p_43278_.hurtAndBreak(1, p_43280_, (p_43296_) -> {
	         p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
	      });
	      return true;
	   }

	   public boolean mineBlock(ItemStack p_43282_, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity p_43286_) {
	      if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
	         p_43282_.hurtAndBreak(2, p_43286_, (p_43276_) -> {
	            p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
	         });
	      }

	      return true;
	   }

	   public boolean isCorrectToolForDrops(BlockState block) {
	      return block.is(AMBlockTags.MINEABLE_WITH_ALIEN_SWORD);
	   }

	   @SuppressWarnings("deprecation")
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43274_) {
	      return p_43274_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43274_);
	   }

	   @Override
	   public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
	      return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
	   }
	}

