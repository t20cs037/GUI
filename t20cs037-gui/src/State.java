import java.awt.Graphics;

//各画面の状態をクラス化し管理するためのインタフェース
public interface State{
    	public State processTimeElapsed(int msec);
    	public State processKeyTyped(String typed);
    	public State processMousePressed(int x, int y);
    	
    	public void paintComponent(Graphics g);
    }