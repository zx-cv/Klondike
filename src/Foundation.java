import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
public class Foundation extends Pile{

  private int x;
  private int y = 50;
  private int suit;
  
  public Foundation(int x, int s){
    this.x = x;
    suit = s;
  }

  public void draw(Graphics g){
    for(Card c: cards){
      c.draw(g);
    }
  }

  public Card topCard(){
    return cards.get(cards.size()-1);
  }

  public void addCard(Card c){
    c.setLoc(x,y);
    cards.add(c);
  }
  
  public boolean canAddCard(Card c){
    Card topCard = null;
    if(!cards.isEmpty()){
      topCard = topCard();
    }
    return (suit == c.getSuit()) && ((cards.isEmpty() && c.getVal() == 0)  || c.getVal() == topCard.getVal() + 1);
  }

  public boolean clickedOnMe(int x, int y){
    return x > this.x && x < this.x + 71 && y > this.y && y < this.y + 96;
    
  }

  public boolean isEmpty(){
    return cards.isEmpty();
  }

  public void update(ActionEvent a){
    
  }

  
}