package mudkipboy7.alien.client.gui.screen;

import mudkipboy7.alien.inventory.menu.AbstractMachineMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractMachineScreen<T extends AbstractMachineMenu> extends AbstractContainerScreen<T> {

	public AbstractMachineScreen(T menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);

	}

	public static Component getValueOfResource(int currentAmmount, int maxAmmount, boolean displayAsPercentage,
			int decimalPrecision) {
		float percentage = calculatePercentage(currentAmmount, maxAmmount);

		String text = "This shouldn't appear. It's just here to prevent a crash from a value being null.";

		double mul = Math.pow(10, decimalPrecision);
		double cropped = Math.round(percentage * mul) / mul;
		int rounded = Math.round(percentage);

		// Makes it display as a fraction
		if (!displayAsPercentage)
			text = currentAmmount + "/" + maxAmmount;
		// Makes it display as a whole number.
		else if (decimalPrecision < 1 || cropped % 100 == 0)
			text = rounded + "%";
		// Makes it display as a decimal rounded to a specific decimal place.
		else
			text = cropped + "%";

		MutableComponent description = Component.literal(text);
		/*
		 * Checks if it is supposed to display as a percent, if it is it returns a
		 * stupid message.
		 */
		TextColor color = getColorFromPercent(percentage);

		return description.withStyle((arg) -> arg.withColor(color));
	}

	public static TextColor getColorFromPercent(float percentage) {
		if (percentage >= 90)
			return TextColor.fromRgb(966929);
		else if (percentage <= 10)
			return TextColor.fromRgb(-65536);
		else if (percentage >= 75)
			return TextColor.fromRgb(7395191);
		else if (percentage <= 25)
			return TextColor.fromRgb(14047059);
		else if (percentage >= 25)
			return TextColor.fromRgb(15983634);
		// else if (percentage >= 25)
		// return TextColor.fromRgb(11642908);
		else
			return null;

		// return null
	}

	public static Component getValueOfResource(int currentAmmount, int maxAmmount) {
		return getValueOfResource(currentAmmount, maxAmmount, !hasShiftDown(), 1);
	}

	public static float calculatePercentage(int currentAmmount, int maxAmmount) {
		float percentage = 0.0F;
		if (maxAmmount != 0)
			percentage = ((float) currentAmmount / (float) maxAmmount) * 100.0F;
		return percentage;

	}
}
