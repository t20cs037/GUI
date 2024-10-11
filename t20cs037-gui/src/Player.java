import java.awt.Graphics;

public class Player {
	private int x;
	private int y;
	private int w;
	private int h;
	private int heart;
	
	public Player(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		w = 50;
		h = 60;
		heart = 3;
	}
	
	public int getX() { return x;}
	public int getY() { return y;}
	public int getXW() { return x+w-1; }
	public int getYH() { return y+h-1; }
	public int getHeart() { return heart; }
	//画面端でないかつ障害物がない場合に右に進む
	public void moveRight(Model model) { 
		if(x+w < model.getView().getWidth() && !model.getObstacles().existObstacles(this, "right"))
			x += 2; 
	}
	//画面端でないかつ障害物がない場合に左に進む
	public void moveLeft(Model model) { 
		if(0 < x && !model.getObstacles().existObstacles(this, "left"))
			x -= 2; 
	}
	//画面端でないかつ障害物がない場合に上に進む
	public void moveUp(Model model) { 
		if(0 < y && !model.getObstacles().existObstacles(this, "up"))
			y -= 2; 
	}
	//画面端でないかつ障害物がない場合に下に進む
	public void moveDown(Model model) { 
		if(y+h < model.getView().getHeight() && !model.getObstacles().existObstacles(this, "down"))
			y += 2; 
	}
	//プレイヤーの座標を初期位置に戻す
	public void resetXY() {
		x = 300;
		y = 250;
	}
	//プレイヤーのライフを初期値に戻す
	public void resetHeart() {
		heart = 3;
	}
	//プレイヤーのライフを１減らす
	public void decreaseHeart(){
		heart--;
	}
	
	public void paint(Graphics g, View view) {
		//プレイヤーの画像を描画する
		g.drawImage(view.getPlayerImage(), x, y, w, h, view);
	}
	
}
