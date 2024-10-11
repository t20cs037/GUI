import java.awt.Graphics;

public class Treasure {
	private int x;
	private int y;
	private int w;
	private int h;
	private int money;
	private boolean opened;
	
	public Treasure(int x, int y, int money) {
		super();
		this.x = x;
		this.y = y;
		w = 50;
		h = 50;
		this.money = money;
		opened = false;
	}
	
	//宝箱を開けられるかに使うもの
	public boolean existTreasure(Player p) {
		//プレイヤーの右に宝箱がある
		if(p.getXW() == x-1 && (p.getY() <= y+h-1 && p.getYH() >= y)) {
			return true;
		}
		//左
		else if(p.getX() == x+w+1-1 && (p.getY() <= y+h-1 && p.getYH() >= y)) {
			return true;
		}
		//上
		else if(p.getY() == y+h+1-1 && (p.getX() <= x+w-1 && p.getXW() >= x)) {
			return true;
		}
		//下
		else if(p.getYH() == y-1 && (p.getX() <= x+w-1 && p.getXW() >= x)) {
			return true;
		}
		return false;
	}

	public void completeOpened() {
		opened = true;
	}
	
	public void paint(Graphics g, View view) {
		g.drawImage(view.getTreasureImage(), x, y, w, h, view);
	}
	
	public int getMoney() {
		return money;
	}
	
	public boolean getOpend() {
		return opened;
	}

	public int getX() { return x;}
	public int getY() { return y;}
	public int getXW() { return x+w-1; }
	public int getYH() { return y+h-1; }
}
