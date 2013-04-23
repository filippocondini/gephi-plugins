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
import java.awt.*;

import org.gephi.data.attributes.api.*;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.*;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.statistics.spi.*;
import org.openide.util.Exceptions;
import org.gephi.dynamic.api.DynamicModel;
import org.openide.util.Lookup;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.text.JTextComponent;
import org.gephi.io.generator.spi.Generator;
import org.gephi.io.generator.spi.GeneratorUI;
import org.gephi.io.importer.api.ContainerLoader;
import org.gephi.io.importer.api.EdgeDraft;
import org.gephi.io.importer.api.NodeDraft;
//import org.gephi.utils.progress.Progress;
//import org.gephi.utils.progress.ProgressTicket;
import org.jdesktop.swingx.autocomplete.TextComponentAdaptor;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
@ServiceProvider(service = Generator.class)
/**
 *
 * @author Administrator
 */
public class Gen implements Generator/*,Statistics*/{
      protected ProgressTicket progress;
    protected boolean cancel = false;
public static Articles listofArticle[]=new Articles[100000];
//public static String net="";
//protected int numberOfNodes = 50;
 //   protected double wiringProbability = 0.05;
   

   
    public static void List_scroll(int radius,String id_article) throws Exception {
    //Articles listofArticle[]=new Articles[10000];
    //int r=2;
    int r=radius;
    //String identificator="853264223316211048,10118837676854620386";
    
    String[] items = id_article.split(",");
int d=0;

String father=null;
    int level=0;
for (String item : items) {

//System.out.println("item = " + item);


//String identificator=item;
    String title="a new method for energy accounting";
    
    
    Articles a=new Articles(item,title,level,father);
    if(listofArticle[d]==null){
     listofArticle[d] = a;
    }
System.out.println("seme"+"  "+d+"  "  + item);
d++;
}
    
    
    
    
 /*   String identificator=id_article;
    String title="a new method for energy accounting";
    String father=null;
    int level=0;
    
    Articles a=new Articles(identificator,title,level,father);
    if(listofArticle[0]==null){
     listofArticle[0] = a;
    }*/
    
    //net=net+a.getArticle_id()+a.getArticle_level()+"\n";
 
            
    for(int i=0;listofArticle[i]!=null;i++){
        
        //read the id and the level of article
       String source= listofArticle[i].getArticle_id();

      int l=listofArticle[i].getArticle_level();
        System.out.println("I'm analyzing:"+source +"  "+l+"("+i+")");
     //   net=net + "analizzo questo:"+source +"  "+l+"\n";
        if(l<r){
            
            level=l+1;
            //chiama il methodo scorri le pagine
            extract_pages(source,level);
           }//end of if
      }  //end of for
    
    }//end of method list scroll
   
  
    ///da qui in poi scrivo tutti i cazzo di metodi
    public static void extract_pages(String source, int level) throws Exception,InterruptedException{
        
        //would be j<100
        for(int j=0;j<1;j++){
            
            String url=(Url(source,j));
            try{
  //do what you want to do before sleeping
  Thread.currentThread().sleep(20000);//sleep for 1000 ms
  //do what you want to do after sleeptig
}
catch(InterruptedException ie){
        
//If this thread was intrrupted by nother thread 
}   
            extract_source(url,level,source);
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
       Pattern patterntit = Pattern.compile("\">([^<]+)</a></h3><div");
       java.util.regex.Matcher matchertit = patterntit.matcher(input);
       int i=0;//per la prova
  while(matcher.find() && matchertit.find()&&i<2) {
      i++;//per la prova
    //System.out.println("Sottosequenza : "+matcher.group());
      String father=source;
      String title=matchertit.group(1);
      //String title="tiotlo";
      String id=matcher.group(1);
     
      
  // int t=0;
   //  for(int s=0;listofArticle[s]!=null;s++){
   //  System.out.println("lunghezza  "+listofArticle.length);    
   // System.out.println("id"+id);
   // System.out.println("param"+ s+" "+listofArticle[s].getArticle_id());
   //  if( id.equals(listofArticle[s].getArticle_id())){
   //  t++;
   //  }  
   //  }
     
    // if(t==0){
       Articles a =new Articles(id,title,level,father);
       int t=0;
       int g=0;
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
      System.out.println(b +"   level:"+c+"  ("+t+")  "+         "       father:"+d+"\n");
      
  //   }//end if
      
      //net=net + b +"   level:"+c+"       father:"+d+"\n";
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
   
        
       
         @Override
    public  void generate(ContainerLoader container)    {
           //14578813871626079601,14556101359633585337,1011810338192237073  
        

//String id_article = JOptionPane.showInputDialog(null, "Insert the id of the article", "Article id", JOptionPane.PLAIN_MESSAGE);
//String radius = JOptionPane.showInputDialog(null, "Insert the radious", "Radious", JOptionPane.PLAIN_MESSAGE);

         String id_article="";
         String radius="";
                 
JTextField xField = new JTextField(20);
      JTextField yField = new JTextField(2);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Insert the id of the articles:"));
      myPanel.add(xField);
      myPanel.add(Box.createHorizontalStrut(20)); // a spacer
      myPanel.add(new JLabel("Insert the radious:"));
      myPanel.add(yField);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         
         id_article=xField.getText();
         radius=yField.getText();
         //System.out.println(a);
         //System.out.println(b);
      }

//String Stringa = "1, 2, 3, 4, 5, 6";
//Numeri = Split(Stringa, ",");

//String data = "1,Diego Maradona,Calciatore,Argentina";



//JPanel panel = new JPanel();

//int conferma = JOptionPane.showConfirmDialog(null, "Seleziona sì, no o cancella", "Scegli un'opzione", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//String output = "Hai scritto: \"" + messaggio + "\" ed hai cliccato sul pulsante: " + conferma;
//JOptionPane.showMessageDialog(null, output);
//System.exit(0);
  int r = Integer.parseInt(radius);

        try {
            List_scroll(r,id_article);
         
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        
      for(int i=0;Articles.listofArticle[i]!=null;i++){
       if(Articles.listofArticle[i]!=null){
           if(Articles.listofArticle[i].getArticle_id()==null)
           {System.out.println("cazzo qui + il problema  "+i);}
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
       for(int i=o;Articles.listofArticle[i]!=null;i++){
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
      
        }//end if
      }//end for
      
    }//end of generate method
     
         //yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
         @Override
   public GeneratorUI getUI() {
       return null;
   }
//@Override
 //   public GeneratorUI getUI() {
  //      return Lookup.getDefault().lookup(UI.class);
  //  }


 /*public void setNumberOfNodes(int numberOfNodes) {
        if (numberOfNodes < 0) {
            throw new IllegalArgumentException("# of nodes must be greater than 0");
        }
        this.numberOfNodes = numberOfNodes;
    }

    public void setWiringProbability(double wiringProbability) {
        if (wiringProbability < 0 || wiringProbability > 1) {
            throw new IllegalArgumentException("Wiring probability must be between 0 and 1");
        }
        this.wiringProbability = wiringProbability;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public double getWiringProbability() {
        return wiringProbability;
    }
 
    */
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
    // @Override

   
    
}
