import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;

public class ScoreState extends DoingNothingState {
	private Model model;

	public ScoreState(Model model) {
		super();
		this.model = model;
	}
	
	//エンターを押したらタイトル画面に遷移する
	@Override
	public State processKeyTyped(String typed) {
		if(typed.equals("ENTER")) {
			return new TitleState(model);
		}
		if(typed.equals("q"))
			return new BossState(model, this);
		return this;
	}
	
	//画面にゲームクリアと表示する
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("ランキング", 500, 500);
		try {
			model.getView().readFile(g, "score.txt", 550, 550);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		g.drawString("1. ", 500, 550);
		g.drawString("2. ", 500, 600);
		g.drawString("3. ", 500, 650);
		g.drawString("4. ", 500, 700);
		g.drawString("5. ", 500, 750);
		g.drawString("戻る:ENTER", 500, 800);
		 
	}
	
	
}
