/**
 *
 */
package steven.java.jvm.virtual;

import java.util.Date;

/**
 * @author Steven
 *
 */
public class VirtualClass{
	private String classfile;
	private Date lastModified;
	private long size;
	private String md5Checksum;
	private String compileFrom;
	private String fullName;
	private String sourceFile;
	private int minorVersion;
	private int majorVersion;
	private boolean publicFlag;
	private boolean finalFlag;
	private boolean superFlag;
	private boolean interfaceFlag;
	private boolean abstractFlag;
	private boolean syntheticFlag;
	private boolean annotationFlag;
	private boolean enumFlag;

	public VirtualClass(){
	}
	public String toString(){
		return "";
	}
	public final String getClassfile(){
		return this.classfile;
	}
	public final void setClassfile(final String classfile){
		this.classfile = classfile;
	}
	public final Date getLastModified(){
		return this.lastModified;
	}
	public final void setLastModified(final Date lastModified){
		this.lastModified = lastModified;
	}
	public final long getSize(){
		return this.size;
	}
	public final void setSize(final long size){
		this.size = size;
	}
	public final String getMd5Checksum(){
		return this.md5Checksum;
	}
	public final void setMd5Checksum(final String md5Checksum){
		this.md5Checksum = md5Checksum;
	}
	public final String getCompileFrom(){
		return this.compileFrom;
	}
	public final void setCompileFrom(final String compileFrom){
		this.compileFrom = compileFrom;
	}
	public final String getFullName(){
		return this.fullName;
	}
	public final void setFullName(final String fullName){
		this.fullName = fullName;
	}
	public final String getSourceFile(){
		return this.sourceFile;
	}
	public final void setSourceFile(final String sourceFile){
		this.sourceFile = sourceFile;
	}
	public final int getMinorVersion(){
		return this.minorVersion;
	}
	public final void setMinorVersion(final int minorVersion){
		this.minorVersion = minorVersion;
	}
	public final int getMajorVersion(){
		return this.majorVersion;
	}
	public final void setMajorVersion(final int majorVersion){
		this.majorVersion = majorVersion;
	}
	public final boolean isPublicFlag(){
		return publicFlag;
	}
	public final void setPublicFlag(boolean publicFlag){
		this.publicFlag = publicFlag;
	}
	public final boolean isFinalFlag(){
		return finalFlag;
	}
	public final void setFinalFlag(boolean finalFlag){
		this.finalFlag = finalFlag;
	}
	public final boolean isSuperFlag(){
		return superFlag;
	}
	public final void setSuperFlag(boolean superFlag){
		this.superFlag = superFlag;
	}
	public final boolean isInterfaceFlag(){
		return interfaceFlag;
	}
	public final void setInterfaceFlag(boolean interfaceFlag){
		this.interfaceFlag = interfaceFlag;
	}
	public final boolean isAbstractFlag(){
		return abstractFlag;
	}
	public final void setAbstractFlag(boolean abstractFlag){
		this.abstractFlag = abstractFlag;
	}
	public final boolean isSyntheticFlag(){
		return syntheticFlag;
	}
	public final void setSyntheticFlag(boolean syntheticFlag){
		this.syntheticFlag = syntheticFlag;
	}
	public final boolean isAnnotationFlag(){
		return annotationFlag;
	}
	public final void setAnnotationFlag(boolean annotationFlag){
		this.annotationFlag = annotationFlag;
	}
	public final boolean isEnumFlag(){
		return enumFlag;
	}
	public final void setEnumFlag(boolean enumFlag){
		this.enumFlag = enumFlag;
	}
}
