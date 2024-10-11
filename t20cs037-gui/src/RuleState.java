import java.awt.Graphics;
import java.io.IOException;

public class RuleState extends DoingNothingState {
	private Model model;

	public RuleState(Model model) {
		super();
		this.model = model;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		try {
			model.getView().readFile(g, "rule.txt", 0, 0);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		g.drawString("戻る", 500, 1000);
	}
}
