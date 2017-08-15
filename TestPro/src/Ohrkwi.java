import java.util.logging.Logger;


public class Ohrkwi {
	
	Class clazz = this.getClass();
	public static void main(String[] args) {
		Ohrkwi o = new Ohrkwi();
		System.out.println(o.eqa());
	}
	public boolean eqa()
	{
		Class clazz = this.getClass();
		Class s = Ohrkwi.class;
		return clazz.equals(s);
	}
}
