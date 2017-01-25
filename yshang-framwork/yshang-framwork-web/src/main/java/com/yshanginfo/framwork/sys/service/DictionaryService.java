package com.yshanginfo.framwork.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yshanginfo.framwork.commom.bui.vo.ListItem;
import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.core.service.BaseService;
import com.yshanginfo.framwork.sys.entity.Dictionary;
import com.yshanginfo.framwork.sys.repository.DictionaryRepository;


@Component
@Transactional
public class DictionaryService extends BaseService<Dictionary, String>{
	
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	public List<ListItem> findByDictinaryTypeCode(String dictinaryTypeCode){
		List<Dictionary> list = this.dictionaryRepository.findByDictionaryTypeCode(dictinaryTypeCode);
		List<ListItem> items = new ArrayList<ListItem>(list.size());
		for(Dictionary dictionary : list){
			ListItem item = new ListItem(dictionary.getName(), dictionary.getValue());
			items.add(item);
		}
		dictionaryRepository.save(new Dictionary());
		return items;
		
	}
	
	public List<ListItem> searchListItem(Map<String, Object> searchParams, Sort sort){
		return this.searchListItem(searchParams, sort, null, null);
	}
	
	public Map<String, String> searchEnum(Map<String, Object> searchParams){
		List<Dictionary> list = this.search(searchParams);
		Map<String, String> map = new HashMap<String, String>(list.size());
		for(Dictionary dic : list){
			map.put(dic.getValue(), dic.getName());
		}
		return map;
	}
	
	public List<ListItem> searchListItem(Map<String, Object> searchParams, Sort sort, String headText,
			String headValue){
		List<Dictionary> list = this.search(searchParams, sort);
		List<ListItem> items = new ArrayList<ListItem>(list.size()+1);
		if(StringUtils.isNotBlank(headText)){
			ListItem item = new ListItem(headText, headValue);
			items.add(item);
		}
		for(Dictionary dictionary : list){
			ListItem item = new ListItem(dictionary.getName(), dictionary.getValue());
			items.add(item);
		}
		return items;
	}

	@Override
	public BaseJapRepository<Dictionary, String> getRepository() {
		return this.dictionaryRepository;
	}

}
