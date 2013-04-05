/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorg.try1;

//import org.gephi.statistics.spi.Statistics;
//import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;
import org.gephi.statistics.spi.*;

import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Graph;
import org.gephi.statistics.plugin.*;



@ServiceProvider(service = StatisticsBuilder.class)
/**
 *
 * @author Administrator
 */
public class Google_scholar_network_builder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Google scholar network";
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Statistics getStatistics() {
        return new Google_scholar_network_main.Google_scholar_network_main();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return Google_scholar_network_main.Google_scholar_network_main.class;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
