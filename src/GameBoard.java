import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class GameBoard implements Drawable, Updateable {
	

	Image testImage, backImage;
	public static final int OFFSET_X = 40, OFFSET_Y = 20;
	
	 
	private int numdraws=0;
  private boolean secondClick;
  private List<Card> selected;
  private Stock stock;
  private Stock waste;
  private Tableau[] tableaus = new Tableau[7];
  private Foundation[] foundations = new Foundation[4];
	
	
	
	public GameBoard() {
    deal();
		try {
			testImage = ImageIO.read(new File("images/cards/dj.png"));
			backImage = ImageIO.read(new File("images/cards/b1fv.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** @param g Graphics context onto which all Objects in the game
	 * draw themselves.  This should NOT change the Objects
	 */
	public void draw(Graphics g) {
		numdraws++;
		g.setColor(new Color(40, 155, 70));
		g.fillRect(0, 0, 3000, 2000);

    stock.draw(g);
    waste.draw(g);
    for(Tableau t: tableaus){
      t.draw(g);
    }
    for(Foundation f: foundations){
      f.draw(g);
    }

    System.out.println("redrew");



		
    

		// this is just to test drawing a card
		//g.drawImage(testImage, 30, 80, null);
		//g.drawImage(backImage, 100, 80, null);
		//g.drawImage(backImage, 105, 100, null);
	}


	/**
	 * This method is called by the game when a click has been made 
	 * on the panel.  Must determine if the click makes sense (is it 
	 * a valid location, If it is the first click, then note it and
	 * the next click will attempt a move (maybe).
	 * @param me
	 */
	public void justClicked(MouseEvent me) {
		Point p = me.getPoint();
		System.out.println("You just clicked "+p);
    
    if(secondClick){
      if(stock.clickedOnMe(me.getX(),me.getY())){
        stock.nextCard(waste);
      }
      else{
        for(int i = 0; i < foundations.length; i++){
          if(selected.size() == 1 && foundations[i].clickedOnMe(me.getX(),me.getY()) && foundations[i].canAddCard(selected.get(0))){
            foundations[i].addCard(selected.get(0));
            System.out.println("asdfasdf");
          }
        }
        for(int i = 0; i < tableaus.length; i++){
          List<Card> subPile = tableaus[i].clickedOnMe(me.getX(),me.getY());
          if(!(subPile == null) && tableaus[i].canAddCard(selected.get(0))){
            tableaus[i].movePile(selected);
          }
          else{
            if(tableaus[i].isEmpty()){
              if(tableaus[i].toEmpty(me.getX(),me.getY())){
                tableaus[i].movePile(selected);
              }
            }
          }
        }
      }
      secondClick = !secondClick;
    }
    else{
      if(stock.clickedOnMe(me.getX(),me.getY())){
        stock.nextCard(waste);
        return;
      }
      if(!(waste.isEmpty()) && waste.clickedOnMe(me.getX(),me.getY())){
        List<Card> subPile = new ArrayList<>();
        subPile.add(waste.topCard());
        selected = subPile;
      }
      else{
        for(int i = 0; i < foundations.length; i++){
          if(foundations[i].clickedOnMe(me.getX(),me.getY())){
            List<Card> subPile = new ArrayList<>();
            subPile.add(foundations[i].topCard());
          }
        }
        for(int i = 0; i < tableaus.length; i++){
          List<Card> subPile = tableaus[i].clickedOnMe(me.getX(),me.getY());
          if(!(subPile == null)){
            selected = subPile;
          }
        }
      }
      secondClick = !secondClick;
    }
    for(Tableau t: tableaus){
      t.uncover();
    }

	}

	@Override
	// this update will be called each time the timer in the KlondikeGame
	// goes off.  This will be convenient for animating.
	public void update(ActionEvent a) {
		
		
	}

  private void deal(){
    Card.loadCards();
    stock = new Stock(Card.getSubDeck(0,24), 50);
    waste = new Stock(new ArrayList<Card>(), 150);
    int asdf = 24;
    for(int i = 0; i < 7; i++){
      tableaus[i] = new Tableau(Card.getSubDeck(asdf,asdf+i+1),50 + (100*i));
      tableaus[i].topCard().flip();
      asdf += i + 1;
    }
    for(int i = 0; i < 4; i++){
      foundations[i] = new Foundation(350 + 100 * i, i);
    }
    
  }

}
