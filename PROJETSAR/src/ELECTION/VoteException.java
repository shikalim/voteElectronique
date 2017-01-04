
/*VoteException est levée lorsqu'une entité essaie de vote plus d'une fois*/

public class VoteException extends Exception
{


    public String getMessage()
    {
      return "J'ai déjà voté";

    }

}
