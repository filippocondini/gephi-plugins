/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mynet.netpro;
import org.gephi.utils.progress.ProgressTicket;
import org.gephi.io.generator.spi.Generator;
import org.gephi.io.generator.spi.GeneratorUI;
import org.gephi.io.importer.api.ContainerLoader;
import org.gephi.io.importer.api.EdgeDraft;
import org.gephi.io.importer.api.NodeDraft;
import org.openide.util.lookup.ServiceProvider;
import org.mynet.netpro.Articles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.gephi.data.attributes.api.*;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.*;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.statistics.spi.*;
import org.openide.util.Exceptions;
import org.gephi.dynamic.api.DynamicModel;
@ServiceProvider(service = Generator.class)
/**
 *
 * @author Administrator
 */
public class Gen implements Generator,Statistics{
      protected ProgressTicket progress;
    protected boolean cancel = false;
public static Articles listofArticle[]=new Articles[100000];
public static String net="";
   
        
            //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA         
    
    

   @Override
    public void execute(GraphModel graphModel, AttributeModel attributeModel)  {
      Graph graph = graphModel.getGraphVisible();
        //  execute(graph, attributeModel);
        //prova
         
    try {
            List_scroll();
         
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        
   
   }
   //MAIN METHOD, SCROLL LIST
    public static void List_scroll(/*String[] args*/) throws Exception {
    //Articles listofArticle[]=new Articles[10000];
    int r=2;
    
    String identificator="853264223316211048";
    String title="a new method for energy accounting";
    String father=null;
    int level=0;
    
    Articles a=new Articles(identificator,title,level,father);
    if(listofArticle[0]==null){
     listofArticle[0] = a;
    }
    
    net=net+a.getArticle_id()+a.getArticle_level()+"\n";
 
            
    for(int i=0;listofArticle[i]!=null;i++){
        
        //read the id and the level of article
       String source= listofArticle[i].getArticle_id();

      int l=listofArticle[i].getArticle_level();
        System.out.println("I'm analyzing:"+source +"  "+l);
        net=net + "analizzo questo:"+source +"  "+l+"\n";
        if(l<r){
            
            level=l+1;
            //chiama il methodo scorri le pagine
            extract_pages(source,level);
           }//end of if
      }  //end of for
    
    }//end of method list scroll
   
  // @Override
    public String getReport(){
       
    String report ="test\n"+net;
       // for(int k=0;k<listofArticle.length;k++){
         //   report=report+listofArticle[k].getArticle_id()+"\n";
        //}
      return report;
}
    public int getProjectFirst(){
        int prova=3;
        return prova;//selfLoopCount;
    }
    ///da qui in poi scrivo tutti i cazzo di metodi
    public static void extract_pages(String source, int level) throws Exception,InterruptedException{
        
        //would be j<100
        for(int j=0;j<1;j++){
            
            String url=(Url(source,j));
            try{
  //do what you want to do before sleeping
  Thread.currentThread().sleep(10000);//sleep for 1000 ms
  //do what you want to do after sleeptig
}
catch(InterruptedException ie){
        
//If this thread was intrrupted by nother thread 
}   
            extract_source(url,level,source);//devo risolvere il problema delle eccezioni
        }
    }
    public static void extract_source(String url,int level, String source)throws Exception{
	    
                
	        //URL yahoo = new URL("http://scholar.google.it/scholar?start=987&q=cane&hl=it&as_sdt=0");
                //I create the url calling the method Url()
                URL yahoo = new URL(url);
                //extracting page give a url
                File filew = new File("text.txt");
	        FileWriter writer = new FileWriter(filew, true);
                
	        URLConnection yc = yahoo.openConnection();
	        yc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:x.x.x) Gecko/20041107 Firefox/x.x");
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                yc.getInputStream()));
	        String inputLine;
	 
	        while ((inputLine = in.readLine()) != null){
                    
	           // System.out.println(inputLine);
                   
                    //chiama il metodo
                   identificator(inputLine,level,source);
                   
                }
	        in.close();
                }
         //metodo che trova le stringhe
  static String identificator(String input,int level,String source){
      
        Pattern pattern = Pattern.compile("cites=([^<]+)&amp;as_sdt");
       java.util.regex.Matcher matcher = pattern.matcher(input);
       int i=0;//per la prova
  while(matcher.find()/*&i<3*/) {
      i++;//per la prova
    //System.out.println("Sottosequenza : "+matcher.group());
      String father=source;
      String title="no title";
      String id=matcher.group(1);
      Articles a =new Articles(id,title,level,father);
      
       int g=0;
      for(int h=0;g<1;h++){
      if(listofArticle[h]==null){
     listofArticle[h] = a;
     g++;
          }//end if
          }//end for
      String b=a.getArticle_id();
      int c=a.getArticle_level();
      String d=a.getid_father();
      System.out.println(b +"   level:"+c+"       father:"+d+"\n");
      net=net + b +"   level:"+c+"       father:"+d+"\n";
     // System.out.println(id);
    //System.out.println("Sottogruppo 1 : "+matcher.group(1));
            }
                return null;
            }
  //compongo la stringa
  public static String Url(String source, int j){
      
  String Url="http://scholar.google.it/scholar?"+j+"0&cites="+source;
  //http://scholar.google.it/scholar?start=00&cites=10291899245580779450
  return Url;
  }
    //finiti tutti i cazzo di metodi
  
        //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        
        
        
        
        
        
         @Override
    public  void generate(ContainerLoader container)  {
        
        try {
            List_scroll();
         
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        
      for(int i=0;Articles.listofArticle[i]!=null;i++){
       if(Articles.listofArticle[i]!=null){
           NodeDraft n1 = container.factory().newNodeDraft();
           n1.setLabel(Articles.listofArticle[i].getArticle_id());
           n1.setId(Articles.listofArticle[i].getArticle_id());
           
          container.addNode(n1);
         //NodeDraft n2= container.getNode(Articles.listofArticle[i].getid_father());
         
         // EdgeDraft e = container.factory().newEdgeDraft();
         //  e.setSource(n2);
         //   e.setTarget(n1);
       //     List<Node> nodes = new ArrayList<Node>();
         //  int index=0;
           // Node m = nodes.get(index);
            
              //      if (n1 != m && graph.getEdge(n1, m) == null) {
            //            Edge e = graphModel.factory().newEdge(n1, m);
          //              graph.addEdge(e);
        //    }
            
           
        }//end if
      }//end for
      
       for(int i=1;Articles.listofArticle[i]!=null;i++){
       if(Articles.listofArticle[i]!=null){
          // NodeDraft n1 = container.factory().newNodeDraft();
          // n1.setLabel(Articles.listofArticle[i].getArticle_id());
           
         // container.addNode(n1);
         NodeDraft n2= container.getNode(Articles.listofArticle[i].getid_father());
         NodeDraft n1= container.getNode(Articles.listofArticle[i].getArticle_id());
          EdgeDraft e = container.factory().newEdgeDraft();
           e.setSource(n2);
            e.setTarget(n1);
            container.addEdge(e);
       //     List<Node> nodes = new ArrayList<Node>();
         //  int index=0;
           // Node m = nodes.get(index);
            
              //      if (n1 != m && graph.getEdge(n1, m) == null) {
            //            Edge e = graphModel.factory().newEdge(n1, m);
          //              graph.addEdge(e);
        //    }
            
           
        }//end if
      }//end for
       // int i=Articles.listofArticle.length;
        
        // create nodes fatto
     //   NodeDraft n1 = container.factory().newNodeDraft();
       // NodeDraft n2 = container.factory().newNodeDraft();

        // set node labels fatto
      //  n1.setLabel("Hello");
     //   n2.setLabel("World");

        // create edge
      //  EdgeDraft e = container.factory().newEdgeDraft();
      //  e.setSource(n1);
     //   e.setTarget(n2);

        // fill in the graph fatto
    //    container.addNode(n1);
    //   container.addNode(n2);
        //container.addEdge(e);
    }//end of generate method

    @Override
    public String getName() {
        return " Network";
    }

    @Override
    public GeneratorUI getUI() {
        return null;
    }

    @Override
    public boolean cancel() {
        cancel = true;
        return true;
    }

    @Override
    public void setProgressTicket(ProgressTicket progressTicket) {
        this.progress = progressTicket;
    }
    public String nodes(){
        
    return null;
    }
    // @Override
    
}
