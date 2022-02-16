import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
public class Stock extends Pile{

  private int x = 50;
  private int y = 50;

  public Stock(List<Card> c){
    cards = c;
    for(int i = 0; i < cards.size(); i++){
      cards.get(i).setLoc(x,y+(i/4)*2);
    }
  }
  
  public void draw(Graphics g){
    for(Card c: cards){
      c.draw(g);
    }
  }

  public boolean canAddCard(Card c){
    return false;
  }

  public Card topCard(){
    return cards.get(cards.size()-1);
  }

  public Card deal(){
    return cards.remove(cards.size()-1);
  }
  
  public void update(ActionEvent a){
    
  }

  public void addCard(Card c){
    cards.add(c);
  }


}