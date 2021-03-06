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
	

	Image spadeFoundation,clubFoundation, diamondFoundation,heartFoundation;
	public static final int OFFSET_X = 40, OFFSET_Y = 20;
	
	 
	private int numdraws=0;
  private boolean secondClick;
  private int selected;
  private Pile selectedPile;
  private Stock stock;
  private Stock waste;
  private Tableau[] tableaus = new Tableau[7];
  private Foundation[] foundations = new Foundation[4];
	
	
	
	public GameBoard() {
    deal();
		try {
			spadeFoundation = ImageIO.read(new File("images/cards/spadeFoundation.png"));
      clubFoundation = ImageIO.read(new File("images/cards/clubFoundation.png"));
			diamondFoundation = ImageIO.read(new File("images/cards/diamondFoundation.png"));
      heartFoundation = ImageIO.read(new File("images/cards/heartFoundation.png"));
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
    g.drawImage(spadeFoundation,350,50,null);
    g.drawImage(clubFoundation,450,50,null);
    g.drawImage(diamondFoundation,550,50,null);
    g.drawImage(heartFoundation,650,50,null);
    
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
    int x = me.getX();
    int y = me.getY();
    if(secondClick){
      if(stock.clickedOnMe(x,y)){
        stock.nextCard(waste);
      }
      else{
        for(int i = 0; i < foundations.length; i++){
          if(selectedPile.subLen(selected) == 1 && foundations[i].clickedOnMe(x,y) && foundations[i].canAddCard(selectedPile.get(selected))){
            foundations[i].addCard(selectedPile.get(selected));
            selectedPile.remove(selected);
            secondClick = !secondClick;
            uncover();
            return;
          }
        }
        for(int i = 0; i < tableaus.length; i++){
          if(tableaus[i].isEmpty()){
            if(tableaus[i].toEmpty(x,y) && selectedPile.get(selected).getVal() == 12){
                tableaus[i].movePile(selectedPile.subPile(selected));
                selectedPile.remove(selected);
            }
          }
          else if(tableaus[i].clickedOnPile(x,y) && tableaus[i].canAddCard(selectedPile.get(selected))){
            tableaus[i].movePile(selectedPile.subPile(selected));
            selectedPile.remove(selected);
          }
        }
        
      }
      secondClick = !secondClick;
    }
    else{
      if(stock.clickedOnMe(x,y)){
        stock.nextCard(waste);
        return;
      }
      if(!(waste.isEmpty()) && waste.clickedOnMe(x,y)){
        selectedPile = waste;
        selected = waste.size()-1;
        secondClick = !secondClick;
      }
      else{
        for(int i = 0; i < foundations.length; i++){
          if(!foundations[i].isEmpty() && foundations[i].clickedOnMe(x,y)){
            selected = foundations[i].size()-1;
            selectedPile = foundations[i];
            secondClick = !secondClick;
            return;
          }
        }
        for(int i = 0; i < tableaus.length; i++){
          int ind = tableaus[i].clickedOnMe(x,y);
          if(!tableaus[i].isEmpty() && !(ind == -1)){
            selected = ind;
            selectedPile = tableaus[i];
            secondClick = !secondClick;
            break;
          }
        }
      }
    }
    uncover();

	}

	@Override
	// this update will be called each time the timer in the KlondikeGame
	// goes off.  This will be convenient for animating.
	public void update(ActionEvent a) {
		
		
	}

  private void uncover(){
    for(Tableau t: tableaus){
      t.uncover();
    }
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
