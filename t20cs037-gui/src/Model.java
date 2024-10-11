import java.util.LinkedList;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

public class Model {

    private View view;
    private Controller controller;
    private Player player;
    private LinkedList<Enemy> enemies;
    private State state;
    private LinkedList<Treasure> treasures;
    private Money money;
    private Obstacle obstacle;
    private ScoreManagement score;

    // Sample instance variables:
    private int time;
    private String typedChar = "";
    private boolean enableKeyRollover = false;
    private Random random = new Random(System.currentTimeMillis());
    private Instant gamestart, now;
	private long passedSeconds;
	private long cleartime;

    public Model() {
        view = new View(this);
        controller = new Controller(this);
        player = new Player(300, 250);
        enemies = new LinkedList<Enemy>();
        enemies.add(new Enemy(1000, 800));
        enemies.add(new Enemy(100, 0));
        enemies.add(new Enemy(1000, 100));
        state = new TitleState(this);
        treasures = new LinkedList<Treasure>();
        money = new Money(1000);
        score = new ScoreManagement();
        createTreasures();
        obstacle = new Obstacle(view.getMap().getBlocks(), treasures);
        cleartime = 0;
    }

    public synchronized void processTimeElapsed(int msec) {
		//stateの状態に応じて表示される画面が切り替わる
        state = state.processTimeElapsed(msec);
    	 time++;
        view.repaint();
    }

    public synchronized void processKeyTyped(String typed) {
    	//stateの状態に応じて表示される画面が切り替わる
    	state = state.processKeyTyped(typed);
    	typedChar = typed;
    	if (typed.equals("ESC")) { 
    		enableKeyRollover = !enableKeyRollover; // 同時押し許可モード反転
    		controller.setKeyRollover(enableKeyRollover);
        }
    	view.repaint();        
    }

    public synchronized void processMousePressed(int x, int y) {
        //view.playBombSound();
    	 state = state.processMousePressed(x, y);
        view.repaint();
    }

    public void start() {
        controller.start();
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public int getTime() {
        return time;
    }

    public String getTypedChar() {
        return typedChar;
    }
  //プレイヤーを返す
    public Player getPlayer() {
    	return player;
    }
  //敵のリストを返す
    public LinkedList<Enemy> getEnemies() {
    	return enemies;
    }
  //画面の状態を返す
	public State getState() {
		return state;
	}
	//宝箱のリストを返す
	public LinkedList<Treasure> getTreasures() {
		return treasures;
	}
	//所持金を管理するクラスを返す
	public Money getMoney() {
		return money;
	}
	//スコアを管理するクラスを返す
	public ScoreManagement getScore() {
		return score;
	}
	//障害物を管理するクラスを返す
	public Obstacle getObstacles() {
		return obstacle;
	}

    public boolean getEnableKeyRollover() { 
        return enableKeyRollover;
    }
  //クリア時間を返す
    public long getClearTime() {
    	return cleartime;
    }
    //場所は固定で、中に入っているお金の金額をランダムに設定し、treasuresリストに保存する。ゲームが詰まないように合計金額が必要所持金以上になるようにしている
	public void createTreasures() {
		LinkedList<Treasure> newtreasures = new LinkedList<Treasure>();
		ArrayList<Integer> m = new ArrayList<Integer>();
		int countmoney = 0;
		for(int i=0; i < 9; i++) {
			m.add( random.nextInt(5) * 100);
			countmoney += m.get(i);
		}
		if(countmoney < money.getNeed())
			m.add(money.getNeed() - countmoney);
		else {
			m.add(0);
		}
		
		newtreasures.add(new Treasure(100, 100, m.get(0)));
		newtreasures.add(new Treasure(400, 300, m.get(1)));
		newtreasures.add(new Treasure(600, 500, m.get(2)));
		newtreasures.add(new Treasure(100, 500, m.get(3)));
		newtreasures.add(new Treasure(300, 400, m.get(4)));
		newtreasures.add(new Treasure(600, 50, m.get(5)));
		newtreasures.add(new Treasure(800, 600, m.get(6)));
		newtreasures.add(new Treasure(800, 300, m.get(7)));
		newtreasures.add(new Treasure(1000, 500, m.get(8)));
		newtreasures.add(new Treasure(700, 300, m.get(9)));

		treasures = newtreasures;
	}
	//敵が先に進める場合は直進させ、先に進めない場合はランダムに数字を生成してsetEnemyDirectionで方向を決めてもらう
	public void moveEnemy(Enemy enemy) {
		if(enemy.getEx() == true && time%50 != 0) {
			enemy.moveStraight(this);
		}
		else {
			enemy.setEx();
			
			int i = random.nextInt(4);
			setEnemyDirection(enemy, i);
		}
	}
	//moveEnemyでランダムに生成された数字によって進む方向を決定する
	public void setEnemyDirection(Enemy enemy, int i) {
		switch(i) {
		case 0:
			enemy.setUp();
			enemy.moveUp(this);
			break;
		case 1:
			enemy.setLeft();
			enemy.moveLeft(this);
			break;
		case 2:
			enemy.setRight();
			enemy.moveRight(this);
			break;
		case 3:
			enemy.setDown();
			enemy.moveDown(this);
			break;
		case 4:
			int i2 = random.nextInt(4);
			setEnemyDirection(enemy, i2);
			break;
		}
	}
	//ゲームを開始するときに、プレイヤー、敵、宝箱、所持金をリセットする
	public void resetGame() {
		player.resetXY();
		player.resetHeart();
		for(int i=0; i < enemies.size(); i++)
			enemies.get(i).resetEnemy(i);
		money.resetMoney();
		createTreasures();
		obstacle = new Obstacle(view.getMap().getBlocks(), treasures);
		gamestart = Instant.now();
	}
	
	public void setClearTime() {
		cleartime = passedSeconds;
	}
	
	public long setTime() {
		now = Instant.now();
		passedSeconds = ChronoUnit.SECONDS.between(gamestart, now);
		long rest = 120 - passedSeconds;
		return rest;
	}

}
