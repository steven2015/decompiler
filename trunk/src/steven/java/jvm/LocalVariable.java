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
public class LocalVariable{
	private final List<Variable> variables = new ArrayList<>();

	public LocalVariable(){
	}
	public Variable get(final int index){
		return this.variables.get(index);
	}
	public Object getReference(final int index){
		final Variable v = this.get(index);
		if(v instanceof ReferenceVariable){
			return ((ReferenceVariable)v).getValue();
		}
		throw new JVMException("VirtualVariable [" + v + "] is not of type reference.");
	}
	public void set(final int index, final Object reference){
		this.ensureCapacity(index);
		this.variables.set(index, new ReferenceVariable(reference));
	}
	private void ensureCapacity(final int index){
		while(this.variables.size() < index){
			this.variables.add(null);
		}
	}
}
