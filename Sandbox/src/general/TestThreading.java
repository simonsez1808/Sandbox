package general;

public class TestThreading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NumberGenerator ng0 = new NumberGenerator(0);
		ng0.start();

		NumberGenerator ng1 = new NumberGenerator(1);
		ng1.start();

	}

}

class NumberGenerator extends Thread {

	Integer prefix;

	NumberGenerator(Integer prefix) {
		this.prefix = prefix;
	}

	@SuppressWarnings("static-access")
	public void run() {
		for (int i = 0; i < 1000; i++) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(prefix);
		}
	}

}
