/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mynet.netpro;

/**
 *
 * @author Administrator
 */
public class Articles extends Gen{
    private String article_id;
	private int article_level;
	private String article_name;
        private String id_father;
	
	public Articles(String id, String n, int l,String f){
		
        article_id=id;
        article_name=n;
	article_level=l;
        id_father=f;
	
	}
        //  Getters and setters
	public String getArticle_id(){
		return article_id;
	}
	
	public void setArticle_id(String id){
		article_id = id;
	}

	public String getArticle_name() {
		return article_name;
	}

	public void setArticle_name(String article_name) {
		this.article_name = article_name;
	}

	public int getArticle_level() {
		return article_level;
	}

	public void setArticle_level(int article_level) {
		this.article_level = article_level;
	}
        public String getid_father() {
		return id_father;
	}

	public void setid_father(String id_father) {
		this.id_father = id_father;
	}
}
