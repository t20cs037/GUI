import java.awt.Graphics;
import java.util.LinkedList;

public class Map {
	private LinkedList<Block> blocks;
	private Exit exit;
	
	public Map() {
		super();
		blocks = new LinkedList<Block>();
		exit = new Exit(300, 500);
	}
	
	//マップに表示するブロックを生成する。ここにブロックオブジェクトを追加していけばマップに反映される
	public void createMap(){
		LinkedList<Block> newblocks = new LinkedList<Block>();
		newblocks.add(new Block(0, 0));
		newblocks.add(new Block(0, 50));
		newblocks.add(new Block(0, 100));
		newblocks.add(new Block(50, 100));
		newblocks.add(new Block(150, 150));
		newblocks.add(new Block(150, 200));
		newblocks.add(new Block(150, 250));
		newblocks.add(new Block(300, 60));
		newblocks.add(new Block(350, 60));
		newblocks.add(new Block(400, 60));
		newblocks.add(new Block(450, 60));
		newblocks.add(new Block(250, 590));
		newblocks.add(new Block(300, 590));
		newblocks.add(new Block(350, 590));
		newblocks.add(new Block(500, 150));
		newblocks.add(new Block(550, 150));
		newblocks.add(new Block(500, 200));
		newblocks.add(new Block(550, 200));
		newblocks.add(new Block(900, 500));
		newblocks.add(new Block(850, 500));
		newblocks.add(new Block(800, 500));
		newblocks.add(new Block(900, 450));
		newblocks.add(new Block(900, 400));
		newblocks.add(new Block(900, 350));
		
		
		blocks = newblocks;
	}
	
	//すべてのブロックと、出口を描画する
	public void paint(Graphics g, View view) {
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).paint(g, view);
		}
		exit.paint(g, view);
	}

	public Exit getExit() {
		return exit;
	}

	public LinkedList<Block> getBlocks() {
		return blocks;
	}
}
