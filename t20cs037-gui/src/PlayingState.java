import java.awt.Color;
import java.awt.Graphics;

public class PlayingState extends DoingNothingState{
	private Model model;
	private long rest;
	public PlayingState(Model m) { 
		super();
		model = m; 
	}
	
	@Override
	public State processTimeElapsed(int msec) {
		//敵がプレイヤーを見つけたかを判定してから敵を動かす。
		for(int i=0; i < model.getEnemies().size(); i++) {
			if(model.getEnemies().get(i).foundPlayer(model)) {
				model.getPlayer().decreaseHeart();
				if(model.getPlayer().getHeart() == 0)
					return new GameOverState(model);
				else {
					model.getPlayer().resetXY();
				}
			}
			model.moveEnemy(model.getEnemies().get(i));
			model.moveEnemy(model.getEnemies().get(i));
			model.moveEnemy(model.getEnemies().get(i));
			model.moveEnemy(model.getEnemies().get(i));
		}
		//残り時間を入手し０になったらゲームオーバーにする
		rest = model.setTime();
		if(rest == 0)
			return new GameOverState(model);
		
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		//プレイヤーの動作をコントローラーの入力によって命令する
		if(typed.equals("d"))
			model.getPlayer().moveRight(model);
		else if(typed.equals("a"))
			model.getPlayer().moveLeft(model);
		else if(typed.equals("w"))
			model.getPlayer().moveUp(model);
		else if(typed.equals("s"))
			model.getPlayer().moveDown(model);
		else if(typed.equals("q"))
			return new BossState(model, this);
		else if(typed.equals("ENTER")) {
			//すべての宝箱において、プレイヤーが宝箱を開けられる距離にいるかを判断する。trueの宝箱を発見したら宝箱を開けた状態にして中のお金を所持金に加算する
			for(int i=0; i<model.getTreasures().size(); i++) {
				if(model.getTreasures().get(i).existTreasure(model.getPlayer()) && !model.getTreasures().get(i).getOpend()) {
					model.getTreasures().get(i).completeOpened();
					model.getMoney().addMoney(model.getTreasures().get(i));
				}
			}
			//プレイヤーが出口から出られる距離にいてかつ所持金が必要数あるかを判断する。trueだったらゲームクリア画面に遷移する。
			if(model.getView().getMap().getExit().existExit(model.getPlayer()) && model.getMoney().escapeFromMap()) {
				model.setClearTime();
				model.getScore().appendScoreToFile("score.txt", model.getClearTime());
				model.getScore().sortAndWriteTop5Scores("score.txt");
				return new ResultState(model);
			}
		}
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		//背景描画
		g.drawImage(model.getView().getBackgroundImage(), 0, 0, 2000, 1200, model.getView()); 
		//プレイヤーの描画
		model.getPlayer().paint(g, model.getView());
		//複数の敵の描画
		 for(int i=0; i < model.getEnemies().size(); i++)
			 model.getEnemies().get(i).paint(g, model.getView());
		 //ブロックの描画
        model.getView().getMap().paint(g, model.getView());
    	//宝箱の描画、すでに開けられている宝箱は描画しない
        for(int i=0; i<model.getTreasures().size(); i++) {
        		if(!model.getTreasures().get(i).getOpend())
        			model.getTreasures().get(i).paint(g, model.getView());
         }
        //残り時間とプレイヤーのライフと所持金の描画
        g.setColor(Color.WHITE);
        g.fillRect(1400, 0, 600, 50);
        g.setColor(Color.BLACK);
        //残り時間の描画
        if(rest >= 60 && rest%60 >= 10)
			g.drawString("残り0" + (int)Math.floor((double)rest/60) + ":" + rest%60, 1410, 40);
		else if(rest >=60 && rest%60 < 10)
			g.drawString("残り0" + (int)Math.floor((double)rest/60) + ":0" + rest%60, 1410, 40);
		else
			g.drawString("残り00:" + rest, 1410, 40);
        //プレイヤーの所持金の描画
        g.drawString("所持金:" + model.getMoney().getMoney() + "円", 1580, 40);
        //プレイヤーのライフの描画
        if(model.getPlayer().getHeart() >= 1)
        	g.drawImage(model.getView().getHeartImage(), 1820, 0, 50, 50, model.getView());
        if(model.getPlayer().getHeart() >= 2)
        	g.drawImage(model.getView().getHeartImage(), 1880, 0, 50, 50, model.getView());
        if(model.getPlayer().getHeart() >= 3)
        	g.drawImage(model.getView().getHeartImage(), 1940, 0, 50, 50, model.getView());
	}
	
}
