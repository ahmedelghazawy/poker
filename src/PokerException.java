/**
 *Throws an exception for poker rules mistakes
 */
public class PokerException extends RuntimeException
{
  public PokerException(String message)
  {
    super(message);
  }
}
