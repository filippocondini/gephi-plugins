
package org.mynet.netpro;


import org.gephi.utils.progress.ProgressTicket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;
import org.gephi.data.attributes.api.*;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.gephi.io.generator.spi.Generator;
import org.gephi.io.generator.spi.GeneratorUI;
import org.gephi.io.importer.api.ContainerLoader;
import org.gephi.io.importer.api.EdgeDraft;
import org.gephi.io.importer.api.NodeDraft;
//import org.gephi.utils.progress.Progress;
//import org.gephi.utils.progress.ProgressTicket;
//import org.jdesktop.swingx.autocomplete.TextComponentAdaptor;
//import org.openide.util.Lookup;
//import org.openide.util.NbBundle;
//import org.gephi.io.generator.spi.Generator;
//import org.gephi.io.generator.spi.GeneratorUI;
//import org.gephi.io.importer.api.ContainerLoader;
//import org.gephi.io.importer.api.EdgeDraft;
//import org.gephi.io.importer.api.NodeDraft;
//import org.openide.util.lookup.ServiceProvider;
//import org.mynet.netpro.Articles;
//import org.gephi.dynamic.api.DynamicModel;
//import org.openide.util.Lookup;
//import java.util.Random;
//import javax.swing.Box;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JTextPane;
//import javax.swing.plaf.basic.BasicOptionPaneUI;
//import javax.swing.text.JTextComponent;
//import org.gephi.graph.api.Edge;
//import org.gephi.graph.api.Graph;
//import org.gephi.graph.api.*;
//import org.gephi.graph.api.GraphModel;
//import org.gephi.graph.api.Node;
//import org.gephi.statistics.spi.*;
//import javax.swing.JTextArea;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
@ServiceProvider(service = Generator.class)
/**
 * Plugin able to generate the google scholar network
 * nodes represent google scholar articles
 * links represent the cite of the source node (article) to the target node (article)
 * The action is set up in the
 * "Generate" menu in the "file" menu.
 * 
 * @author Filippo Condini
 */

public class Gen implements Generator{
      protected ProgressTicket progress;
    protected boolean cancel = false;
public static Articles listofArticle[]=new Articles[100000];
/*method List_scroll
 * 
 *the method inizialize the list with the seeds and launch the method "extract_pages"
 * 
 * it receive as parameters the user's imputs:
 * radius of the network
 * string of the seeds separated by comma
 * the interval between two request to the google server, in order to avoid 404,403,505 errors
 * the number of pages to be analized for each research
 *
 */
    public static void List_scroll(int radius,String id_article, int sleep , int pag) throws Exception {
        //clean the listof Article
     for(int k=0;listofArticle[k]!=null;k++)   
     {listofArticle[k]=null;
     System.out.println(k);}
    
    int r=radius;
    int sleeper=sleep;
    int pages=pag;
    //String identificator="853264223316211048,10118837676854620386";
    
    //split the string
    String[] items = id_article.split(",");
int d=0;

String father=null;
    int level=0;
   //inizialize the list with the seeds
for (String item : items) {

    String title="a new method for energy accounting";
    Articles a=new Articles(item,title,level,father);
  
    //if(listofArticle[d]==null){
       
     listofArticle[d] = a;
     System.out.println("prova1");
    //}
  
    
System.out.println("seme"+"  "+d+"  "  + item);

d++;
}
    
    
    
    
 //check the level
    for(int i=0;listofArticle[i]!=null;i++){
        
        //read the id and the level of article
       String source= listofArticle[i].getArticle_id();

      int l=listofArticle[i].getArticle_level();
        System.out.println("I'm analyzing:"+source +"  "+l+"("+i+")");
     
        if(l<r){
            
            level=l+1;
            //call the method extract_pages
            extract_pages(source,level,sleeper,pages);
           }//end of if
      }  //end of for
    
    }//end of method list scroll
   
