import java.awt.Color;
import java.awt.Graphics;

public class GameOverState extends DoingNothingState{
	private Model model;
	
	public GameOverState(Model model) {
		super();
		this.model = model;
	}

	@Override
	public State processKeyTyped(String typed) {
		//エンターを押したらタイトル画面に遷移する
		if(typed.equals("ENTER")) {
			return new TitleState(model);
		}
		else if(typed.equals("p")) {
			model.resetGame();
			return new PlayingState(model);
		}
		else if(typed.equals("q"))
			return new BossState(model, this);
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		//画面にゲームオーバーと表示する
		g.setColor(Color.BLACK);
		g.drawString("ゲームオーバー", 500, 500);
		g.drawString("もう一度遊ぶ:P", 500, 550);
		g.drawString("タイトルに戻る:ENTER", 500, 600);
	}

}
