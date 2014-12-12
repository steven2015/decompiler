/**
 *
 */
package steven.java.jvm.virtual;

/**
 * @author Steven
 *
 */
public class VirtualReferenceVariable implements VirtualVariable{
	private String className;
	private String variableName;

	public VirtualReferenceVariable(final String className, final String variableName){
		this.className = className;
		this.variableName = variableName;
	}
	@Override
	public void reset(){
		this.className = null;
		this.variableName = null;
	}
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		sb.append("reference: ");
		if(this.className == null){
			sb.append("null");
		}else{
			sb.append(this.className + "@" + this.variableName);
		}
		return sb.toString();
	}
	public final String getClassName(){
		return this.className;
	}
	public final void setClassName(final String className){
		this.className = className;
	}
	public final String getVariableName(){
		return this.variableName;
	}
	public final void setVariableName(final String variableName){
		this.variableName = variableName;
	}
}