  /*
   *method extract_pages
   *create the request for each pages
   * 
   * receive as parameters:
   * the source article
   * the level of the source
   * the interval between two request to the google server, in order to avoid 404,403,505 errors
   * the number of pages to be analized for each research
   * 
   */
    public static void extract_pages(String source, int level, int sleep,int pag) throws Exception,InterruptedException{
        int sleeper=sleep;
        int pages=pag;
        if (pages>100){pages=100;}
        if (pages==0){pages=1;}
        //would be 0<j<100
        for(int j=0;j<pages;j++){
            //create the url
            String url=(Url(source,j));
            try{
  //do what you want to do before sleeping
  Thread.currentThread().sleep(sleep);//sleep for 1000 ms
  //do what you want to do after sleeptig
}
catch(InterruptedException ie){
        
//If this thread was intrrupted by nother thread 
}   
            extract_source(url,level,source);
        }//end for
    }//end method extract_pages
    /*
     *method extract_source
     * launch the researches to the google server
     *receive as parameters:
     * url
     * the level of the source
     * the id of the source
     */
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
	
         //extract the source code lines
	        while ((inputLine = in.readLine()) != null){
                    
	           // System.out.println(inputLine);
                   
                    //call the method identificator
                   identificator(inputLine,level,source);
                   
                }
	        in.close();
                }
    
         /*
          *method identificator
          * extract from the source code the articles that cite the source article
          * 
          * receive as parameters:
          * the souce code lines
          * the level of the source article
          * the id of the source article
          */
  static String identificator(String input,int level,String source){
      
        Pattern pattern = Pattern.compile("cites=([^<]+)&amp;as_sdt");
       java.util.regex.Matcher matcher = pattern.matcher(input);
       Pattern patterntit = Pattern.compile("\">([^<]+)</a></h3><div");
       java.util.regex.Matcher matchertit = patterntit.matcher(input);
       //recognize the strings
       int i=0;
  while(matcher.find() && matchertit.find()&&i<2) {
      i++;//per la prova
    //System.out.println("Sottosequenza : "+matcher.group());
      String father=source;
      String title=matchertit.group(1);
      //String title="tiotlo";
      String id=matcher.group(1);
     
       Articles a =new Articles(id,title,level,father);
       int t=0;
       int g=0;
       //update the listofArticle with the new articles
      for(int h=0;g<1;h++){
      if(listofArticle[h]==null){
     listofArticle[h] = a;
     g++;
     t=h;
          }//end if
      
          }//end for
      String b=a.getArticle_id();
      int c=a.getArticle_level();
      String d=a.getid_father();
      System.out.println(b +"   level:"+c+"  ("+t+")  "+  i+"  "+       "       father:"+d+"\n");
      
  //   }//end if
      
     
            }
                return null;
            }
  /*method Url
   * create the url
   * 
   * receive as parameters:
   * id of the source article
   * the page of results to extract
   */
  public static String Url(String source, int j){
      
  String Url="http://scholar.google.it/scholar?"+j+"0&cites="+source;
  //http://scholar.google.it/scholar?start=00&cites=10291899245580779450
  return Url;
  }
   
        
       /*method generate
        * create nodes and edges
        * 
        */
         @Override
    public  void generate(ContainerLoader container)    {
           //14578813871626079601,14556101359633585337,1011810338192237073  
        
//853264223316211048,14578813871626079601,6207177028634780900
             // 7624327534192632700,11080283063311825032,505110406916258494
             


         String id_article="";
         String radius="";
         String sleeper="";
         String pages="";
         
         //create the panel to receive imputs
                 String seme="6207177028634780900";
      JTextField xField = new JTextField(seme);
      String rad="2";
      JTextField yField = new JTextField(rad);
      String wait="10000";
      JTextField waitingtime=new JTextField(wait);
      String npag="1";
      JTextField page=new JTextField(npag);
      JPanel myPanel = new JPanel();
      
      
      
      
      myPanel.add(new JLabel("articles ids(separated by commma without space):"));
      myPanel.add(xField);
      
      
      myPanel.add(new JLabel("radious:"));
      
      myPanel.add(yField);
      myPanel.add(new JLabel("Waiting time:"));
      myPanel.add(waitingtime);
      myPanel.add(new JLabel("# pages analized(1-100):"));
      myPanel.add(page);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Please, enter parameters", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         
         id_article=xField.getText();
         radius=yField.getText();
         sleeper=waitingtime.getText();
         pages=page.getText();
         
         
      }


  int r = Integer.parseInt(radius);
  int sleep=Integer.parseInt(sleeper);
  int pag=Integer.parseInt(pages);

        try {
            //call the method List_scroll
            List_scroll(r,id_article,sleep,pag);
         
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        //create nodes and create attributes
      for(int i=0;Articles.listofArticle[i]!=null;i++){
       if(Articles.listofArticle[i]!=null){
           if(Articles.listofArticle[i].getArticle_id()==null)
           {System.out.println("here is the problem"+i);}
           NodeDraft n1 = container.factory().newNodeDraft();
           n1.setLabel(Articles.listofArticle[i].getArticle_id());
           n1.setId(Articles.listofArticle[i].getArticle_id());
           int j=Articles.listofArticle[i].getArticle_level();
          int g =255-j*50;
           if(g<0){ g =0;}
           
           n1.setColor(0,g , 0);
           //853264223316211048
           AttributeColumn col = null;
        if ((col = container.getAttributeModel().getNodeTable().getColumn("level")) == null) {
            col = container.getAttributeModel().getNodeTable().addColumn("level", AttributeType.INT);
        }
           AttributeColumn colu = null;
        if ((colu = container.getAttributeModel().getNodeTable().getColumn("title")) == null) {
            colu = container.getAttributeModel().getNodeTable().addColumn("title", AttributeType.STRING);
        }
       //   AttributeColumn colu = container.getAttributeModel().getNodeTable().addColumn("title", AttributeType.STRING);
          n1.addAttributeValue(colu, Articles.listofArticle[i].getArticle_name());
           
          // AttributeColumn col = container.getAttributeModel().getNodeTable().addColumn("level", AttributeType.INT);
            n1.addAttributeValue(col, Articles.listofArticle[i].getArticle_level());
           
         //  n1.addAttributeValue(null, Articles.listofArticle[i].getArticle_name());   
           //n1.addAttributeValue(null, Articles.listofArticle[i].getArticle_level()); 
            AttributeColumn colum = null;
        if ((colum = container.getAttributeModel().getNodeTable().getColumn("status")) == null) {
            colum = container.getAttributeModel().getNodeTable().addColumn("status", AttributeType.STRING);
        }
       //   AttributeColumn colu = container.getAttributeModel().getNodeTable().addColumn("title", AttributeType.STRING);
        String status="";
        if(Articles.listofArticle[i].getArticle_level()==0)
            status="entry point";
        if(Articles.listofArticle[i].getArticle_level()==r)
            status="only cited";
        if(Articles.listofArticle[i].getArticle_level()>0&&Articles.listofArticle[i].getArticle_level()<r)
            status="processed";
        
          n1.addAttributeValue(colum, status);
            
            
            
            
          container.addNode(n1);
        System.out.println(i);
           
        }//end if
      }//end for
      
      int o=0;
      while(Articles.listofArticle[o].getArticle_level()==0){
      o++;
      }
      //create edges
       for(int i=o;Articles.listofArticle[i]!=null;i++){
       if(Articles.listofArticle[i]!=null){
          // NodeDraft n1 = container.factory().newNodeDraft();
          // n1.setLabel(Articles.listofArticle[i].getArticle_id());
           
         // container.addNode(n1);
         NodeDraft n2= container.getNode(Articles.listofArticle[i].getid_father());
         NodeDraft n1= container.getNode(Articles.listofArticle[i].getArticle_id());
       if(container.getEdge(n1, n2)==null){
          EdgeDraft e = container.factory().newEdgeDraft();
           e.setSource(n1);
            e.setTarget(n2);
            container.addEdge(e);
       }
        }//end if
      }//end for
      
    }//end of generate method
     
        
         @Override
   public GeneratorUI getUI() {
       return null;
   }

         @Override
         public void setProgressTicket(ProgressTicket progressTicket) {
        this.progress = progressTicket;
        }
          public boolean cancel() {
        cancel = true;
        return true;
    }
//yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
    @Override
    public String getName() {
        return " Scholar Cites Network";
    }

   
   public String nodes(){
        
   return null;
    }
  

   
    
}
