import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class Prog4 {

	public static void main (String[] args) {
		
		OperatingSystemMXBean opsys = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		System.out.println("Phys Mem: " + opsys.getFreePhysicalMemorySize());
		System.out.println("Virtual Mem: " + opsys.getCommittedVirtualMemorySize());
		System.out.println("Free swap space: " + opsys.getFreeSwapSpaceSize());
	
		double[] C = {0.5, 0.6, 0.7, 0.8, 0.9, 0.95, 0.99, 1.0, 1.01, 1.1, 1.5, 2, 5, 10, 50};
		long freeBytes = opsys.getFreePhysicalMemorySize();

		for (int i = 0; i < 15; i++) {
			System.out.println("-----------------------\n" + "Cache Size: " + C[i] + "*M");
			long start = System.currentTimeMillis();

			int numBytes = Math.abs((int)(C[i] * (freeBytes)));
			int size = numBytes/4;
			int[] testArr = new int[size];

			System.out.println("Phys Mem: " + opsys.getFreePhysicalMemorySize());
			System.out.println("Virtual Mem: " + opsys.getCommittedVirtualMemorySize());
			System.out.println("Free swap space: " + opsys.getFreeSwapSpaceSize());
			
			for (int j = 0; j < size; j++) {
				testArr[j] = i + 1;
			}
			for (int j = 0; j < size; j++) {
				testArr[j] -= 2;
			}
			
			System.out.println("Time elapsed: " + ((double) System.currentTimeMillis() - start) + " milliseconds");

		}
	
	}
}
