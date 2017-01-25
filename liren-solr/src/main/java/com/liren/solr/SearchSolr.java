package com.liren.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SearchSolr {

	// solr 服务器地址
	public static final String solrServerUrl = "http://localhost:8080/solr";
	// solrhome下的core
	public static final String solrCroeHome = "my_solr";

	public static void main(String[] args) throws SolrServerException, IOException {

		SolrClient client = getSolrClient();

		SolrQuery sQuery = new SolrQuery();
		StringBuilder param = new StringBuilder("*:*");
		sQuery.addFacetQuery(param.toString());
		sQuery.addField("id");
		sQuery.setStart(0);
		sQuery.setRows(10);
		sQuery.addSort("id", SolrQuery.ORDER.desc);

		QueryResponse query = client.query(sQuery);
		SolrDocumentList list = query.getResults();
		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

	@SuppressWarnings("deprecation")
	public static SolrClient getSolrClient() {
		return new HttpSolrClient(solrServerUrl + "/" + solrCroeHome);
	}

}
