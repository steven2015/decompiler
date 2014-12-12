/**
 *
 */
package steven.java.jvm.virtual;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven
 *
 */
public class VirtualOperandStack{
	private final List<VirtualVariable> elements = new ArrayList<>();

	public VirtualOperandStack(){
	}
	public void pushReference(final String className, final String variableName){
		this.elements.add(new VirtualReferenceVariable(className, variableName));
	}
	public VirtualVariable pop(){
		return this.elements.remove(this.elements.size() - 1);
	}
	public VirtualReferenceVariable popReference(){
		return (VirtualReferenceVariable)this.pop();
	}
}
