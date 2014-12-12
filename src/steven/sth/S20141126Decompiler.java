/**
 *
 */
package steven.sth;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import steven.java.jvm.virtual.VirtualConstantPool;

/**
 * @author Steven
 *
 */
public class S20141126Decompiler{
	public static final void main(final String[] args) throws Exception{
		final VirtualConstantPool constantPool = new VirtualConstantPool();
		final String clazzFilePath = "V:\\shared\\bbq.class";
		final ProcessBuilder pb = new ProcessBuilder("javap", "-v", "-l", "-p", "-c", "-s", "-sysinfo", "-constants", clazzFilePath);
		final Process p = pb.start();
		try(final InputStream is = p.getInputStream(); final InputStreamReader isr = new InputStreamReader(is); final BufferedReader br = new BufferedReader(isr);){
			String line = null;
			System.out.println("// " + br.readLine());
			System.out.println("// " + br.readLine());
			System.out.println("// " + br.readLine());
			System.out.println(br.readLine() + "{");
			while((line = br.readLine()) != null){
				if("Constant pool:".equals(line)){
					break;
				}
			}
			// constant pool
			while((line = br.readLine()) != null){
				if("{".equals(line)){
					break;
				}
				int index = -1;
				boolean error = false;
				final String hashIndex = line.substring(0, 8).trim();
				final String type = line.substring(10, 29).trim();
				final int commentIndex = line.indexOf("//");
				final String value;
				if("Utf8".equals(type)){
					value = line.substring(29);
				}else if(commentIndex >= 0){
					value = constantPool.resolve(line.substring(29, commentIndex));
				}else{
					value = constantPool.resolve(line.substring(29));
				}
				if(hashIndex.startsWith("#")){
					index = Integer.parseInt(hashIndex.substring(1));
				}else{
					error = true;
				}
				if("Utf8".equals(type)){
					constantPool.setUtf8(index, value);
				}else if("Class".equals(type)){
					constantPool.setClass(index, value);
				}else if("NameAndType".equals(type)){
					constantPool.setNameAndType(index, value);
				}else if("String".equals(type)){
					constantPool.setString(index, value);
				}else if("Methodref".equals(type)){
					constantPool.setMethodref(index, value);
				}else if("Fieldref".equals(type)){
					constantPool.setFieldref(index, value);
				}else if("Float".equals(type)){
					if("NaNf".equals(value)){
						constantPool.setFloat(index, Float.NaN);
					}else{
						constantPool.setFloat(index, Float.parseFloat(value));
					}
				}else if("InterfaceMethodref".equals(type)){
					constantPool.setInterfaceMethodref(index, value);
				}else if("Double".equals(type)){
					constantPool.setDouble(index, Double.parseDouble(value));
				}else if("Long".equals(type) && (value.endsWith("l") || value.endsWith("L"))){
					constantPool.setLong(index, Long.parseLong(value.substring(0, value.length() - 1)));
				}else if("Integer".equals(type)){
					constantPool.setInteger(index, Integer.parseInt(value));
				}else{
					error = true;
				}
				if(error){
					System.out.println("error " + line);
				}
			}
			// fields/methods
			while((line = br.readLine()) != null){
				if("}".equals(line)){
					break;
				}
				boolean error = false;
				if(line.endsWith(";")){
					if(line.equals("  static {};") || line.indexOf('(') >= 0){
						// method
						if(line.equals("  static {};")){
							System.out.println("static{");
						}else{
							System.out.println(line.substring(0, line.length() - 1) + "{");
						}
						if(br.readLine().startsWith("    descriptor:") == false){
							error = true;
						}
						if(br.readLine().startsWith("    flags:") == false){
							error = true;
						}
						if(br.readLine().startsWith("    Code:") == false){
							error = true;
						}
						br.readLine(); // stack, locals, args_size
					}else{
						// field
						System.out.println(line);
						if(br.readLine().startsWith("    descriptor:") == false){
							error = true;
						}
						if(br.readLine().startsWith("    flags:") == false){
							error = true;
						}
						line = br.readLine();
						if(line.startsWith("    Signature:") == false && line.length() > 0){
							error = true;
						}
					}
				}
				if(error){
					System.out.println("error " + line);
				}
			}
		}
	}
}
