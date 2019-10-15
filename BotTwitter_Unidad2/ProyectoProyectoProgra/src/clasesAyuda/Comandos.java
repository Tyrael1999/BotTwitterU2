/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesAyuda;

import java.io.IOException;
import java.util.List;
import proyectoproyectoprogra.MotorClases.TwitterBot;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
/**
 *
 * @author Mecho
 */
public class Comandos {
    
    public void buscarH(long id) throws TwitterException, IOException{
        
        String gustar = "#gustar";
        String seguir = "#seguir";
        String retweet = "#difundir";
        int op = 1;
        TwitterBot bot = new TwitterBot();
        String mensaje = "@javinMoraga #gustar";
        
        String[] partes = mensaje.split(" ");
        
        if("@javinMoraga".equals(partes[0])){
            if(partes[1].equals(gustar)){
                op = 1;
            }
            if(partes[1].equals(seguir)){
                op = 2;
            }
            if(partes[1].equals(retweet)){
                op = 3;
            }
            switch(op){
                case 1:
                    bot.likeTweet(id);
                    if(partes[2]!=null){
                        long newid = Long.parseLong(partes[2]);
                        bot.likeTweet(newid);
                    }
                    break;
                case 2:
                    if(partes[2]!=null){
                        
                    }
                    break;
                case 3:
                    
                    if(partes[2]!=null){
                        long newid = Long.parseLong(partes[2]);
                        bot.retweet(newid);
                    }
                    else{
                        bot.retweetList(id);
                    }
                    break;
            }
        }
    }
}
