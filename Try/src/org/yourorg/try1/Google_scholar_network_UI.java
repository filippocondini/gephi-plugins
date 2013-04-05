/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorg.try1;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;



/**
 *
 * @author Administrator
 */
@ServiceProvider(service = StatisticsUI.class)
public class Google_scholar_network_UI implements StatisticsUI{
private Google_scholar_network_main.Google_scholar_network_main statistic;
    @Override
    public JPanel getSettingsPanel() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setup(Statistics ststcs) {
        this.statistic= (Google_scholar_network_main.Google_scholar_network_main) ststcs;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unsetup() {
        this.statistic=null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return Google_scholar_network_main.Google_scholar_network_main.class;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValue() {
       // if(statistic!=null){
           // return "" +statistic.getProjectFirst();
    //    }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   //return "";
        return "";
    }

    @Override
    public String getDisplayName() {
        return "Google  Scholar Network";
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   // @Override
   // public String getShortDescription() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  //  }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_NETWORK_OVERVIEW;
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPosition() {
        return 1000;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getShortDescription() {
        return "network of the google scholar articles";
    }
    
}
