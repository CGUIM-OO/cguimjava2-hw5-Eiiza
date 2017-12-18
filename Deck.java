

import java.util.ArrayList;
import java.util.Random;



public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCards;
	private ArrayList<Card> openCard;
	public int nUsed;
	
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		usedCards=new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		for(int i=0;i<nDeck;i++){
			for(Card.Suit s1:Card.Suit.values()){
				for(int r1 =1;r1<14;r1++){
					Card card=new Card(s1,r1);
					cards.add(card);
				}
			}
		}
		shuffle();
	}	
	//TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		int i=0;
		while(i<cards.size()){
			cards.get(i).printCard();
			i++;
		}
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public void shuffle(){
		Random rnd= new Random();
		cards.addAll(usedCards);
		nUsed = 0;
		usedCards = new ArrayList<Card>();
		openCard = new ArrayList<Card>();
		for(int j=0;j<cards.size();j++){	//亂數要設上限值
			int i=rnd.nextInt(cards.size());
			Card n=cards.get(j);
			Card temp =cards.get (i);
			cards.set(i,n);
			cards.set(j, temp);
		}	//按順序選牌之後，放到亂數出來的隨機位置
		
		
	}
	public Card getOneCard(boolean isOpened){
		Card temp = null;
		
		if (cards.size()==0){
			
			shuffle();
			return getOneCard(isOpened);
		}
		else{
			temp = cards.get(0);
			if(isOpened ==true){
				openCard.add(temp);
			}
			usedCards.add(temp);
			nUsed++;
			cards.remove(0);
		}
		return temp;
	}
	
	public ArrayList getOpenedCard(){
			return  openCard;
	}
}
