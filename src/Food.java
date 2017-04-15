public class Food {
	private int amount;
	private int x, y;
	private boolean isClicked;
	private Point up_co, down_co, right_co, left_co;
	public Point getUp_co() {
		return up_co;
	}

	public void setUp_co(Point up_co) {
		this.up_co = up_co;
	}

	public Point getDown_co() {
		return down_co;
	}

	public void setDown_co(Point down_co) {
		this.down_co = down_co;
	}

	public Point getRight_co() {
		return right_co;
	}

	public void setRight_co(Point right_co) {
		this.right_co = right_co;
	}

	public Point getLeft_co() {
		return left_co;
	}

	public void setLeft_co(Point left_co) {
		this.left_co = left_co;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Food(int amount, int x, int y) {

		this.amount = amount;
		this.x = x;
		this.y = y;
		isClicked = false;
	}
}
