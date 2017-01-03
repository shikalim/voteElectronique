package ELECTION;

import java.util.Hashtable;

public class Message {
    private String message;
    private Hashtable<String, String> dic;

    public Message(String string) {
        this.message = string;
        this.dic = new Hashtable <String,String>();
    }

    public Hashtable<String, String> insertMess() {
        String[] arrstring = this.message.split(" ");
        this.dic.put("type", arrstring[0].trim());
        this.dic.put("ip", arrstring[1].trim());
        this.dic.put("port", arrstring[2].trim());
        return this.dic;
    }

    public Hashtable<String, String> newCMess() {
        String[] arrstring = this.message.split(" ");
        this.dic.put("type", arrstring[0].trim());
        this.dic.put("ip", arrstring[1].trim());
        this.dic.put("port", arrstring[2].trim());
        return this.dic;
    }

    public Hashtable<String,String>  messElu(){
      String[] arrstring = this.message.split(" ");
      this.dic.put("type", arrstring[0].trim());
      this.dic.put("ip", arrstring[1].trim());
      this.dic.put("port", arrstring[2].trim());
      return this.dic;
    }
    public Hashtable<String,String>messUDP(){
      String[] arrstring = this.message.split(" ");
      this.dic.put("type", arrstring[0].trim());

      if(dic.get("type").equals("ElectionChef"))
      {
        this.dic.put("numero", arrstring[1].trim());
      }

      if(dic.get("type").equals("ChefElu")){
        this.dic.put("type", arrstring[0].trim());
        this.dic.put("ip", arrstring[1].trim());
        this.dic.put("port", arrstring[2].trim());
        

      }

      return this.dic;
    }
}
