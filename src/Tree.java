
public class Tree {
	String symbol;
	int life, building_cost, creating_time;
	boolean isClicked = true;
	private int x;
	private int y;
	private int food;
	private Point up_co, down_co, right_co, left_co;
	private boolean isAttacking;
	
	
	
	
	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
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

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public Tree(String symbol, int life, int building_cost, int creating_time,int food) {
		this.life = life;
		this.building_cost = building_cost;
		this.creating_time = creating_time;
		this.food=food;
		isClicked = false;
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

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	
	
	
	
}
