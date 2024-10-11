
public class Money {
	private int money;
	private int need; 

	public Money(int need) {
		super();
		money = 0;
		this.need = need;
	}
	
	public int getMoney() {
		return money;
	}
	//宝箱から得たお金を加算する
	public void addMoney(Treasure t) {
		money += t.getMoney();
	}
	//ゲームクリアを満たす所持金があるかを返す
	public boolean escapeFromMap() {
		if(money >= need )
			return true;
		else
			return false;
	}
	//所持金を初期化する
	public void resetMoney() {
		money = 0;
	}

	public int getNeed() {
		return need;
	}
}
