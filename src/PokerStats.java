import java.util.*;
import java.lang.*;
/**
 *Class showing the statistics of poker hands in a user specified number of decks
 */
class PokerStats
{
  public static void main(String[] args)
  {
    //Declaring all objects and variables and setting their values
    Scanner input = new Scanner(System.in);
    System.out.println("How many decks would you like?\n");
    int noOfDecks = input.nextInt();
    int dealCounter = 0;
    int flushCounter = 0;
    int fOAKCounter = 0;
    int handsCounter = noOfDecks * 10;
    int tOAKcounter = 0;
    int straightCounter = 0;
    int pairCounter = 0;
    int twoPairsCounter = 0;
    int fullHouseCounter = 0;
    List<Deck> decks = new LinkedList<>();

    //Shuffling decks and adding them to the LinkedList of decks
    for(int i=0; i < noOfDecks; i++)
    {
      Deck temp = new Deck();
      temp.shuffle();
      decks.add(temp);
    }

    for(Deck deck: decks)
    {
      for(int i=0; i < 10; i++)
      {

        //creation of each new hand
        PokerHand temp = new PokerHand();
        for(int j=0; j<5; j++)
        {
          //Dealing a card from deck and adding it to the current hand
          temp.add(deck.deal());
          dealCounter++;
        }

        //checking type of each hand to increment it's counter
        if (temp.isFlush())
        {
          flushCounter++;
        }
        if(temp.isFourOfAKind())
        {
          fOAKCounter++;
        }
        if(temp.isPair())
        {
          pairCounter++;
        }
        if(temp.isTwoPairs())
        {
          twoPairsCounter++;
        }
        if(temp.isFullHouse())
        {
          fullHouseCounter++;
        }
        if(temp.isThreeOfAKind())
        {
          tOAKcounter++;
        }
        if(temp.isStraight())
        {
          straightCounter++;
        }

        //prints hand in a fancy string
        System.out.println(temp.toFancyString());
      }
      System.out.println("");
    }

    //calculation of percentage of each hand type from the hands obtained
    double flushProbability = (double) flushCounter * 100 / handsCounter;
    double fOAKprobability = (double) fOAKCounter * 100 / handsCounter;
    double pairProbability = (double) pairCounter * 100 / handsCounter;
    double twoPairsProbability = (double) twoPairsCounter * 100 / handsCounter;
    double fullHouseProbability = (double) fullHouseCounter * 100 / handsCounter;
    double tOAKprobability = (double) tOAKcounter * 100 / handsCounter;
    double straightProbability = (double) straightCounter * 100 / handsCounter;

    //printing data
    System.out.println(dealCounter + " deals");
    System.out.printf("\nP(Flush)            =   %.3f%%", flushProbability);
    System.out.printf("\nP(Four of a kind)   =   %.3f%%", fOAKprobability);
    System.out.printf("\nP(Pair)             =   %.3f%%", pairProbability);
    System.out.printf("\nP(Full house)       =   %.3f%%", twoPairsProbability);
    System.out.printf("\nP(Two pairs)        =   %.3f%%", fullHouseProbability);
    System.out.printf("\nP(Three of a kind)  =   %.3f%%", tOAKprobability);
    System.out.printf("\nP(Straight)         =   %.3f%%\n", straightProbability);

  }
}
