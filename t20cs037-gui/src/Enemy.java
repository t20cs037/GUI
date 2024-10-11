import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	private int x;
	private int y;
	private int w;
	private int h;
	private boolean ex; //先に進めるか
	private String di; //方向
	private int vi; //視界
	public static final String RIGHT = "right";
	public static final String LEFT = "left";
	public static final String UP = "up";
	public static final String DOWN = "down";
	
	public Enemy(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		w = 50;
		h = 60;
		ex = false;
		di = "";
		vi = 150; //3マス分
		
	}
	
	public int getX() { return x;}
	public int getY() { return y;}
	public int getXW() { return x+w-1; }
	public int getYH() { return y+h-1; }
	public boolean getEx(){ return ex; }
	public String getDi() { return di; }
	
	public void setEx() {
		ex = true;
	}
	
	public void setRight() {
		di = RIGHT;
	}
	public void setLeft() {
		di = LEFT;
	}
	public void setUp() {
		di = UP;
	}
	public void setDown() {
		di = DOWN;
	}
	//画面端でないかつ障害物がない場合に右に進む
	public void moveRight(Model model) { 
		if(x+w < model.getView().getWidth() && !model.getObstacles().existObstacles(this, RIGHT))
			x += 2; 
		else
			ex = false;
	}
	//画面端でないかつ障害物がない場合に左に進む
	public void moveLeft(Model model) { 
		if(0 < x && !model.getObstacles().existObstacles(this, LEFT))
			x -= 2; 
		else
			ex = false;
	}
	//画面端でないかつ障害物がない場合に上に進む
	public void moveUp(Model model) { 
		if(0 < y && !model.getObstacles().existObstacles(this, UP))
			y -= 2; 
		else
			ex = false;
	}
	//画面端でないかつ障害物がない場合に下に進む
	public void moveDown(Model model) { 
		if(y+h < model.getView().getHeight() && !model.getObstacles().existObstacles(this, DOWN))
			y += 2; 
		else
			ex = false;
	}
	//diの方向に合わせて直進する
	public void moveStraight(Model model) {
		switch(di) {
		case RIGHT:
			moveRight(model);
			break;
		case LEFT:
			moveLeft(model);
			break;
		case UP:
			moveUp(model);
			break;
		case DOWN:
			moveDown(model);
			break;
		default:
			break;
		}
	}
	
	//foundPlayerSubによって敵がプレイヤーを見つけたかつ、障害物の向こう側にプレイヤーがいないときは見つけた判定をする
	public boolean foundPlayer(Model model) {
	    if (!foundPlayerSub(model.getPlayer())) {
	        return false;
	    }

	    for (int i = 0; i < vi; i++) {
	        if (existObstaclesInDirection(model, i)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	//方向によって座標の加算減算の仕方を変え、その座標において障害物が存在すればtrueを返す
	private boolean existObstaclesInDirection(Model model, int i) {
	    int newX = x;
	    int newY = y;

	    switch (di) {
	        case RIGHT:
	            newX += i - w;
	            break;
	        case LEFT:
	            newX -= i;
	            break;
	        case UP:
	            newY -= i;
	            break;
	        case DOWN:
	            newY += i - h;
	            break;
	        default:
	            break;
	    }

	    return model.getObstacles().existObstacles(new Enemy(newX, newY), di);
	}
	
	
	//敵の向きの方向３マス分(vi=150)内にプレイヤーがいたら見つけた判定をする
	public boolean foundPlayerSub(Player p) {
		//敵の上または下３マスにプレイヤーがいるか
		if((x <= p.getXW() && p.getX() <= x+w-1) && (((y-vi <= p.getYH() && y > p.getY()) && di.equals(UP)) || ((y+h+vi-2 >= p.getY() && y < p.getY()) && di.equals(DOWN)))
				//敵の右または左３マスにプレイヤーがいるか
				|| ((y <= p.getYH() && p.getY() <= y+h-1) && (((x+w+vi-2 >= p.getX() && x < p.getX()) && di.equals(RIGHT)) || ((x-vi <= p.getXW() && x > p.getX()) && di.equals(LEFT)))))
			return true;

		return false;
	}
	
	//敵の位置を初期値に戻す。敵は各数字に対応して別々の初期値になる。
	public void resetEnemy(int i) {
		if(i == 1) {
			x = 1000;
			y = 800;
		}
		else if(i == 2){
			x = 100;
			y = 100;
		}
		else {
			x = 1000;
			y = 100;
		}
	}
	
	public void paint(Graphics g, View view) {
        // 敵がプレイヤーを見つけられる範囲を半透明な黄色で描画する
        Color vicolor = new Color(255, 255, 0, 130);
        g.setColor(vicolor);
        
        paintVision(g, view);

        // 敵の画像を描画する
        g.drawImage(view.getEnemyImage(), x, y, w, h, view);
    }
	//敵の視界を描画する。createEnemyで敵がどこまで行くと障害物にぶつかるかを判定しそこまでで描画を止める
    private void paintVision(Graphics g, View view) {
        for (int i = 0; i < vi; i++) {
            if (!view.getModel().getObstacles().existObstacles(createEnemy(i), di)) {
                fillRectangle(g, i);
            } else {
                break;
            }
        }
    }
    //敵の視界を描画する上で、障害物があればそ個までで描画をストップするために一時的な敵クラスを作成するもの
    private Enemy createEnemy(int i) {
        int newX = x;
        int newY = y;
        switch (di) {
            case RIGHT:
                newX += i;
                break;
            case LEFT:
                newX -= i;
                break;
            case UP:
                newY -= i;
                break;
            case DOWN:
                newY += i;
                break;
            default:
                break;
        }
        return new Enemy(newX, newY);
    }
   //視界を実際に描画をする関数
    private void fillRectangle(Graphics g, int i) {
        switch (di) {
            case RIGHT:
                g.fillRect(x + w + i, y, 1, h);
                break;
            case LEFT:
                g.fillRect(x - i, y, 1, h);
                break;
            case UP:
                g.fillRect(x, y - i, w, 1);
                break;
            case DOWN:
                g.fillRect(x, y + h + i, w, 1);
                break;
            default:
                break;
        }
    }
}

