/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {
	private int[] table;

	public KMP(String pattern, String text) {
		long startTime = System.nanoTime();
		this.table = new int[pattern.length()]; // create a new table of pattern length for prefix table
		this.table[0] = -1; // these will always be assigned to these values
		this.table[1] = 0;
		int index = 0; // start at index 0

		for (int i = 2; i < pattern.length(); i++) { // starts @ 2
			if (text.charAt(i - 1) == text.charAt(index)) { // now we want to compare the characters
				this.table[i] = index; // put value of index in table if found
				index++; // increase index as its successful
				i++; // increase i
			} else if (index > 0)
				index = this.table[index - 1]; // if the match is not found and not == to 0 then decrease index

			else {
				this.table[i] = 0; // otherwise put 0 in the prefix table and increase i
				i++;
			}
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration / 1000);
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 *
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		if (pattern.length() == 0)
			return 0; // starting index will be 0

		int i = 0;
		int j = 0;
		while (j + i < text.length()) { // loop through text
			if (pattern.charAt(i) == text.charAt(j + i)) { // check matching
				i++; // increment
				if (i == this.table.length) {
					return j; // if its at its length then return
				}
			} else if (this.table[i] == -1) {
				j = j + i + 1;
				i = 0;
			} else {
				j = j + i - this.table[i];
				i = this.table[i];
			}
		}

		return -1; // not found
	}
}
