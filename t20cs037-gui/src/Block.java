import java.awt.Graphics;

//マップ上にある壁（ブロック）を一つ一つオブジェクトとして管理するためのクラス
public class Block {
	private int x;
	private int y;
	private int w;
	private int h;
	
	public Block(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		w = 50;
		h = 50;
	}
	
	//ブロックの画像を描画する
	public void paint(Graphics g, View view) {
		g.drawImage(view.getBlockImage(), x, y, w, h, view);
	}
	
	public int getX() { return x;}
	public int getY() { return y;}
	public int getXW() { return x+w-1; }
	public int getYH() { return y+h-1; }
}
