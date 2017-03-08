package com.liren.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CloudConnectionUtils2 {
	
	public static String[] docs = { "Solr是一个独立的企业级搜索应用服务器", "它对外提供类似于Web-service的API接口", "用户可以通过http请求",
			"向搜索引擎服务器提交一定格式的XML文件生成索引", "也可以通过Http Get操作提出查找请求", "并得到XML格式的返回结果" };
	
	private final static String DEFAULT_COL  = "id,starId,starName,fanId,title,content,postUserType, publishTime,isTop,isReport,isAnonymous,isRecommend,recommendDate,startIndex,isCream,isDelete,ringId,stationId,praiseNum,commentNum";
	
	public static void main(String[] args) {
		int i = 0;
		List<SolrInputDocument> sids = new ArrayList<SolrInputDocument>();
		CloudSolrClient solrClient = new CloudSolrClient.Builder().withZkHost("192.168.1.204:2181").build();
		solrClient.setDefaultCollection("posts");
		
		SolrQuery sQuery = new SolrQuery();
		sQuery.setFields(DEFAULT_COL);
		StringBuilder param = new StringBuilder("+content:目睹 +title:他不不怕");
	
		
		sQuery.setQuery(param.toString());
		sQuery.setStart(0);
		sQuery.setRows(100);
		
		
		try {
			QueryResponse result = solrClient.query(sQuery);
			List<FanPostsBo> list = result.getBeans(FanPostsBo.class);
			if(list != null){
				System.out.println(list.size());
				ObjectMapper mapper = new ObjectMapper();  
//				for(FanPostsBo fb:list){
//					mapper.writeValueAsString(fb);
//				}
				
				System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
				
//				System.out.println(mapper.writeValueAsString(list));
				
			}else{
				System.out.println("-------------");
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (String content : docs) {
//			SolrInputDocument doc = new SolrInputDocument();
//			doc.addField("id", i++);
//			doc.addField("content_test", content);					
//			sids.add(doc);
//		}
//		try {
//			solrClient.add("abcd", sids);
//			solrClient.commit();
//		} catch (SolrServerException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
	

}
