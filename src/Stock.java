import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
public class Stock extends Pile{

  private int x;
  private int y = 50;

  public Stock(List<Card> c, int x){
    this.x = x;
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

  public void nextCard(Stock other){
    if(cards.isEmpty()){
      resetPile(other);
      return;
    }
    Card c = cards.remove(cards.size()-1);
    c.flip();
    other.addCard(c);
  }

  
  public void update(ActionEvent a){
    
  }

  public void resetPile(Stock s){
    for(int i = s.cards.size()-1; i >= 0; i--){
      s.cards.get(i).flip();
      addCard(s.cards.get(i));
    }
    s.cards.clear();
  }

  public void addCard(Card c){
    c.setLoc(x,y+(cards.size()/4)*2);
    cards.add(c);
  }


}