import java.util.Comparator;

public class Compare implements Comparator<HuffmanNode> {

	@Override
	public int compare(HuffmanNode o1, HuffmanNode o2) {
		return o1.getAmount() - o2.getAmount();
	}

}
