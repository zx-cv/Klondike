import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class Tableau extends Pile{


  public Tableau(List<Card> c, int x){
    Collection<Card> cs = c;
    cards = new CopyOnWriteArrayList<Card>(cs);
    for(int i = 0; i < cards.size(); i++){
      cards.get(i).setLoc(x,200 + i * 30);
    }
  }

  public void draw(Graphics g){
    synchronized(cards){
      for(Card c: cards){
        c.draw(g);
      }
    }
  }

  public boolean canAddCard(Card c){
    return false;
  }

  public Card topCard(){
    return cards.get(cards.size()-1);
  }

  public void update(ActionEvent a){
    
  }
}