import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class Tableau extends Pile{

  private int x;
  private int y = 200;


  public Tableau(List<Card> c, int x){
    this.x = x;
    Collection<Card> cs = c;
    cards = new CopyOnWriteArrayList<Card>(cs);
    for(int i = 0; i < cards.size(); i++){
      cards.get(i).setLoc(x,y + i * 30);
    }
  }

  public void draw(Graphics g){
    for(Card c: cards){
      c.draw(g);
    }
  }

  public boolean canAddCard(Card c){
    Card topCard = topCard();
    return topCard.facingUp() && topCard.red() ^ c.red() && c.getVal() == topCard.getVal() - 1 && c.facingUp();
  }

  public Card topCard(){
    return cards.get(cards.size()-1);
  }

  public void update(ActionEvent a){
    
  }

  /*public List<Card> clickedOnMe(int x, int y){
    for(int i = 0; i < cards.size(); i++){
      if(i == cards.size() - 1){
        if(cards.get(i).clickedOnMe(x,y,71,96)){
          return cards.subList(i,cards.size());
        }
      }
      else if(cards.get(i).clickedOnMe(x,y,71,30)){
        return cards.subList(i,cards.size());
      }
    }
    return null;
  }*/

  public int clickedOnMe(int x, int y){
    for(int i = 0; i < cards.size(); i++){
      if(i == cards.size() - 1){
        if(cards.get(i).clickedOnMe(x,y,71,96)){
          return i;
        }
      }
      else if(cards.get(i).clickedOnMe(x,y,71,30)){
        return i;
      }
    }
    return -1;
  }

  public boolean clickedOnPile(int x, int y){
    int len = (cards.size()*30) + 66;
    return (x > this.x && x < this.x + 71 && y > this.y && y < this.y + len);
  }

  public boolean toEmpty(int x, int y){
    return (x > this.x && x < this.x + 71 && y > this.y && y < this.y + 96);
  }

  public void removeCard(Card c){
    cards.remove(c);
  }

  public void addCard(Card c){
    c.setLoc(x, y + cards.size() * 30);
    cards.add(c);
  }

  public void movePile(List<Card> other){
    for(Card c: other){
      addCard(c);
    }
    other.clear();
  }

  public boolean isEmpty(){
    return cards.isEmpty();
  }

  public void uncover(){
    if(cards.isEmpty()){
      return;
    }
    Card topCard = topCard();
    if(!topCard.facingUp()){
      topCard.flip();
    }
  }

  public int size(){
    return cards.size();
  }

  public void remove(int ind){
    cards.subList(ind, cards.size()).clear();
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
    
}