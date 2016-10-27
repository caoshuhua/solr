package com.solr.test;  
  
  
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
  
public class InsertProgarm {  
    //solr 服务器地址  
    public static final String solrServerUrl = "http://localhost:8080/solr";  
    //solrhome下的core  
    public static final String solrCroeHome = "test";  
    //待索引、查询字段  
    public static String[] docs = {"java",  
                                    "hadoop",  
                                    "c",  
                                     "dd",  
                                    "ee",  
                                    "gg"};  
    public static void main(String[] args) {  
        SolrClient client = getSolrClient(); 
        int i=0;  
        List<SolrInputDocument> solrDocs = new ArrayList<SolrInputDocument>();  
        for (String content : docs) {  
            SolrInputDocument doc = new SolrInputDocument();  
            doc.addField("id", i++);  
            doc.addField("name", content);
            solrDocs.add(doc);  
        }  
        try {  
            client.add(solrDocs);  
            client.commit();  
        } catch (SolrServerException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
    }  
    public static SolrClient getSolrClient(){  
        return new HttpSolrClient(solrServerUrl+"/"+solrCroeHome);  
    }  
  
}  