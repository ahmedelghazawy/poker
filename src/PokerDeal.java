import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *JavaFX program simulating a dealt poker game
 *@author Ahmed Elghazawy
 */
 public class PokerDeal extends Animate
 {
   private Scene scene_2, scene_3;

   @Override
   public void start(Stage primaryStage)
   {
     //creating layouts
     Group layout_1, layout_2, layout_3;

     //creating the Deck and the hands

     Deck deck = new Deck();
     deck.shuffle();
     PokerHand first = new PokerHand();
     PokerHand second = new PokerHand();

     //creating text for the fist scene
     Label start = new Label();
     start.setText("Let's start this game!");
     start.setLayoutX(280);
     start.setLayoutY(230);
     start.setFont(new Font(20));
     start.setTextFill(Color.RED);

     //setting button
     Button play = new Button("Play");
     play.setLayoutX(360);
     play.setLayoutY(270);
     play.setOnAction(e-> primaryStage.setScene(scene_2));

     //setting scene 1
     layout_1 = new Group();
     layout_1.getChildren().addAll(play, start);
     Scene scene_1 = new Scene(layout_1, 800, 600, Color.CYAN);

     //Dealing the game
     int i;
     for(i=0; i<5; i++)
     {
       first.add(deck.deal());
       second.add(deck.deal());
     }

     //creating transition button
     Button check = new Button("Check hands' types");
     check.setLayoutX(320);
     check.setLayoutY(250);
     check.setOnAction(e-> primaryStage.setScene(scene_3));

     //setting player 1 label
     Label player_1 = new Label();
     player_1.setText("Player 1");
     player_1.setFont(new Font(40));
     player_1.setTextFill(Color.RED);
     player_1.setLayoutX(100);
     player_1.setLayoutY(415);

     //setting player 2 label
     Label player_2 = new Label();
     player_2.setText("Player 2");
     player_2.setFont(new Font(40));
     player_2.setTextFill(Color.RED);
     player_2.setLayoutX(530);
     player_2.setLayoutY(415);

     //layout of scene 2
     layout_2 = new Group();
     for(i=0; i<5; i++)
     {
       layout_2.getChildren().addAll(new ImageView(first.cards.get(i).getImage()));
    }
    for (i=0; i<5; i++)
    {
      layout_2.getChildren().addAll(new ImageView(second.cards.get(i).getImage()));
    }
    layout_2.getChildren().addAll(check, player_1, player_2);


    int iVector_1 = 720;
    int iVector_2 = 305;
    //loops for spreading cards
    for(i=5; i<10; i++)
    {
      TranslateTransition translate_1 = new TranslateTransition(Duration.millis(5000));
      translate_1.setToX(iVector_1);
      translate_1.setToY(500);
      RotateTransition rotate = new RotateTransition(Duration.millis(5000));
      rotate.setToAngle(360);
      ParallelTransition transition = new ParallelTransition(layout_2.getChildren().get(i), translate_1, rotate);
      transition.play();

      transition.play();
      iVector_1 -= 75;
    }

    for(i=0; i<5; i++)
    {
      TranslateTransition translate_2 = new TranslateTransition(Duration.millis(5000));
      translate_2.setToX(iVector_2);
      translate_2.setToY(500);
      RotateTransition rotate_2 = new RotateTransition(Duration.millis(5000));
      rotate_2.setToAngle(360);
      ParallelTransition transition = new ParallelTransition(layout_2.getChildren().get(i), translate_2, rotate_2);
      transition.play();

      transition.play();
      iVector_2 -= 75;
    }

    //constructing scene 2
    scene_2 = new Scene(layout_2, 800, 600, Color.CYAN);

    //setting up scoring system
    int score_1 = 0;
    String hand_1 = "";
    int score_2 = 0;
    String hand_2 = "";
    if(first.isFourOfAKind())
    {
      score_1 = 7;
      hand_1 = "Four of a kind";
    }
    else if(first.isFullHouse())
    {
      score_1 = 6;
      hand_1 = "Full house";
    }
    else if(first.isFlush())
    {
      score_1 = 5;
      hand_1 = "Flush";
    }
    else if(first.isStraight())
    {
      score_1 = 4;
      hand_1 = "Straight";
    }
    else if(first.isThreeOfAKind())
    {
      score_1 = 3;
      hand_1 = "Three of a kind";
    }
    else if(first.isTwoPairs())
    {
      score_1 = 2;
      hand_1 = "Two pairs";
    }
    else if(first.isPair())
    {
      score_1 = 1;
      hand_1 = "Pair";
    }
    else
    {
      hand_1 = "nothing";
    }

    if(second.isFourOfAKind())
    {
      score_2 = 7;
      hand_2 = "Four of a kind";
    }
    else if(second.isFullHouse())
    {
      score_2 = 6;
      hand_2 = "Full house";
    }
    else if(second.isFlush())
    {
      score_2 = 5;
      hand_2 = "Flush";
    }
    else if(second.isStraight())
    {
      score_2 = 4;
      hand_2 = "Straight";
    }
    else if(second.isThreeOfAKind())
    {
      score_2 = 3;
      hand_2 = "Three of a kind";
    }
    else if(second.isTwoPairs())
    {
      score_2 = 2;
      hand_2 = "Two pairs";
    }
    else if(second.isPair())
    {
      score_2 = 1;
      hand_2 = "Pair";
    }
    else
    {
      hand_2 = "nothing";
    }

    //Creating hand type labels
    Label type_1 = new Label();
    type_1.setText("Player 1 has: " + hand_1);
    type_1.setLayoutX(100);
    type_1.setLayoutY(500);
    type_1.setFont(new Font(20));
    type_1.setTextFill(Color.RED);

    Label type_2 = new Label();
    type_2.setText("Player 2 has: " + hand_2);
    type_2.setLayoutX(500);
    type_2.setLayoutY(500);
    type_2.setFont(new Font(20));
    type_2.setTextFill(Color.RED);

    //Creating result labels
    Label tie = new Label();
    Label pTwo = new Label();
    Label pOne = new Label();

    //checking the winner
    if(score_1 == score_2)
    {
      tie.setText("Tie!");
      tie.setLayoutX(350);
      tie.setLayoutY(250);
      tie.setFont(new Font(60));
      tie.setTextFill(Color.BLACK);
    }
    else if(score_1 > score_2)
    {
      pOne.setText("Player one won!!");
      pOne.setLayoutX(200);
      pOne.setLayoutY(250);
      pOne.setFont(new Font(60));
      pOne.setTextFill(Color.BLACK);
    }
    else if(score_1 < score_2)
    {
      pTwo.setText("Player two won!!");
      pTwo.setLayoutX(200);
      pTwo.setLayoutY(250);
      pTwo.setFont(new Font(60));
      pTwo.setTextFill(Color.BLACK);
    }

    //creating final layout
    layout_3 = new Group();
    layout_3.getChildren().addAll(type_1, type_2, tie, pOne, pTwo);

    //final scene creation
    scene_3 = new Scene(layout_3, 800, 600, Color.CYAN);

    primaryStage.setTitle("-- Poker Game --");
    primaryStage.setScene(scene_1);
    primaryStage.show();
  }

   public static void main(String[] args)
    { launch(args); }
 }
