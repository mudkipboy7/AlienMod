package mudkipboy7.alien.world.item.functional;

import java.util.List;

import mudkipboy7.alien.data.AMLanguage;
import mudkipboy7.alien.world.block.AMBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class AmmoniaLiquidBucketItem extends BucketItem {

	public AmmoniaLiquidBucketItem(RegistryObject<ForgeFlowingFluid> ammoniaFluidSource, Properties p_40690_) {
		super(ammoniaFluidSource, p_40690_);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, Level level, List<Component> component, TooltipFlag tooltipFlag) {
		component.add(AMLanguage.getDescription(AMBlocks.AMMONIA_LIQUID_BLOCK.get()));
		super.appendHoverText(itemStack, level, component, tooltipFlag);
	}

}
