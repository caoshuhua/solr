package com.solr.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;

public class SolrUtils {
	
	public static final String solrUrl = "http://localhost:8080/solr";
	
	public static final String solrName = "my_solr";

	public static void main(String[] args) {
		SolrUtils test = new SolrUtils();
//		test.query("hadoop");
		test.addDoc();
		
	}
	
	public void query(String query) {
		SolrClient server = new HttpSolrClient(solrUrl+"/"+solrName);  
        SolrParams params = new SolrQuery(query);
        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
	
	public final void fail(Object o) {
        System.out.println(o);
    }
	
	public void addDoc() {
		SolrClient server = new HttpSolrClient(solrUrl+"/"+solrName);  
	    //创建doc文档
	    SolrInputDocument doc = new SolrInputDocument();
	    doc.addField("id", 11);
	    doc.addField("name", "this is SolrInputDocument content");
	    doc.addField("manu", "test");
	    
	    try {
	        //添加一个doc文档
	        UpdateResponse response = server.add(doc);
	        server.commit();//commit后才保存到索引库
	        fail(response);
	        fail("query time：" + response.getQTime());
	        fail("Elapsed Time：" + response.getElapsedTime());
	        fail("status：" + response.getStatus());
	    } catch (SolrServerException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    query("name:this");
	}
 
}
