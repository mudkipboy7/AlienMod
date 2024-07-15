package mudkipboy7.alien.world.worldgen.dimension.sky;

public class EclipseSettings {
	public static float defaultTimeOfEclipse = 6000.0F;
	public static float defaultFullEclipseLength = 3000.0F;
	public static float defaultEclipseTransitionLength = 400.0F;

	/*
	 * What time the middle of the eclipse is. This should always be at a time that
	 * by the time the eclipse is over the sun would still be yet to set.
	 */
	public final float timeOfMiddleOfEclipse;

	/*
	 * How long the full eclipse is. It should be at 3000.0F
	 */
	public final float fullEclipseLength;

	/*
	 * How long the transitions at the starts and ends of an eclipses last.
	 */
	public final float eclipseTransitionLength;

	/**
	 * Custom eclipse settings
	 * 
	 * @param time        The time at which the middle of the eclipse is
	 * @param length      The length of the full eclipse when the sky is fully
	 *                    darkened
	 * @param transLength The Length of the transitions at the start and end of
	 *                    eclipses
	 */
	public EclipseSettings(float time, float length, float transLength) {
		this.timeOfMiddleOfEclipse = time;
		this.fullEclipseLength = length;
		this.eclipseTransitionLength = transLength;
	}

	/**
	 * Default eclipse settings
	 */
	public EclipseSettings() {
		this(defaultTimeOfEclipse, defaultFullEclipseLength, defaultEclipseTransitionLength);
	}

	// Returns timeOfMiddleOfEclipse
	public float timeOfMiddleOfEclipse() {
		return this.timeOfMiddleOfEclipse;
	}

	// Returns eclipseTransitionLength
	public float eclipseTransitionLength() {
		return this.eclipseTransitionLength;
	}

	public float fullEclipseLength() {
		return this.fullEclipseLength;
	}

	// When the sky starts to darken during an eclipse.
	public float fullEclipseStart() {
		return this.timeOfMiddleOfEclipse - (this.fullEclipseLength / 2);
	}

	// When the sky starts to brighten during the end of an eclipse.
	public float fullEclipseEnd() {
		return this.timeOfMiddleOfEclipse + (this.fullEclipseLength / 2);
	}

	// When the sky stops darkening during the start of an eclipse.
	public float eclipseStartStart() {
		return this.fullEclipseStart() - this.eclipseTransitionLength;
	}

	// When the sky stops brightening during the end of an eclipse
	public float eclipseEndEnd() {
		return this.fullEclipseEnd() + this.eclipseTransitionLength;
	}

}
