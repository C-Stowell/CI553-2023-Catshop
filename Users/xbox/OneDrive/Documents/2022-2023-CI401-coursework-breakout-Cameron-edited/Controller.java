// The breakout controller converts key presses from the user (received by the View object)
// into commands for the game (in the Model object)

// we need to use on JavaFX class
import javafx.scene.input.KeyEvent;

public class Controller
{
  // instance variables for the two other components of the MVC model  
  public Model model;
  public View  view;
  public int B              = 6;      // Border round the edge of the panel
  public int MAX_DIST_R     = 90;    // Max distance on the right 
  public int MAX_DIST_L     = 0;   // Max distance on the left 
  public int x         = 60;      // Bat position
  
  // we don't really need a constructor method, but include one to print a 
  // debugging message if required
  public Controller()
  {
    Debug.trace("Controller::<constructor>");
  }
  
  // This is how the View talks to the Controller
  // AND how the Controller talks to the Model
  // This method is called by the View to respond to key presses in the GUI
  // The controller's job is to decide what to do. In this case it converts
  // the keypresses into commands which are run in the model
  public void userKeyInteraction(KeyEvent event )
  {
    // print a debugging message to show a key has been pressed
    Debug.trace("Controller::userKeyInteraction: keyCode = " + event.getCode() );
    
    // KeyEvent objects have a method getCode which tells us which key has been pressed.
    // KeyEvent also provides variables LEFT, RIGHT, F, N, S (etc) which are the codes
    // for individual keys. So you can add keys here just by using ther name (which you
    // can find out by googling 'JavaFX KeyCode')
    
    
    
    
    
    switch ( event.getCode() )             
    {
      case LEFT:                     // Left Arrow
          
        if(x > MAX_DIST_L){
          model.moveBat( -1 );        // move bat left
          x = x-1;                    // reduces x when LEFT is pressed
        }
        
        break;
      case RIGHT:                    // Right arrow
          
        if(x < MAX_DIST_R){
          model.moveBat( +1 );        // move bat right
          x = x+1;                    // adds to x when RIGHT is pressed
        }
        
        break;
      case F :
        // Very fast ball movement
        model.setFast(true);
        break;
      case N :
        // Normal speed ball movement
        model.setFast(false);
        break;
      case S :
        // Stop the game
        model.setGameState("finished");
        break;
      
          
           
          
     
      
    }
  }
  
  
}
