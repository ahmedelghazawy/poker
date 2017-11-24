import java.util.*;
import java.lang.*;

/**
 *A class representing a poker hand inheriting from CardCollection.
 */
public class PokerHand extends CardCollection
{

  /**
   *Constructor for creating an empty poker hand.
   */
  public PokerHand()
  {
    super();
  }


  /**
   *Constructor for creating poker hands from a string specifying cards in it.
   *
   *@param cards Cards to be added
   */
  public PokerHand(String cards)
  {
    if(cards.length() == 0)
    {
      return;
    }

    String parts[] = cards.split("\\s+");
    if(parts.length < 6)
    for(String current: parts)
    {
      Card temp = new Card(current);
      this.add(temp);
    }
    else
    {
      throw new PokerException("More than five cards provided\n");
    }

  }

  /**
   * Adds the given card to this collection.
   *
   * @param card Card to be added
   */
  public void add(Card card)
  {
    if((cards.size() < 5) && (cards.contains(card) == false))
      super.add(card);
    else if(cards.size() == 5)
    {
      throw new PokerException("Hand full\n");
    }
    else
    {
      throw new PokerException("Hand already contains this card\n");
    }
  }

  /**
   * Discards all the cards from this collection.
   *
   *@param deck Deck to return the cards to
   */
  public void discard (Deck deck)
  {
    if(cards.isEmpty() != true)
    {
      for(Card card: cards)
      {
        deck.add(card);
      }
      super.discard();
    }
    else
      throw new PokerException("Hand is already empty\n");
  }

  /**
   * Creates a two-character string representation of each card in the hand separated by spaces.
   *
   * <p>The first character represents rank, the second represents suit.</p>
   *
   * @return String representation of this hand
   */

  public String toString()
  {
    String hand = "";
    for(Card current: cards)
    {
      if(hand.length() != 0)
        hand += " ";
      hand += current.toString();
    }
    return hand;
  }

  /**
   * Creates a fancy two-character string representation of each card in this hand separated by spaces.
   *
   * <p>The first character represents rank, the second is a Unicode
   * symbol representing the suit.</p>
   *
   * @return String representation of this hand
   */

  public String toFancyString()
  {
    String fancyHand = "";
    for(Card current: cards)
    {
      if(fancyHand.length() != 0)
        fancyHand += " ";
      fancyHand += current.toFancyString();
    }
    return fancyHand;
  }

  /**
   *@return true if this hand is a four of a kind, false otherwise
   */
  public boolean isFourOfAKind()
  {
    if(cards.size() != 5)
    {
      return false;
    }
    Map<Card.Rank,Integer> hand = new HashMap<>();
    for(Card current: cards)
    {
      Card.Rank key = current.getRank();
      if(hand.containsKey(key) == true)
        hand.put(key, hand.get(key)+1);
      else
      {
        hand.put(key, 1);
      }
    }
    if(hand.containsValue(4) == true)
      return true;
    return false;
  }

  /**
   *@return true if this hand is a flush, false otherwise
   */
  public boolean isFlush()
  {
    if(cards.size() != 5)
    {
      return false;
    }
    Card first = cards.get(1);
    for(Card temp: cards)
    {
      if (first.getSuit().compareTo(temp.getSuit() ) != 0 )
        return false;
    }
    return true;
  }

  /**
   *@return true if this hand is a pair, false otherwise
   */
  public boolean isPair()
  {
      if(cards.size() != 5 || isTwoPairs())
    {
      return false;
    }
    Map<Card.Rank,Integer> hand = new HashMap<>();
    for(Card current: cards)
    {
      Card.Rank key = current.getRank();
      if(hand.containsKey(key) == true)
        hand.put(key, hand.get(key)+1);
      else
      {
        hand.put(key, 1);
      }
    }
    if(hand.containsValue(2) == true)
      return true;
    return false;
  }

  /**
   *@return true if this hand is two pairs, false otherwise
   */
  public boolean isTwoPairs()
  {
    if(cards.size() != 5 )
    {
      return false;
    }
    Map<Card.Rank,Integer> hand = new HashMap<>();
    for(Card current: cards)
    {
      Card.Rank key = current.getRank();
      if(hand.containsKey(key) == true)
        hand.put(key, hand.get(key)+1);
      else
      {
        hand.put(key, 1);
      }
    }
    List<Integer> ranks = new ArrayList<>(hand.values());
    int pairs = 0;
    for(int i = 0; i<ranks.size(); i++)
    {
      if (ranks.get(i) == 2)
        pairs++;
    }
    if(pairs == 2)
      return true;
    return false;
  }

  /**
   *@return true if this hand is a three of a kind, false otherwise
   */
  public boolean isThreeOfAKind()
  {
    if(cards.size() != 5 || isFullHouse())
    {
      return false;
    }
    Map<Card.Rank,Integer> hand = new HashMap<>();
    for(Card current: cards)
    {
      Card.Rank key = current.getRank();
      if(hand.containsKey(key) == true)
        hand.put(key, hand.get(key)+1);
      else
      {
        hand.put(key, 1);
      }
    }
    if(hand.containsValue(3) == true)
      return true;
    return false;
  }

  /**
   *@return true if this hand is a full house, false otherwise
   */
  public boolean isFullHouse()
  {
    if(cards.size() != 5)
    {
      return false;
    }
    Map<Card.Rank,Integer> hand = new HashMap<>();
    for(Card current: cards)
    {
      Card.Rank key = current.getRank();
      if(hand.containsKey(key) == true)
        hand.put(key, hand.get(key)+1);
      else
      {
        hand.put(key, 1);
      }
    }
    if(hand.containsValue(3) && hand.containsValue(2))
      return true;
    return false;
  }

  /**
   *@return true if this hand is straight, false otherwise
   */
  public boolean isStraight()
  {
    if(cards.size() != 5)
    {
      return false;
    }
    int[] hand = new int[5];
    int i=0;
    for(Card cardInHand: cards)
    {
      hand[i] = cardInHand.getRank().ordinal();
      i++;
    }
    //Arrays.sort(hand);
    boolean straight = false;
    for(i=0; i<4; i++)
    {
      if(hand[i] == (hand[i+1] -1))
        straight = true;
      else if(hand[4] == 0 && hand[3] == 12)
        straight = true;
      else
        return false;
    }
    return straight;
  }
}
