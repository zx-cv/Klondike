import java.util.*;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;
/** A Pile is a collection of cards.  This needs to be
 * Drawable because it will be shown on the GUI. Put code
 * here that ALL Piles share.  The ways in which Piles are 
 * different belong in the subclasses.  The draw method should 
 * be implemented here.  Updateable may have empty implementation.
 * You WILL write subclasses of Pile
 */
public abstract class Pile implements Drawable, Updateable {
    
  protected List<Card> cards = new CopyOnWriteArrayList<Card>();
  
  public abstract void draw(Graphics g);
  public abstract boolean canAddCard(Card c);
  public abstract void remove(int ind);
  public abstract int subLen(int ind);
  public abstract Card get(int ind);
  public abstract List<Card> subPile(int ind);
  
}
