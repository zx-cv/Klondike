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

  public boolean clickedOnMe(int x, int y){
    if(cards.isEmpty()){
      if(x > this.x && x < this.x + 71 && y > this.y && y < this.y + 96){
        return true;
      }
      return false;
    }
    return topCard().clickedOnMe(x, y, 71, 96);
  }

  public boolean isEmpty(){
    return cards.isEmpty();
  }

  public void removeTopCard(){
    cards.remove(cards.size()-1);
  }

  public void remove(int ind){
    cards.subList(ind,cards.size()).clear();
  }

  public int subLen(int ind){
    return cards.size() - ind;
  }

  public Card get(int ind){
    return cards.get(ind);
  }

  public List<Card> subPile(int ind){
    return cards.subList(ind,cards.size());
  }

  public int size(){
    return cards.size();
  }

}