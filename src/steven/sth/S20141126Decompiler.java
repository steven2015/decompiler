/**l
 *
 */
package steven.sth;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import steven.java.jvm.virtual.VirtualClass;
import steven.java.jvm.virtual.VirtualConstantPool;

/**
 * @author Steven
 *
 */
public class S20141126Decompiler{
	public static final void main(final String[] args) throws Exception{
		final VirtualClass clazz = new VirtualClass();
		final VirtualConstantPool constantPool = new VirtualConstantPool();
		final String clazzFilePath = "E:\\Java\\20141201MockLocation\\bin\\steven\\sth\\S20141206FloatDoubleTest.class";
		final ProcessBuilder pb = new ProcessBuilder("javap", "-v", "-l", "-p", "-c", "-s", "-sysinfo", "-constants", clazzFilePath);
		final Process p = pb.start();
		try(final InputStream is = p.getInputStream(); final InputStreamReader isr = new InputStreamReader(is); final BufferedReader br = new BufferedReader(isr);){
			String line = null;
			while((line = br.readLine()) != null){
				if(line.startsWith("Classfile")){
					clazz.setClassfile(line.substring("Classfile".length()).trim());
				}else if(line.startsWith("  Last modified")){
					final int semicolonIndex = line.indexOf(';');
					final String tmp = line.substring("  Last modified".length(), semicolonIndex).trim();
					clazz.setLastModified(new SimpleDateFormat("yyyy/MM/dd").parse(tmp));
					final int sizeIndex = line.indexOf("size") + "size".length() + 1;
					final int nextSpaceIndex = line.indexOf(' ', sizeIndex);
					clazz.setSize(Long.parseLong(line.substring(sizeIndex, nextSpaceIndex)));
				}else if(line.startsWith("  MD5 checksum")){
					clazz.setMd5Checksum(line.substring("  MD5 checksum".length()).trim());
				}else if(line.startsWith("  Compiled from")){
					clazz.setCompileFrom(line.substring("  Compiled from".length()).trim().replace("\"", ""));
				}else if(line.startsWith("  SourceFile:")){
					clazz.setSourceFile(line.substring("  SourceFile:".length()).trim().replace("\"", ""));
				}else if(line.startsWith("  minor version:")){
					clazz.setMinorVersion(Integer.parseInt(line.substring("  minor version:".length()).trim()));
				}else if(line.startsWith("  major version:")){
					clazz.setMajorVersion(Integer.parseInt(line.substring("  major version:".length()).trim()));
				}else if(line.startsWith("  flags:")){
					final String[] flags = line.substring("  flags:".length()).trim().split(",");
					for(String flag : flags){
						flag = flag.trim();
						if("ACC_PUBLIC".equals(flag)){
							clazz.setPublicFlag(true);
						}else if("ACC_FINAL".equals(flag)){
							clazz.setFinalFlag(true);
						}else if("ACC_SUPER".equals(flag)){
							clazz.setSuperFlag(true);
						}else if("ACC_INTERFACE".equals(flag)){
							clazz.setInterfaceFlag(true);
						}else if("ACC_ABSTRACT".equals(flag)){
							clazz.setAbstractFlag(true);
						}else if("ACC_SYNTHETIC".equals(flag)){
							clazz.setSyntheticFlag(true);
						}else if("ACC_ANNOTATION".equals(flag)){
							clazz.setAnnotationFlag(true);
						}else if("ACC_ENUM".equals(flag)){
							clazz.setEnumFlag(true);
						}else{
							System.out.println("unknown: " + line);
						}
					}
				}else if(line.startsWith("  ") == false && (line.indexOf("class") >= 0 || line.indexOf("interface") >= 0) || line.indexOf("enum") >= 0){
					clazz.setFullName(line.substring(line.lastIndexOf(' ')).trim());
				}else if("Constant pool:".equals(line)){
					break;
				}else{
					System.out.println("unknown: " + line);
				}
			}
			// constant pool
			int valueStartIndex = -1;
			while((line = br.readLine()) != null){
				if("{".equals(line)){
					break;
				}
				final int hashIndex = line.indexOf('#');
				final int equalIndex = line.indexOf('=');
				final int constantIndex = Integer.parseInt(line.substring(hashIndex + 1, equalIndex).trim());
				final int nextSpaceIndex = line.indexOf(' ', equalIndex + 2);
				final String type = line.substring(equalIndex + 2, nextSpaceIndex).trim();
				if(valueStartIndex < 0){
					for(int i = nextSpaceIndex; i < line.length(); i++){
						if(line.charAt(i) != ' '){
							valueStartIndex = i;
							break;
						}
					}
				}
				final String value;
				if("Utf8".equals(type) || "Double".equals(type) || "Long".equals(type) || "Float".equals(type) || "Integer".equals(type)){
					value = line.substring(valueStartIndex);
				}else{
					final int spaceIndex = line.indexOf(' ', valueStartIndex);
					value = line.substring(valueStartIndex, spaceIndex);
				}
				if("Utf8".equals(type)){
					constantPool.setUtf8(constantIndex, value);
				}else if("Class".equals(type)){
					constantPool.setClass(constantIndex, value);
				}else if("NameAndType".equals(type)){
					constantPool.setNameAndType(constantIndex, value);
				}else if("String".equals(type)){
					constantPool.setString(constantIndex, value);
				}else if("Methodref".equals(type)){
					constantPool.setMethodref(constantIndex, value);
				}else if("Fieldref".equals(type)){
					constantPool.setFieldref(constantIndex, value);
				}else if("Float".equals(type)){
					if("NaNf".equals(value)){
						constantPool.setFloat(constantIndex, Float.NaN);
					}else{
						constantPool.setFloat(constantIndex, Float.parseFloat(value));
					}
				}else if("InterfaceMethodref".equals(type)){
					constantPool.setInterfaceMethodref(constantIndex, value);
				}else if("Double".equals(type)){
					constantPool.setDouble(constantIndex, Double.parseDouble(value));
				}else if("Long".equals(type) && (value.endsWith("l") || value.endsWith("L"))){
					constantPool.setLong(constantIndex, Long.parseLong(value.substring(0, value.length() - 1)));
				}else if("Integer".equals(type)){
					constantPool.setInteger(constantIndex, Integer.parseInt(value));
				}else{
					System.out.println("unknown: " + line);
				}
			}
			if(System.currentTimeMillis() > 0){
				return;
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
