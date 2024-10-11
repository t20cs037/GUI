import java.awt.Graphics;

public class Exit {
	private int x;
	private int y;
	private int w;
	private int h;
	
	public Exit(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		w = 50;
		h = 90;
	}
	
	public boolean existExit(Player p) {
		//プレイヤーと重なる位置に出口があるときtrueを返す
		if((p.getXW() >= x && p.getX() <= x+w-1) && (p.getY() <= y+h-1 && p.getYH() >= y)) {
			return true;
		}
		return false;		
	}
	
	public void paint(Graphics g, View view) {
		//出口の画像を描画する
		g.drawImage(view.getExitImage(), x, y, w, h, view);
	}
}
