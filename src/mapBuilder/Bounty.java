package mapBuilder;

public class Bounty extends BasicMapBlock {
	private int timeToLive;
	
	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public Bounty(int x, int y, String string,int ttl) {
		super(x, y, string);
		timeToLive = ttl;
	}
}
