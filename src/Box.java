public class Box {
	private String symbol;
	private int amount;
	private Point coordinat;

	public Point getCoordinat() {
		return coordinat;
	}

	public void setCoordinat(Point coordinat) {
		this.coordinat = coordinat;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Box(String symbol, int amount) {
		
		this.symbol = symbol;
		this.amount = amount;
	}
}
