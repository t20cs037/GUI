import java.awt.Color;
import java.awt.Graphics;

public class ResultState extends DoingNothingState {
	private Model model;
	public ResultState(Model m) { 
		super();
		model = m; 
	}

	//エンターを押したらタイトル画面に遷移する
	@Override
	public State processKeyTyped(String typed) {
		switch(typed) {
		case "q":
			return new BossState(model, this);
		case "ENTER":
			return new TitleState(model);
		case "p":
			model.resetGame();
			return new PlayingState(model);
		}
		return this;
	}
	
	//画面にゲームクリアと表示する
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("ゲームクリア", 500, 500);
		 if(model.getClearTime() >= 60 && model.getClearTime()%60 >= 10)
				g.drawString("クリアタイム 0" + (int)Math.floor((double)model.getClearTime()/60) + ":" + model.getClearTime()%60, 500, 550);
			else if(model.getClearTime() >=60 && model.getClearTime()%60 < 10)
				g.drawString("クリアタイム 0" + (int)Math.floor((double)model.getClearTime()/60) + ":0" + model.getClearTime()%60, 500, 550);
			else
				g.drawString("クリアタイム 00:" + model.getClearTime(), 500, 550);
		 g.drawString("もう一度遊ぶ:P", 500, 600);
			g.drawString("タイトルに戻る:ENTER", 500, 650);
	}

}
