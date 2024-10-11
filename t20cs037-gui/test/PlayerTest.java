import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void moveRightでxがWIDTHー1より大きい範囲には加算されない() {
		Model model = new Model();
		Player p = new Player(model.getView().getWidth(), 0);
		p.moveRight(model);
		assertEquals(model.getView().getWidth(), p.getX());
	}
	
	@Test
	public void moveLeftでxが0より小さい範囲に減算されない() {
		Model model = new Model();
		Player p = new Player(0, 0);
		p.moveLeft(model);
		assertEquals(0, p.getX());
	}
	
	@Test
	public void moveDownでyがHEIGHTー1より大きい範囲には加算されない() {
		Model model = new Model();
		Player p = new Player(0, model.getView().getHeight());
		p.moveDown(model);
		assertEquals(model.getView().getHeight(), p.getY());
	}
	
	@Test
	public void moveUpでxがWIDTHより大きい範囲には加算されない() {
		Model model = new Model();
		Player p = new Player(0, 0);
		p.moveUp(model);
		assertEquals(0, p.getY());
	}

}
