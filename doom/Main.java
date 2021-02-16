package doom;

public class Main {

	private static Main instance;
	
	/**
	 *	I am absolutely not liable for anything, Viruses are illegal and using them for malicious activites is against the law and can result in jail time.
	 *  What you choose to do with this is your choice but I am NOT liable for ANYTHING... 
	 */
	
	public Main() {
		instance = this;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 35; i++) {
			new Adapter(instance).setup();
		}
	}

	public static Main getInstance() {
		return instance;
	}
}
