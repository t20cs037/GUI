import java.awt.Graphics;

public class BossState extends DoingNothingState {
	private Model model;
	private State state;

	public BossState(Model model, State state) {
		super();
		this.model = model;
		this.state = state;
	}
	
	public State processKeyTyped(String typed) {
		if(typed.equals("q"))
			return state;
		return this;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(model.getView().getBossImage(), 0, 0, 1479, 889, model.getView());
	}
}
