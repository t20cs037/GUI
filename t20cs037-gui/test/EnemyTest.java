import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyTest {

	@Test
	public void foundPlayerで視界以外の場所ではfalseを出力する() {
		Enemy enemy = new Enemy(0, 250);
		Enemy enemy2 = new Enemy(500, 250);
		Enemy enemy3 = new Enemy(300, 500);
		Enemy enemy4 = new Enemy(300, 0);
		enemy.setRight();
		enemy2.setLeft();
		enemy3.setUp();
		enemy4.setDown();
		Model model = new Model();
		
		assertEquals(false, enemy.foundPlayer(model));
		assertEquals(false, enemy2.foundPlayer(model));
		assertEquals(false, enemy3.foundPlayer(model));
		assertEquals(false, enemy4.foundPlayer(model));
	}
	
	@Test
	public void foundPlayerで視界の場所ではtrueを出力する() {
		Enemy enemy = new Enemy(200, 250);
		Enemy enemy2 = new Enemy(320, 250);
		Enemy enemy3 = new Enemy(300, 300);
		Enemy enemy4 = new Enemy(300, 200);
		enemy.setRight();
		enemy2.setLeft();
		enemy3.setUp();
		enemy4.setDown();
		Model model = new Model();
		
		assertEquals(true, enemy.foundPlayer(model));
		assertEquals(true, enemy2.foundPlayer(model));
		assertEquals(true, enemy3.foundPlayer(model));
		assertEquals(true, enemy4.foundPlayer(model));
	}

}
