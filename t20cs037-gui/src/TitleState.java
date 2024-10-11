import java.awt.Color;
import java.awt.Graphics;

public class TitleState extends DoingNothingState {
	private Model model;
	
	public TitleState(Model model) {
		super();
		this.model = model;
	}

	@Override
	public State processKeyTyped(String typed) {
		// TODO 自動生成されたメソッド・スタブ
		if(typed.equals("ENTER")) {
			model.resetGame();
			return new PlayingState(model);
		}
		else if(typed.equals("s")) {
			return new ScoreState(model);
		}
		else if(typed.equals("q")) {
			return new BossState(model, this);
		}
		else if(typed.equals("r")) {
			return new RuleState(model);
		}
		return super.processKeyTyped(typed);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(Color.BLACK);
		g.drawString("脱出ゲーム", 500, 500);
		g.drawString("遊ぶ:ENTER", 500, 550);
		g.drawString("遊び方:R", 500, 600);
		g.drawString("ランキング:S", 500, 650);
	}
}
