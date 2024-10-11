import java.util.LinkedList;

public class Obstacle {
	private LinkedList<Block> blocks;
	private LinkedList<Treasure> treasures;
	
	public Obstacle(LinkedList<Block> blocks, LinkedList<Treasure> treasures) {
		super();
		this.blocks = blocks;
		this.treasures = treasures;
	}
	
	//すべてのブロック、宝箱で敵のそばに障害物があるかを判定をする。１つでもtrueがあればtrueを返す
	public boolean existObstacles(Enemy e, String di) {
		for(int i=0; i<blocks.size(); i++) {
			if(existObstacleE(e, di, blocks.get(i).getX(), blocks.get(i).getY(), blocks.get(i).getXW(), blocks.get(i).getYH()))
				return true;
		}
		for(int i=0; i<treasures.size(); i++) {
			if(existObstacleE(e, di, treasures.get(i).getX(), treasures.get(i).getY(), treasures.get(i).getXW(), treasures.get(i).getYH()) && !treasures.get(i).getOpend())
				return true;
		}
		
		return false;
	}
	//プレイヤー版
	public boolean existObstacles(Player p, String di) {
		for(int i=0; i<blocks.size(); i++) {
			if(existObstacleP(p, di, blocks.get(i).getX(), blocks.get(i).getY(), blocks.get(i).getXW(), blocks.get(i).getYH()))
				return true;
		}
		for(int i=0; i<treasures.size(); i++) {
			if(existObstacleP(p, di, treasures.get(i).getX(), treasures.get(i).getY(), treasures.get(i).getXW(), treasures.get(i).getYH()) && !treasures.get(i).getOpend())
				return true;
		}
		
		return false;
	}
	
	public boolean existObstacleP(Player p, String di, int x, int y, int xw, int yh) {
		//プレイヤーの右に障害物がある
		if(p.getXW() == x-1) {
			if((p.getY() <= yh && p.getYH() >= y) && di.equals(Enemy.RIGHT))
				return true;
		}
		//左
		else if(p.getX() == xw+1) {
			if((p.getY() <= yh && p.getYH() >= y) && di.equals(Enemy.LEFT))
				return true;
		}
		//上
		else if(p.getY() == yh+1) {
			if((p.getX() <= xw && p.getXW() >= x) && di.equals(Enemy.UP))
				return true;
		}
		//下
		else if(p.getYH() == y-1) {
			if((p.getX() <= xw && p.getXW() >= x) && di.equals(Enemy.DOWN))
				return true;
		}
		return false;
	}
	
	public boolean existObstacleE(Enemy e, String di, int x, int y, int xw, int yh) {
		//敵の右に障害物がある
		if(e.getXW() == x-1) {
			if((e.getY() <= yh && e.getYH() >= y) && di.equals(Enemy.RIGHT))
				return true;
		}
		//左
		else if(e.getX() == xw+1) {
			if((e.getY() <= yh && e.getYH() >= y) && di.equals(Enemy.LEFT))
				return true;
		}
		//上
		else if(e.getY() == yh+1) {
			if((e.getX() <= xw && e.getXW() >= x) && di.equals(Enemy.UP))
				return true;
		}
		//下
		else if(e.getYH() == y-1) {
			if((e.getX() <= xw && e.getXW() >= x) && di.equals(Enemy.DOWN))
				return true;
		}
		return false;
	}
}	

