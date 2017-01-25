package com.liren.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class CloudConnectionUtils {
	
	public static String[] docs = { "Solr是一个独立的企业级搜索应用服务器", "它对外提供类似于Web-service的API接口", "用户可以通过http请求",
			"向搜索引擎服务器提交一定格式的XML文件生成索引", "也可以通过Http Get操作提出查找请求", "并得到XML格式的返回结果" };
	
	public static void main(String[] args) {
		int i = 0;
		List<SolrInputDocument> sids = new ArrayList<SolrInputDocument>();
		CloudSolrClient solrClient = new CloudSolrClient.Builder().withZkHost("192.168.1.116:2181").build();
		solrClient.setDefaultCollection("abcd");
		for (String content : docs) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", i++);
			doc.addField("content_test", content);					
			sids.add(doc);
		}
		try {
			solrClient.add("abcd", sids);
			solrClient.commit();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
