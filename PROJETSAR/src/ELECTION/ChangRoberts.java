
package ELECTION ;
public class ChangRoberts{



  public static void provoquerElection(BureauDeVote bureau){
    String message="ElectionChef "+bureau.getNumero()+"\n";
    bureau.voter();
    Communication.envoyer(message,bureau.getNextAdress(),bureau.getNextUdpPort());
  }

  public static void receptionElection(BureauDeVote bureau,int numero)
  {
    System.out.println("Mon numéro pour l'élection: "+bureau.getNumero());
    if(numero > bureau.getNumero())
    {
      String message="ElectionChef "+numero+"\n";
      Communication.envoyer(message,bureau.getNextAdress(),bureau.getNextUdpPort());
      bureau.voter();
      return ;
    }

    if(numero < bureau.getNumero() && !bureau.getVote())
    {
      String message="ElectionChef "+bureau.getNumero()+"\n";
      Communication.envoyer(message,bureau.getNextAdress(),bureau.getNextUdpPort());
      bureau.voter();
      return ;
    }

    if(bureau.getNumero()==numero && bureau.getVote()){
      System.out.println("Je suis le nouveau chef !!!!!!!!!");
      String message ="ChefElu "+bureau.getAdress()+" "+bureau.getUdpPort()+"\n";
      Communication.envoyer(message,bureau.getNextAdress(),bureau.getNextUdpPort());
      bureau.elirChef();
      return ;
    }

    if(bureau.getNumero()==numero && !bureau.getVote()){

      String message="ElectionChef "+numero+"\n";
      Communication.envoyer(message,bureau.getNextAdress(),bureau.getNextUdpPort());
      bureau.voter();
      return ;
    }

  }

  public static void receptionElu(BureauDeVote bureau,String message,String adresse,int port)
  {
    if(bureau.EstChef()) return ;
    Communication.envoyer(message,bureau.getNextAdress(),bureau.getNextUdpPort());
    bureau.setNextAdress(adresse);
    bureau.setNextUdpPort(port);
    System.out.println("Après élection");
    System.out.println(bureau);
  }

}
