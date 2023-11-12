package assaft.healthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthServiceApplication {
	public static final String DELAY_FLAG = "--delay";

	public static void main(String[] args) {

		processDelayFlag(args);
		SpringApplication.run(HealthServiceApplication.class, args);
	}

	private static void processDelayFlag(String[] args) {

		// Default delay time in milliseconds
		int delayTime = 0;

		// Loop through the arguments to find the --delay flag
		for (int index = 0; index < args.length; index++) {
			if (DELAY_FLAG.equals(args[index]) && index + 1 < args.length) {
				try {
					// Parse the next argument as an integer to get the delay time
					delayTime = Integer.parseInt(args[index + 1]);
				} catch (NumberFormatException e) {
					System.err.println("Invalid delay time. Delay flag ignored.");
					return;
				}
				break;
			}
		}

		// If delayTime is greater than 0, sleep for that duration
		if (delayTime > 0) {
			try {
				System.out.println("Sleeping for " + delayTime + " msec");
				Thread.sleep(delayTime);
			} catch (InterruptedException e) {
				System.err.println("Sleep interrupted: " + e.getMessage());
			}
		}
	}

}
