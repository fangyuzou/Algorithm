public class Shuffle {
	public void shuffle(int[] nums) {
		int len = nums.length;
		Random rand = new Random(System.nanoTime());	// Use the system nano time as seed
		for (int i = 0; i < len; i++) {
			int r = i + rand.nextInt(len - i);
			int temp = nums[i];
			nums[i] = nums[r];
			nums[r] = nums[i];
		}
	}
}
