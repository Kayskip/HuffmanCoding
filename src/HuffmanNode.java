
public class HuffmanNode {

	public HuffmanNode left;
	public HuffmanNode right;
	public String binary;

	private char character;
	private int amount;

	public HuffmanNode(Character character, int amount) {
		this.setCharacter(character);
		this.setAmount(amount);
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
