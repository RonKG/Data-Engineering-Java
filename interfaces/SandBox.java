package interfaces;

public class SandBox {
	public static void main(String[] args) {
		int n = 1000;
		int length = (int)(Math.log10(n)+1);
		System.out.println(length);
		
		int y = "xc".length();
		System.out.println(y);
	}
}