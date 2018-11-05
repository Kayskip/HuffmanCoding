import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	/**
	 * This would be a good place to compute and store the tree.
	 */
	private Map<Character, String> encode = new HashMap<Character, String>();
	private Map<String, Character> decode = new HashMap<String, Character>();

	public HuffmanCoding(String text) {

		Map<Character, Integer> characters = new HashMap<Character, Integer>();

		for (int i = 0; i < text.length(); i++) {
			if (characters.containsKey(text.charAt(i))) {
				characters.put(text.charAt(i), characters.get(text.charAt(i)) + 1);
			} else {
				characters.put(text.charAt(i), 1);
			}

		}

		Comparator<HuffmanNode> comparator = new Compare();
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(comparator);

		for (Entry<Character, Integer> entry : characters.entrySet()) {
			queue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));

		}
		while (queue.size() > 1) {
			HuffmanNode left = queue.poll();
			HuffmanNode right = queue.poll();

			HuffmanNode parent = new HuffmanNode('\0', left.getAmount() + right.getAmount());
			parent.left = left;
			parent.right = right;
			queue.offer(parent);
		}

		HuffmanNode root = queue.poll();

		traverse(root, "");
		for (Entry<Character, String> entry : encode.entrySet()) {
			decode.put(entry.getValue(), entry.getKey());
		}
		for (Entry<String, Character> entry : decode.entrySet()) {
			System.out.println(entry.getValue() + " " + entry.getKey());
		}

		System.out.println(encode.size());
	}

	public void traverse(HuffmanNode node, String prefix) {
		if (node == null)
			return;

		if (node.getCharacter() != '\0')
			encode.put(node.getCharacter(), prefix);

		traverse(node.left, prefix + "0");
		traverse(node.right, prefix + "1");
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should return
	 * the encoded text as a binary string, that is, a string containing only 1 and
	 * 0.
	 */
	public String encode(String text) {
		StringBuilder string = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			string.append(encode.get(text.charAt(i)));
		}
		return string.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree, and
	 * return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		StringBuilder string = new StringBuilder();
		String current = "";

		for (int i = 0; i < encoded.length(); i++) {
			current += encoded.charAt(i);

			if (decode.containsKey(current)) {
				string.append(decode.get(current));
				current = "";
			}

		}
		return string.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print out
	 * the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
