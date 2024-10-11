import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

    private Model model;
    private Map map;

    // Sample instance variables:
    private Image playerimage;
    private Image blockimage;
    private Image treasureimage;
    private Image enemyimage;
    private Image exitimage;
    private Image backgroundimage;
    private Image heartimage;
    private Image bossimage;
    private WavSound sound;
    private Dimension size;

    public View(Model model) {
        this.model = model;
        map = new Map();

        // 画像を読み込む．画像ファイルは src においておくと bin に自動コピーされる
        playerimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("player.png"));
        blockimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("block.png"));
        treasureimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("treasure.png"));
        enemyimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("enemy.png"));
        exitimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("exit.png"));
        backgroundimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("background.png"));
        heartimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("heart.png"));
        bossimage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ボスが来た画面.jpg"));
        // サウンドを読み込む
        sound = new WavSound(getClass().getResource("bomb.wav"));
        size = getSize();
        //マップを生成する
        map.createMap();
      }

    /**
     * 画面を描画する
     * @param g  描画用のグラフィックスオブジェクト
     */
    @Override
    public void paintComponent(Graphics g) {
        // 画面をいったんクリア
        clear(g);

        // 画面の表示
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        State state = model.getState();
        state.paintComponent(g);

        // Linux でアニメーションをスムーズに描画するため（描画結果が勝手に間引かれることを防ぐ）
        getToolkit().sync();
    }
    
  //ファイルを読み取って任意の位置に描画する
  	public void readFile(Graphics g, String file, int x, int y) throws IOException {
  		//URL textURL = getClass().getResource(file);
  		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
  			String line;
  			int row = y;
  			while((line = reader.readLine()) != null) {
  				int intline = Integer.parseInt(line);
  				if(model.getClearTime() >= 60 && model.getClearTime()%60 >= 10)
  					g.drawString("0" + (int)Math.floor((double)intline/60) + ":" + intline%60, x, row);
  				else if(intline >=60 && model.getClearTime()%60 < 10)
  					g.drawString("0" + (int)Math.floor((double)intline/60) + ":0" + intline%60, x, row);
  				else
  					g.drawString("00:" + intline, x, row);
  				row += 50;
  			}
  		}
  	}

    /**
     * 画面を黒色でクリア
     * @param g  描画用のグラフィックスオブジェクト
     */
    public void clear(Graphics g) {
        size = getSize();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size.width, size.height);
    }

    public void playBombSound() {
        sound.play();
    }
    //マップを返す
    public Map getMap() {
    	return map;
    }
  //プレイヤーの画像を返す
    public Image getPlayerImage() {
    	return playerimage;
    }
  //敵の画像を返す
    public Image getEnemyImage() {
    	return enemyimage;
    }
  //ブロックの画像を返す
	public Image getBlockImage() {
		return blockimage;
	}
	//宝箱の画像を返す
	public Image getTreasureImage() {
		return treasureimage;
	}
	//出口の画像を返す
	public Image getExitImage() {
		return exitimage;
	}
	//背景画像を返す
	public Image getBackgroundImage() {
		return backgroundimage;
	}
	//プレイヤーのライフの画像を返す
	public Image getHeartImage() {
		return heartimage;
	}
	//ボスが来た画面の画像を返す
	public Image getBossImage() {
		return bossimage;
	}

	public Model getModel() {
		return model;
	}
}
