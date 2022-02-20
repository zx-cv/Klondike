import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

import javax.imageio.*;


/** represents a playing card that can draw itself. */
public class Card implements Drawable, Updateable{
  private int suit; //0,1,2,3 = Spades,Clubs,Diamonds,Hearts
  private int value;
  private int x = 50;
  private int y = 50;
  private int width = 71;
  private int height = 96;
  private boolean red;
  private boolean facingUp;
  static Image backside;
  static Image[][] cardImages = new Image[4][13];
  static List<Card> deck = new ArrayList<Card>();

  public Card(int s, int v, int x, int y, boolean up){
    loadCards();
    suit = s;
    value = v - 1;
    red = (suit == 2 || suit == 3) ? true:false;
    this.x = x;
    this.y = y;
    facingUp = up;
  }

  @Override
  public void update(ActionEvent a) {
    // TODO Auto-generated method stub
        
  }

  public int getVal(){
    return value;
  }

  public boolean red(){
    return red;
  }


  @Override
  public void draw(Graphics g) {
    if(!facingUp)
      g.drawImage(backside,x,y,null);
    else
      g.drawImage(cardImages[suit][value],x,y,null);
    
  }

  public Card getCard(){
    return null;
  }

  public void setLoc(int x, int y){
    this.x = x;
    this.y = y;
  }

  public void flip(){
    facingUp = !facingUp;
  }

  public static List<Card> getSubDeck(int start, int end){
    return deck.subList(start,end);
  }

  public boolean clickedOnMe(int x, int y, int w, int h){
    return (x > this.x && x < this.x + w) && (y > this.y && y < this.y + h);
  }

  public static void loadCards(){
    if(cardImages[0][0] == null){
      try{
        backside = ImageIO.read(new File("images/cards/b1fv.png"));
      }
      catch(IOException e){
        e.printStackTrace();
      }
      String[] vals = new String[]{"1","2","3","4","5","6","7","8","9","10","j","q","k"};
      String[] s = new String[]{"s","c","d","h"};
      for(int i = 0; i < 4; i++){
        for(int j = 0; j < 13; j++){
          try{
            cardImages[i][j] = ImageIO.read(new File("images/cards/" + s[i] + vals[j] + ".png"));
            deck.add(new Card(i,j+1,100,100,false));
          }
          catch(IOException e){
            e.printStackTrace();
          }
        }
      }
      Collections.shuffle(deck);
    }
  }
}
