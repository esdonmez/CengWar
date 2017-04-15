public class CengMan {
	private String symbol;
	private int ID;
	private int life;
	private int level;
	private int damage;
	private Stack<Box> bag;
	private int locx;
	private int locy;
	private boolean isClicked;
	private boolean isMoving;
	private int timer;
	public static int[][] playermaze = new int[19][49];
	public static int[][] computermaze = new int[19][49];
	private Stack<Node> path1;
	private Stack<Node> path;
	private Point up_co, down_co, right_co, left_co;
	private int finalx;
	private int finaly;
	private boolean isFighting;
	public Stack<Node> viewRange;
	public Stack<Node> getViewRange() {
		return viewRange;
	}


	public void setViewRange(Stack<Node> viewRange) {
		this.viewRange = viewRange;
	}



	public int getFinalx() {
		return finalx;
	}

	public void setFinalx(int finalx) {
		this.finalx = finalx;
	}

	public int getFinaly() {
		return finaly;
	}

	public void setFinaly(int finaly) {
		this.finaly = finaly;
	}

	public boolean isFighting() {
		return isFighting;
	}

	public void setFighting(boolean isFighting) {
		this.isFighting = isFighting;
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

	public Stack<Node> getPath() {
		return path;
	}

	public void setPath(Stack<Node> path) {
		this.path = path;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public Stack<Node> getPath1() {
		return path1;
	}

	public void setPath1(Stack<Node> path1) {
		this.path1 = path1;
	}

	public CengMan(String symbol, int iD, int life, int level, int damage,
			Stack<Box> bag, int locx, int locy) {
		this.symbol = symbol;
		this.ID = iD;
		this.life = life;
		this.level = level;
		this.damage = damage;
		this.bag = bag;
		this.locx = locx;
		this.locy = locy;
		setClicked(false);
		this.path1 = null;
		setMoving(false);
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Stack<Box> getBag() {
		return bag;
	}

	public void setBag(Stack<Box> bag) {
		this.bag = bag;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getLocx() {
		return locx;
	}

	public void setLocx(int locx) {
		this.locx = locx;
	}

	public int getLocy() {
		return locy;
	}

	public void setLocy(int locy) {
		this.locy = locy;
	}
}