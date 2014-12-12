/**
 *
 */
package steven.java.jvm;

/**
 * @author Steven
 *
 */
public class ReferenceVariable implements Variable{
	private Object value;

	public ReferenceVariable(final Object value){
		this.value = value;
	}
	@Override
	public void reset(){
		this.value = null;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("reference: ");
		if(value == null){
			sb.append("null");
		}else{
			sb.append(value.getClass().getName() + "@" + System.identityHashCode(value));
		}
		return sb.toString();
	}
	public final Object getValue(){
		return this.value;
	}
	public final void setValue(final Object value){
		this.value = value;
	}
}
