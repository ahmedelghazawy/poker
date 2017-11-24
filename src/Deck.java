import java.util.*;

/**
 *A class representing a deck of cards
 *
 *@author Ahmed Elghazawy
 */

public class Deck extends CardCollection
{

/**
 *Constructor creating an empty deck inheriting from CardCollection.
 */
  public Deck()
  {
    super();
    for(Card.Suit suit: Card.Suit.values())
    {
      for(Card.Rank rank: Card.Rank.values())
      {
        Card temp = new Card(rank,suit);
        this.add(temp);
      }
    }
  }

/**
 *Deals a card.
 *
 *@return Card drawn from deck.
 */
  public Card deal()
  {
    if(this.cards.isEmpty())
      throw new PokerException("Deck is empty");
    else
    {
      Card dealt = cards.get(0);
      this.cards.remove(dealt);
      return dealt;
    }
  }

/**
 *Shuffles the deck in a random order.
 */

  public void shuffle()
  {
    Collections.shuffle(cards);
  }
}
