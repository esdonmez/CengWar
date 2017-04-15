
public class Base 
{
	private String symbol;
	private int RepairCost;
	private int RepairTime;
	private OldQueue productionQueue;
	private int x;
	private int y1;
	private int y2;
	private int life;
private boolean isAttacking;
	
	
	
	
	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Base(String symbol, int life, int RepairCost, int RepairTime, OldQueue productionQueue)
	{
		this.symbol = symbol;
		this.life = life;
		this.RepairCost = RepairCost;
		this.RepairTime = RepairTime;
		this.productionQueue = productionQueue;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
	
}
