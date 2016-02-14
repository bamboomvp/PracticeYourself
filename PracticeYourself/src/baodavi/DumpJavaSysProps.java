package baodavi;

import java.util.Properties;

public class DumpJavaSysProps {
	public static void main(String[] args) {
		Properties props = System.getProperties();
		props.list(System.out);
	}
}
