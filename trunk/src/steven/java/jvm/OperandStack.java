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
public class OperandStack{
	private final List<Variable> elements = new ArrayList<>();

	public OperandStack(){
	}
	public void push(final Object reference){
		this.elements.add(new ReferenceVariable(reference));
	}
	public Variable pop(){
		return this.elements.remove(this.elements.size() - 1);
	}
	public Object popReference(){
		final Variable e = this.pop();
		if(e instanceof ReferenceVariable){
			return ((ReferenceVariable)e).getValue();
		}
		throw new JVMException("Element [" + e + "] is not of type reference.");
	}
}
