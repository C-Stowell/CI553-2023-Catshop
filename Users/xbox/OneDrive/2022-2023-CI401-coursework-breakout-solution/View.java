// The View class creates and manages the GUI for the application.
// It doesn't know anything about the game itself, it just displays
// the current state of the Model, and handles user input

// We import lots of JavaFX libraries (we may not use them all, but it
// saves us having to thinkabout them if we add new code)
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class View implements EventHandler<KeyEvent>
{ 
    // variables for components of the user interface
    public int width;       // width of window
    public int height;      // height of window

    // user interface objects
    public Pane pane;       // basic layout pane
    public Canvas canvas;   // canvas to draw game on
    public Label infoText;  // info at top of screen
    public Label fModeText;  // Fast mode
    public Label nModeText;  // Normal mode
    public Label brickText;  // Brick count
    public Label gOverText;  // Game over text
    public Label fScore;     // Final score text
    public Label lScore;     // Low Score
    public Label gScore;     // Good score
    public Label aScore;     // Amazing score
    
    // The other parts of the model-view-controller setup
    public Controller controller;
    public Model model;

    public GameObj   bat;            // The bat
    public GameObj   ball;           // The ball
    public GameObj[] bricks;         // The bricks
    public int       score =  0;     // The score
    public int brickNum = 0;         // Number of bricks
    public boolean gameEnd = false;  // A value to determine if the game is over
    public int finalScore = 0;       //The final score once the game ends
          
   
    // constructor method - we get told the width and height of the window
    public View(int w, int h)
    {
        Debug.trace("View::<constructor>");
        width = w;
        height = h;
    }

    // start is called from the Main class, to start the GUI up
    
    public void start(Stage window) 
    {
        // breakout is basically one big drawing canvas, and all the objects are
        // drawn on it as rectangles, except for the text at the top - this
        // is a label which sits 'in front of' the canvas.
        
        // Note that it is important to create control objects (Pane, Label,Canvas etc) 
        // here not in the constructor (or as initialisations to instance variables),
        // to make sure everything is initialised in the right order
        pane = new Pane();       // a simple layout pane
        pane.setId("Breakout");  // Id to use in CSS file to style the pane if needed
        
        // canvas object - we set the width and height here (from the constructor), 
        // and the pane and window set themselves up to be big enough
        canvas = new Canvas(width,height);  
        pane.getChildren().add(canvas);     // add the canvas to the pane
        
        // infoText box for the score - a label which we position in front of
        // the canvas (by adding it to the pane after the canvas)
        infoText = new Label("Score = " + score);
        infoText.setTranslateX(5);  // these commands setthe position of the text box
        infoText.setTranslateY(10);  // (measuring from the top left corner)
        pane.getChildren().add(infoText);  // add label to the pane
        infoText.setStyle("-fx-text-fill: white"); //changing the text colour to white
        
        fModeText = new Label("Press F for fast mode"); 
        fModeText.setTranslateX(5);
        fModeText.setTranslateY(40);
        pane.getChildren().add(fModeText);
        fModeText.setStyle("-fx-text-fill: red");
        
        nModeText = new Label("Press N for normal mode");
        nModeText.setTranslateX(5);
        nModeText.setTranslateY(60);
        pane.getChildren().add(nModeText);
        nModeText.setStyle("-fx-text-fill: blue");
        
        brickText = new Label("No. bricks = " + brickNum);
        brickText.setTranslateX(300);
        brickText.setTranslateY(10);
        pane.getChildren().add(brickText);
        brickText.setStyle("-fx-text-fill: green");
        
        
        
        // Make a new JavaFX Scene, containing the complete GUI
        Scene scene = new Scene(pane);   
        scene.getStylesheets().add("breakout.css"); // tell the app to use our css file

        // Add an event handler for key presses. By using 'this' (which means 'this 
        // view object itself') we tell JavaFX to call the 'handle' method (below)
        // whenever a key is pressed
        scene.setOnKeyPressed(this);

        // put the scene in the window and display it
        window.setScene(scene);
        window.show();
    }

    // Event handler for key presses - it just passes the event to the controller
    public void handle(KeyEvent event)
    {
        // send the event to the controller
        controller.userKeyInteraction( event );
    }
    
    public void GameOver()
    {
        gameEnd = true;
        
        gOverText = new Label("Game over");
        gOverText.setTranslateX(140);
        gOverText.setTranslateY(200);
        pane.getChildren().add(gOverText);
        gOverText.setStyle("-fx-text-fill: white; -fx-font: 70 impact");
    }
    
     
    
    // drawing the game image
    public void drawPicture()
    {
        // the game loop is running 'in the background' so we have
        // add the following line to make sure it doesn't change
        // the model in the middle of us updating the image
        if(brickNum <= 0){
            GameOver();
        }
        
        
        if(gameEnd == true) {
            finalScore = score; 
            nModeText.setVisible(false); // Hides all of the text to create a blank screen
            fModeText.setVisible(false); 
            brickText.setVisible(false);
            infoText.setVisible(false);
            
            
            fScore = new Label("Your final score is: " + finalScore);
            fScore.setTranslateX(120);
            fScore.setTranslateY(300);
            pane.getChildren().add(fScore);
            fScore.setStyle("-fx-text-fill: white");  
            
            model.setGameState("finished"); // Ends the game so the score doesn't change
            
            if(finalScore <= 2000){
                lScore = new Label("That's a low score, \n better luck next time");
                lScore.setTranslateX(110);
                lScore.setTranslateY(350);
                pane.getChildren().add(lScore);
                lScore.setStyle("-fx-text-fill: bronze");
            } else if (finalScore >= 2001 && finalScore <= 4000){
                gScore = new Label("That is a good score,\n well done");
                gScore.setTranslateX(110);
                gScore.setTranslateY(350);
                pane.getChildren().add(gScore);
                gScore.setStyle("-fx-text-fill: silver");
            } else if (finalScore >= 4001){
                aScore = new Label("That is an amazing score,\n I'm impressed");
                aScore.setTranslateX(110);
                aScore.setTranslateY(350);
                pane.getChildren().add(aScore);
                aScore.setStyle("-fx-text-fill: gold");
            }
            
            
            
            
            
            
            
            synchronized (model)
            {
              GraphicsContext gc = canvas.getGraphicsContext2D();
              // clear the whole canvas to white
              gc.setFill( Color.BLACK ); //changed the colour to black
              gc.fillRect( 0, 0, width, height );
              
            }
        }
        else{
            synchronized ( model ) 
            {
                // get the 'paint brush' to pdraw on the canvas
                GraphicsContext gc = canvas.getGraphicsContext2D();

                // clear the whole canvas to white
                gc.setFill( Color.BLACK ); //changed the colour to black
                gc.fillRect( 0, 0, width, height );
            
                // draw the bat and ball
                displayGameObj( gc, ball );   // Display the Ball
                displayGameObj( gc, bat  );   // Display the Bat

                // *[2]****************************************************[2]*
                // * Display the bricks that make up the game                 *
                // * Fill in code to display bricks from the brick array      *
                // * Remember only a visible brick is to be displayed         *
                // ************************************************************
                for (GameObj brick: bricks) {
                    if (brick.visible) {
                        displayGameObj(gc, brick);
                    } 
                }  
            
                // update the score
                infoText.setText("Score = " + score);
                
                // update the brick count
                brickText.setText("No. bricks = " + brickNum);
                brickText.setStyle("-fx-text-fill: green");
                
                
                
            
            }    
        }
        
    }

    // Display a game object - it is just a rectangle on the canvas
    public void displayGameObj( GraphicsContext gc, GameObj go )
    {
        gc.setFill( go.colour );
        gc.fillRect( go.topX, go.topY, go.width, go.height );
    }

    // This is how the Model talks to the View
    // This method gets called BY THE MODEL, whenever the model changes
    // It has to do whatever is required to update the GUI to show the new game position
    public void update()
    {
        // Get from the model the ball, bat, bricks & score
        ball    = model.getBall();              // Ball
        bricks  = model.getBricks();            // Bricks
        bat     = model.getBat();               // Bat
        score   = model.getScore();             // Score
        brickNum = model.getBrickNum();         // Brick count
        //Debug.trace("Update");
        drawPicture();                     // Re draw game
    }
}
