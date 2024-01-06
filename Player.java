
public class Player {

	private String playerName;
	//passing
	private double passing_yards = 0;
	private double completions = 0;
	private double attempts = 0;
	private double passing_touchdowns = 0;
	private double interceptions = 0;
	//rushing
	private double carries = 0;
	private double rushing_yards = 0;
	private double rushing_touchdowns = 0;
	//receiving
	private double catches = 0;
	private double receiving_yards = 0;
	private double receiving_touchdowns = 0;
	
	public Player (String name) {
		playerName = name;
	}
	
	public String toString () {
		return playerName;
	}
	
	public String getName () {
		return playerName;
	}
	
	//passing 
	public void passingYardsPlus () {
		passing_yards++;
		System.out.println(playerName + " Passing Yards: " + passing_yards);
	}
	
	public void passingYardsMinus () {
		passing_yards--;
		System.out.println(playerName + " Passing Yards: " + passing_yards);
	}
	
	public void completionsPlus () {
		completions++;
		System.out.println(playerName + " Completions: " + completions);
	}
	
	public void completionsMinus () {
		completions--;
		System.out.println(playerName + " Completions: " + completions);
	}
	
	public void attemptsPlus () {
		attempts++;
		System.out.println(playerName + " Attempts: " + attempts);
	}
	
	public void attemptsMinus () {
		attempts--;
		System.out.println(playerName + " Attempts: " + attempts);
	}
	
	public void passingTouchdownPlus () {
		passing_touchdowns++;
		System.out.println(playerName + " Passing Touchdowns: " + passing_touchdowns);
	}
	
	public void passingTouchdownMinus () {
		passing_touchdowns--;
		System.out.println(playerName + " Passing Touchdowns: " + passing_touchdowns);
	}
	
	public void interceptionsPlus () {
		interceptions++;
		System.out.println(playerName + " Interceptions: " + interceptions);
	}
	
	public void interceptionsMinus () {
		interceptions--;
		System.out.println(playerName + " Interceptions: " + interceptions);
	}
	//passing
	
	//rushing
	public void carriesPlus () {
		carries++;
		System.out.println(playerName + " Carries: " + carries);
	}
	
	public void carriesMinus () {
		carries--;
		System.out.println(playerName + " Carries: " + carries);
	}
	public void rushingYardsPlus () {
		rushing_yards++;
		System.out.println(playerName + " Rushing Yards: " + rushing_yards);
	}
	
	public void rushingYardsMinus () {
		rushing_yards--;
		System.out.println(playerName + " Rushing: " + rushing_yards);
	}
	
	public void rushingTouchdownsPlus () {
		rushing_touchdowns++;
		System.out.println(playerName + " Rushing Touchdowns: " + rushing_touchdowns);
	}
	
	public void rushingTouchdownsMinus () {
		rushing_touchdowns--;
		System.out.println(playerName +  " Rushing Touchdowns: " + rushing_touchdowns);
	}
	//rushing
	
	//receiving
	public void catchesPlus () {
		catches++;
		System.out.println(playerName + " Catches: " + catches);
	}
	
	public void catchesMinus () {
		catches--;
		System.out.println(playerName + " Catches: " + catches);
	}

	public void receivingYardsPlus () {
		receiving_yards++; 
		System.out.println(playerName + " Receiving Yards: " + receiving_yards);
	}
	
	public void receivingYardsMinus () {
		receiving_yards--;
		System.out.println(playerName + " Receiving Yards: " + receiving_yards);
	}
	
	public void receivingTouchdownsPlus () {
		receiving_touchdowns++;
		System.out.println(playerName + " Receiving Touchdowns: " + receiving_touchdowns);
	}
	
	public void receivingTouchdownsMinus () {
		receiving_touchdowns--;
		System.out.println(playerName + " Receiving Touchdowns: " + receiving_touchdowns);
	}
	//receiving
}
