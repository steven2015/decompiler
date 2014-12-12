/**
 *
 */
package steven.java.jvm.instruction;

import steven.java.jvm.ConstantPool;
import steven.java.jvm.LocalVariable;
import steven.java.jvm.OperandStack;
import steven.java.jvm.virtual.VirtualConstantPool;
import steven.java.jvm.virtual.VirtualOperandStack;

/**
 * @author Steven
 *
 */
public abstract class Instruction{
	private final String mnemonic;
	private final int opcode;

	protected Instruction(final String mnemonic, final int opcode){
		this.mnemonic = mnemonic;
		this.opcode = opcode;
	}
	public abstract void execute(int sequence, ConstantPool constants, OperandStack stack, LocalVariable variables, int... operands);
	public abstract void execute(int sequence, VirtualConstantPool constants, VirtualOperandStack stack, LocalVariable variables, int... operands);
	public final String getMnemonic(){
		return this.mnemonic;
	}
	public final int getOpcode(){
		return this.opcode;
	}

	public static final Instruction NEW = new Instruction("new", 187){
		@Override
		public void execute(final int sequence, final ConstantPool constants, final OperandStack stack, final LocalVariable variables, final int... operands){
		}
		@Override
		public void execute(final int sequence, final VirtualConstantPool constants, final VirtualOperandStack stack, final LocalVariable variables, final int... operands){
			stack.pushReference(constants.getClass(operands[0]), "v" + sequence);
		}
	};
}
