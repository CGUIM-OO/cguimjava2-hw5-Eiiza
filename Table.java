import java.util.ArrayList;

public class Table {

	public static final int MAXPLAYER = 4;
	
	private Deck deck; //�s��Ҧ���
	private Player[] players; //�Ҧ����a
	private Dealer dealer; //���a
	private int[]pos_betArray; //�s��C�Ӫ��a�b�@���U���`
	private int nDecks;
	
	public Table(int nDecks){
		//this.nDecks=nDecks;
		deck = new Deck(nDecks); //�NDeck class����ơA�æs�J�W�z�s�W��Deck�ܼ�
		players = new Player[MAXPLAYER]; //�s�W���O��Player[]���ܼơA�ŧi�@�Ӫ��׬OMAXPLAYER ��Player array
		pos_betArray=new int[MAXPLAYER];
	}
	public void set_player(int pos, Player p){
			players[pos]=p;
	}
	public Player[] get_player(){
			return players;
	}
	public void set_dealer(Dealer d){
		dealer = d;
	}
	public Card get_face_up_card_of_dealer(){
		return dealer.getOneRoundCard().get(1);
	}
	
	private void ask_each_player_about_bets(){
		for(int i =0;i<4;i++){
			pos_betArray[i]=players[i].makeBet();
			players[i].sayHello();
		}
	}
	
	private void distribute_cards_to_dealer_and_players(){
		for(int i =0;i<4;i++){
			ArrayList <Card> temp=new ArrayList <Card>();
			temp.add(deck.getOneCard(true));
			temp.add(deck.getOneCard(true));
			players[i].setOneRoundCard(temp);
			
		}
		ArrayList <Card> tempDealer=new ArrayList <Card>();
		tempDealer.add(deck.getOneCard(false));
		tempDealer.add(deck.getOneCard(true));
		dealer.setOneRoundCard(tempDealer);
		System.out.print("Dealer's face up card is ");
		tempDealer.get(1).printCard();
	}
	private void ask_each_player_about_hits(){
		boolean hit=false;
		//ArrayList <Card> temp1=new ArrayList <Card>();
		for(int i =0;i<4;i++){
			do{
				hit=players[i].hit_me(this); //this
				if(hit){
					System.out.print("Hit! ");
					//temp1.add(deck.getOneCard(true));
					players[i].getOneRoundCard().add(deck.getOneCard(true));
					//players[i].setOneRoundCard(temp1);
					System.out.println(players[i].getName()+"'s Cards now:");
					for(Card c : players[i].getOneRoundCard()){
						c.printCard();
					}
					if(players[i].getTotalValue()>21){
						hit=false;
					}
				}
			}while(hit);
			
			System.out.println(players[i].getName()+", Pass hit!");
			System.out.println(players[i].getName()+", Final Card:");
			for(Card c : players[i].getOneRoundCard()){
				c.printCard();
			}
		}
		
	}
	private void ask_dealer_about_hits(){
		boolean hit=false;
		do{
			hit=dealer.hit_me(this); //this
			if(hit){
				System.out.print("Hit! ");
				dealer.getOneRoundCard().add(deck.getOneCard(true));
				System.out.println("dealer's Cards now:");
				for(Card c : dealer.getOneRoundCard()){
					c.printCard();
				}
				if(dealer.getTotalValue()>21){
					hit=false;
				}
			}
		}while(hit);
		System.out.println("Dealer's  hit is over!");
		System.out.println("dealer, Final Card:");
		for(Card c : dealer.getOneRoundCard()){
			c.printCard();
		}
	}
	private void calculate_chips(){
		System.out.print("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");
		dealer.printAllCard();
		for(int i =0;i<4;i++){
			System.out.println(players[i].getName()+" card value is "+players[i].getTotalValue());
			if(dealer.getTotalValue()==players[i].getTotalValue()){
				System.out.println(players[i].getName()+",chips have no change! The Chips now is:"
						+players[i].getCurrentChips());
			}
			if(dealer.getTotalValue()>21&&players[i].getTotalValue()>21){
				System.out.println(players[i].getName()+",chips have no change! The Chips now is:"
						+players[i].getCurrentChips());
			}
			if(dealer.getTotalValue()<=21&&players[i].getTotalValue()>21){
				players[i].increaseChips (-pos_betArray[i]);
				System.out.println(players[i].getName()+", Loss "+players[i].makeBet()+
						" Chips, the Chips now is: "+players[i].getCurrentChips());
			}
			if(dealer.getTotalValue()<=21&&dealer.getTotalValue()>players[i].getTotalValue()){
				players[i].increaseChips (-pos_betArray[i]);
				System.out.println(players[i].getName()+", Loss "+players[i].makeBet()+
						" Chips, the Chips now is: "+players[i].getCurrentChips());
			}
			if(players[i].getTotalValue()<=21&&dealer.getTotalValue()>21){
				players[i].increaseChips (pos_betArray[i]);
				System.out.println(players[i].getName()+",Get "+players[i].makeBet()+
						" Chips, the Chips now is: "+players[i].getCurrentChips());
			}
			if(players[i].getTotalValue()<=21&&dealer.getTotalValue()<players[i].getTotalValue()){
				players[i].increaseChips (pos_betArray[i]);
				System.out.println(players[i].getName()+",Get "+players[i].makeBet()+
						" Chips, the Chips now is: "+players[i].getCurrentChips());
			}
		}
	}
	
	public int[] get_palyers_bet(){
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();  //�ݤj�a���n�U�h�֪`
		distribute_cards_to_dealer_and_players();  //�o�P
		ask_each_player_about_hits();  //�ݪ��a�n���n�P
		ask_dealer_about_hits();  //�ݲ��a�n���n�P
		calculate_chips();  //��ֿ��Ĺ
	}

}
