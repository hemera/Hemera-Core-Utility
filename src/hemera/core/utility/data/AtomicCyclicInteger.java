package hemera.core.utility.data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <code>AtomicCyclicInteger</code> defines a data
 * structure that is an <code>AtomicInteger</code>
 * but has a defined minimum and maximum value that
 * if the current value is at the minimum, a
 * decrement operation will set the value to the
 * defined maximum. And if the current value is at
 * the maximum, an increment will set the value to
 * the defined minimum.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.2
 */
public class AtomicCyclicInteger {
	/**
	 * The <code>AtomicInteger</code> instance.
	 */
	private final AtomicInteger value;
	/**
	 * The <code>int</code> minimum value.
	 */
	private final int min;
	/**
	 * The <code>int</code> maximum value.
	 */
	private final int max;
	
	/**
	 * Constructor of <code>AtomicCyclicInteger</code>.
	 * <p>
	 * This constructor sets the initial value to the
	 * specified minimum.
	 * @param min The <code>int</code> minimum value.
	 * @param max The <code>int</code> maximum value.
	 */
	public AtomicCyclicInteger(final int min, final int max) {
		this.value = new AtomicInteger(min);
		this.min = min;
		this.max = max;
	}
	
	/**
	 * Increment the current value and retrieve the
	 * modified value, if it's in range. If the new
	 * value is greater than the maximum value, the
	 * value is set to the minimum value and the next
	 * increment is returned.
	 * @return The <code>int</code> value after the
	 * increment.
	 */
	public int incrementAndGet() {
		final int value = this.value.incrementAndGet();
		if (value > this.max) {
			this.value.set(this.min);
			return this.min;
		} else {
			return value;
		}
	}
	
	/**
	 * Decrement the current value and retrieve the
	 * modified value, if it's in range. If the new
	 * value is less than the minimum value, the value
	 * is set to the maximum value and the next
	 * decrement is returned.
	 * @return The <code>int</code> value after the
	 * decrement.
	 */
	public int decrementAndGet() {
		final int value = this.value.decrementAndGet();
		if (value < this.min) {
			this.value.set(this.max);
			return this.max;
		} else {
			return value;
		}
	}
	
	/**
	 * Retrieve the current value.
	 * @return The <code>int</code> current value.
	 */
	public int get() {
		return this.value.get();
	}
}
