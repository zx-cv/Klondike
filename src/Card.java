import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


/** represents a playing card that can draw itself. */
public class Card implements Drawable, Updateable{
  private int suite; //0,1,2,3 = Spades,Clubs,Diamonds,Hearts
  private int value;
  private int x = 50;
  private int y = 50;
  private boolean red;
  private boolean facingUp;
  static Image backside;
  static Image[][] cardImages = new Image[4][13];

  public Card(int s, int v, int x, int y, boolean up){
    loadCards();
    suite = s;
    value = v - 1;
    red = (suite == 2 || suite == 3) ? true:false;
    this.x = x;
    this.y = y;
    facingUp = up;
    
  }

  @Override
  public void update(ActionEvent a) {
    // TODO Auto-generated method stub
        
  }


  @Override
  public void draw(Graphics g) {
    if(facingUp)
      g.drawImage(backside,x,y,null);
    else
      g.drawImage(cardImages[suite][value],x,y,null);
    
  }

  public Card getCard(){
    return null;
  }

  public void flip(){
    facingUp = !facingUp;
  }

  public void loadCards(){
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
          }
          catch(IOException e){
            e.printStackTrace();
          }
        }
      }
    }
  }
}
