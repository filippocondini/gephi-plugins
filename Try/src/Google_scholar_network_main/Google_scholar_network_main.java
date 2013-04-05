/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Google_scholar_network_main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;
import org.yourorg.try1.*;
import org.gephi.data.attributes.api.*;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.spi.*;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrator
 */
public class Google_scholar_network_main implements Statistics {
   // private int totalEdgeCount;
   // private int selfLoopCount;
    private static String iden="";// test tring to be dispalyed in the report
    static Articles listofArticle[]=new Articles[100000];

   @Override
    public void execute(GraphModel graphModel, AttributeModel attributeModel)  {
        Graph graph = graphModel.getGraphVisible();
        //   execute(graph, attributeModel);
        //prova
         
        try {
            List_scroll();
         
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
   
   
    /*public void execute(Graph graph, AttributeModel attributeModel) {
      
       // selfLoopCount = 0;
       // totalEdgeCount = graph.getEdgeCount();
        for (Edge e : graph.getEdges()) {
            if (e.isSelfLoop()) {
           //     selfLoopCount++;
            }
        }
    }
    */
   //MAIN METHOD, SCROLL LIST
    public static void List_scroll(/*String[] args*/) throws Exception {
    //Articles listofArticle[]=new Articles[10000];
    int r=2;
    
    String identificator="853264223316211048";
    String title="a new method for energy accounting";
    String father="78909";
    int level=0;
    
    Articles a=new Articles(identificator,title,level,father);
    if(listofArticle[0]==null){
     listofArticle[0] = a;
    }
    iden=iden+"p1\n";
  // listofArticle[0].setArticle_id(identificator);
   iden=iden+"p1.2\n";
    //listofArticle[0].setArticle_level(level);
      //      listofArticle[0].setArticle_name(title);
        //    listofArticle[0].setid_father(father);
            
    for(int i=0;i<listofArticle.length;i++){
        iden=iden+"p2\n";
        //read the id and the level of article
       String id= listofArticle[i].getArticle_id();
      
       iden=iden+"p2.1\n";
      
      //il problema sta qui nel get
      int l=listofArticle[i].getArticle_level();
        
        if(level<r){
            iden=iden+"p3\n";
            //chiama il methodo scorri le pagine
            extract_pages(id,level);
           }
      }
    
    
    }
   
   
   
   
   
   @Override
    public String getReport(){
        /*String report = "<HTML> <BODY> <h1>Count Self-Loop Report </h1> "
                + "<hr>"
                + "<br> <h2> Results: </h2>"
                + "Total number of edges: " + totalEdgeCount
                + "<br />Number of self-loop: " + selfLoopCount
                + "<br />"
                + "</BODY></HTML>";*/
       
    String report ="test\n"+iden;
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
    public static void extract_pages(String id, int level) throws Exception{
        iden=iden+"p4\n";
        for(int j=0;j<100;j++){
            iden=iden+"p5\n";
            String url=(Url(id,j));
            extract_source(url,level);//devo risolvere il problema delle eccezioni
        }
    }
    public static void extract_source(String url,int level)throws Exception{
	     iden=iden+"p6\n";
                
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
                    iden=iden+"p7\n";
	           // System.out.println(inputLine);
                   // JavaApplication1 id=null; e da cancellare?
                    //chiama il metodo
                   identificator(inputLine,level);
                   
                }
	        in.close();
                }
         //metodo che trova le stringhe
  static String identificator(String input,int level){
      iden=iden+"p8\n";
        Pattern pattern = Pattern.compile("cites=([^<]+)&amp;as_sdt");
       java.util.regex.Matcher matcher = pattern.matcher(input);
  while(matcher.find()) {
      iden=iden+"p9\n";
    //System.out.println("Sottosequenza : "+matcher.group());
      String father="no father";
      String title="no title";
      String id=matcher.group(1);
      Articles a =new Articles(id,title,level,father);
      iden=iden +id+"\n";
       int g=0;
      for(int h=0;g<1;h++){
      if(listofArticle[h]==null){
     listofArticle[h] = a;
     g++;
          }//end if
          }//end for
      System.out.println(id);
    //System.out.println("Sottogruppo 1 : "+matcher.group(1));
            }
                return null;
            }
  //compongo la stringa
  public static String Url(String id, int j){
      iden=iden+"p10\n";
  String Url="http://scholar.google.it/scholar?"+j+"0&cites="+id;
  //http://scholar.google.it/scholar?start=00&cites=10291899245580779450
  return Url;
  }
    //finiti tutti i cazzo di metodi
}