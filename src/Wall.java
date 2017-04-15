
public class Wall {
private int life;
private int x;
private int y;
private boolean isAttacking;




public boolean isAttacking() {
	return isAttacking;
}

public void setAttacking(boolean isAttacking) {
	this.isAttacking = isAttacking;
}


public Wall(int life, int x, int y) {
	
	this.life = life;
	this.x = x;
	this.y = y;
}
public int getLife() {
	return life;
}
public void setLife(int life) {
	this.life = life;
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

}
