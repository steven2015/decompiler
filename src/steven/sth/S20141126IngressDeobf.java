/**
 *
 */
package steven.sth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Steven
 *
 */
public class S20141126IngressDeobf{
	public static final void main(final String[] args) throws Exception{
		try(final InputStream is = new FileInputStream("V:\\shared\\init.txt"); final InputStreamReader isr = new InputStreamReader(is); final BufferedReader br = new BufferedReader(isr);){
			final StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null){
				boolean equal = false;
				for(final char c : line.toCharArray()){
					if(c == '='){
						equal = true;
					}
					if(c >= 128 && equal){
						sb.append(Integer.toString(c));
					}else{
						sb.append(c);
					}
				}
				System.out.println(sb.toString());
				sb.setLength(0);
			}
		}
	}
}
