import static org.junit.Assert.*;
import java.util.LinkedList;

import org.junit.Test;

public class ObstacleTest {
	private Player player = new Player(100, 100);
	
	@Test
	public void existBlockでプレイヤーがと隣り合っている場合trueを返す() {
		LinkedList<Block> blocks = new LinkedList<Block>();
		LinkedList<Treasure> treasures = new LinkedList<Treasure>();
		blocks.add(new Block(150, 100));
		blocks.add(new Block(50, 100));
		treasures.add(new Treasure(100, 50, 0));
		treasures.add(new Treasure(100, 150, 0));
		Obstacle obstacle = new Obstacle(blocks, treasures);
		
		assertEquals(true, obstacle.existObstacles(player, "right"));
		assertEquals(true, obstacle.existObstacles(player, "left"));
		assertEquals(true, obstacle.existObstacles(player, "up"));
		assertEquals(true, obstacle.existObstacles(player, "down"));
		
	}
	
	@Test
	public void existBlockでブロックと隣り合ってはいないが座標が隣り合っている場合はfalseを返す() {
		LinkedList<Block> blocks = new LinkedList<Block>();
		LinkedList<Treasure> treasures = new LinkedList<Treasure>();
		blocks.add(new Block(150, 0));
		blocks.add(new Block(50, 0));
		treasures.add(new Treasure(0, 50, 0));
		treasures.add(new Treasure(0, 150, 0));
		Obstacle obstacle = new Obstacle(blocks, treasures);
		
		assertEquals(false, obstacle.existObstacles(player, "right"));
		assertEquals(false, obstacle.existObstacles(player, "left"));
		assertEquals(false, obstacle.existObstacles(player, "up"));
		assertEquals(false, obstacle.existObstacles(player, "down"));
	}

}
