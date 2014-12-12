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
public class VirtualConstantPool{
	private final List<Constant> constants = new ArrayList<>();

	public VirtualConstantPool(){
	}
	public String resolve(final String value){
		final StringBuilder sb = new StringBuilder();
		final StringBuilder tmp = new StringBuilder();
		boolean pointer = false;
		for(final char c : value.toCharArray()){
			if(c == '#'){
				pointer = true;
			}else if(pointer){
				if(c >= '0' && c <= '9'){
					tmp.append(c);
				}else{
					sb.append(this.constants.get(Integer.parseInt(tmp.toString())).value);
					pointer = false;
					tmp.setLength(0);
				}
			}else{
				sb.append(c);
			}
		}
		if(tmp.length() > 0){
			sb.append(this.constants.get(Integer.parseInt(tmp.toString())).value);
		}
		return sb.toString();
	}
	public String getUtf8(final int index){
		return ((Utf8Constant)this.get(index)).value;
	}
	public String getString(final int index){
		return ((StringConstant)this.get(index)).value;
	}
	public String getClass(final int index){
		return ((ClassConstant)this.get(index)).value;
	}
	public String getNameAndType(final int index){
		return ((NameAndTypeConstant)this.get(index)).value;
	}
	public String getMethodref(final int index){
		return ((MethodrefConstant)this.get(index)).value;
	}
	public String getFieldref(final int index){
		return ((FieldrefConstant)this.get(index)).value;
	}
	public float getFloat(final int index){
		return ((FloatConstant)this.get(index)).actualValue;
	}
	public String getInterfaceMethodref(final int index){
		return ((InterfaceMethodrefConstant)this.get(index)).value;
	}
	public double getDouble(final int index){
		return ((DoubleConstant)this.get(index)).actualValue;
	}
	public long getLong(final int index){
		return ((LongConstant)this.get(index)).actualValue;
	}
	public int getInteger(final int index){
		return ((IntegerConstant)this.get(index)).actualValue;
	}
	public void setUtf8(final int index, final String value){
		this.set(index, new Utf8Constant(value));
	}
	public void setString(final int index, final String value){
		this.set(index, new StringConstant(value));
	}
	public void setClass(final int index, final String name){
		this.set(index, new ClassConstant(name));
	}
	public void setNameAndType(final int index, final String nameAndType){
		this.set(index, new NameAndTypeConstant(nameAndType));
	}
	public void setMethodref(final int index, final String methodref){
		this.set(index, new MethodrefConstant(methodref));
	}
	public void setFieldref(final int index, final String fieldref){
		this.set(index, new FieldrefConstant(fieldref));
	}
	public void setFloat(final int index, final float value){
		this.set(index, new FloatConstant(value));
	}
	public void setInterfaceMethodref(final int index, final String interfaceMethodref){
		this.set(index, new InterfaceMethodrefConstant(interfaceMethodref));
	}
	public void setDouble(final int index, final double value){
		this.set(index, new DoubleConstant(value));
	}
	public void setLong(final int index, final long value){
		this.set(index, new LongConstant(value));
	}
	public void setInteger(final int index, final int value){
		this.set(index, new IntegerConstant(value));
	}
	private Constant get(final int index){
		return this.constants.get(index);
	}
	private void set(final int index, final Constant constant){
		if(index == this.constants.size()){
			this.constants.add(constant);
		}else if(index < this.constants.size()){
			this.constants.set(index, constant);
		}else{
			while(this.constants.size() < index){
				this.constants.add(null);
			}
			this.constants.add(constant);
		}
	}

	private static abstract class Constant{
		final String value;

		private Constant(final String value){
			this.value = value;
		}
	}
	private static final class Utf8Constant extends Constant{
		private Utf8Constant(final String value){
			super(value);
		}
	}
	private static final class StringConstant extends Constant{
		private StringConstant(final String value){
			super(value);
		}
	}
	private static final class ClassConstant extends Constant{
		private ClassConstant(final String name){
			super(name);
		}
	}
	private static final class NameAndTypeConstant extends Constant{
		private NameAndTypeConstant(final String nameAndType){
			super(nameAndType);
		}
	}
	private static final class MethodrefConstant extends Constant{
		private MethodrefConstant(final String methodref){
			super(methodref);
		}
	}
	private static final class FieldrefConstant extends Constant{
		private FieldrefConstant(final String fieldref){
			super(fieldref);
		}
	}
	private static final class FloatConstant extends Constant{
		private final float actualValue;

		private FloatConstant(final float value){
			super(String.valueOf(value));
			this.actualValue = value;
		}
	}
	private static final class InterfaceMethodrefConstant extends Constant{
		private InterfaceMethodrefConstant(final String interfaceMethodref){
			super(interfaceMethodref);
		}
	}
	private static final class DoubleConstant extends Constant{
		private final double actualValue;

		private DoubleConstant(final double value){
			super(String.valueOf(value));
			this.actualValue = value;
		}
	}
	private static final class LongConstant extends Constant{
		private final long actualValue;

		private LongConstant(final long value){
			super(String.valueOf(value));
			this.actualValue = value;
		}
	}
	private static final class IntegerConstant extends Constant{
		private final int actualValue;

		private IntegerConstant(final int value){
			super(String.valueOf(value));
			this.actualValue = value;
		}
	}
}
