/**
 *
 */
package steven.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven
 *
 */
public class ConstantPool{
	private final List<Constant> constants = new ArrayList<>();

	public ConstantPool(){
	}
	public String getString(final int index){
		return ((StringConstant)this.get(index)).value;
	}
	public void set(final int index, final String value){
		this.ensureCapacity(index);
		this.constants.set(index, new StringConstant(value));
	}
	private Constant get(final int index){
		return this.constants.get(index);
	}
	private void ensureCapacity(final int index){
		while(this.constants.size() < index){
			this.constants.add(null);
		}
	}

	private static interface Constant{
	}
	private static final class StringConstant implements Constant{
		private final String value;

		private StringConstant(final String value){
			this.value = value;
		}
	}
}
