
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kye
 */
public class ExchangeRates {
    private String FromCurrency;
    private String ToCurrency;
    private HashMap Rates;
    public ExchangeRates(){
        Rates = new HashMap();
        this.update("USD");
    }
    public void update(String FromUnits){
    String URLstr = "http://themoneyconverter.com/rss-feed/";
    FromCurrency = FromUnits;
    SyndFeed feed = null;
        try {
            URL feedUrl = new URL(URLstr+FromCurrency+"/rss.xml");

            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedUrl));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }
        
        if (feed != null) {
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                String title = entry.getTitle();
                String descr = entry.getDescription().getValue();
                
                System.out.println("Title: "+title+"\nDescription: "+descr);
                double rate;
                int start = descr.indexOf("=") + 1;
                int end = descr.indexOf(".", start) + 6;
                rate = Double.parseDouble(descr.substring(start, end).replace(",", ""));
                Rates.put(title, rate);
            }
        } else {
            System.out.println("RSS Feed unavailable.");
            }
    }
    
        public String getUnits(){
            return FromCurrency;
        }
        
        public double getRate(String ToUnits){
            String conversion = ToUnits+"/"+FromCurrency;
            double rate = 0;
            try{
                rate = (Double) Rates.get(conversion);
            }
            catch(Exception e){}
            return rate;
        }
    
}
