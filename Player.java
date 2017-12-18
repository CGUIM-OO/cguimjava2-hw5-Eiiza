import java.util.ArrayList;
public  class Player extends Person {
	
	private String name;  //	玩家姓名
	private int chips;  //	玩家有的籌碼
	private int bet;  //玩家此局下注的籌碼
	private ArrayList<Card> oneRoundCard;  //此牌局的卡
	public Player(String name, int chips){
		this.name = name;
		this.chips = chips;
	}
	public String getName(){
		return name;
	}
	public int makeBet(){
		bet=10;
		if(chips<=0){
			return 0;
		}
		return bet;
	}
	//public void setOneRoundCard(ArrayList<Card> cards){
	//	oneRoundCard=cards;
	//}
	public boolean hit_me(Table tbl){
		if(getTotalValue()>=17){
			return false;
		}else{
			return true;
		}
	}
	//public int getTotalValue(){
	//	int sum=0;
	//	for(int i=0;i<oneRoundCard.size();i++){
	//		if(oneRoundCard.get(i).getRank()==11||oneRoundCard.get(i).getRank()==12
	//				||oneRoundCard.get(i).getRank()==13){
	//			sum+=10;
	//		}	
	//		if(oneRoundCard.get(i).getRank()==1){
	//			if(sum<=10){
	//				sum+=11;
	//			}
	//			else{
	//				sum+=1;
	//			}
	//		}
	//		else{
	//			sum +=oneRoundCard.get(i).getRank();
	//		}
	//	}
	//	return sum;
	//}
	public int getCurrentChips(){
		return chips;
	}
	public void increaseChips (int diff){
		chips+=diff;
	}
	public void sayHello(){
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");

	}
	
	

}
