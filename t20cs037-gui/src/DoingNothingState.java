import java.awt.Graphics;

//何もしない状態を親クラスとして持ち、それぞれの状態に提供する
public class DoingNothingState implements State {

	@Override
	public State processTimeElapsed(int msec) {
		return this;
	}

	@Override
	public State processKeyTyped(String typed) {
		return this;
	}

	@Override
	public State processMousePressed(int x, int y) {
		return this;
	}

	@Override
	public void paintComponent(Graphics g) {
		//何も描画しない
	}

}
