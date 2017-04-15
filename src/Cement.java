public class Cement {
	private int building_cost;
	private int creating_time;
	private boolean isClicked;
	private int x;
	private int y;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public Cement(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		isClicked = false;

	}

	public int getBuilding_cost() {
		return building_cost;
	}

	public void setBuilding_cost(int building_cost) {
		this.building_cost = building_cost;
	}

	public int getCreating_time() {
		return creating_time;
	}

	public void setCreating_time(int creating_time) {
		this.creating_time = creating_time;
	}
}
