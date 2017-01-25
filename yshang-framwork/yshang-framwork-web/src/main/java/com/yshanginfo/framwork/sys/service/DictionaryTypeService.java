package com.yshanginfo.framwork.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yshanginfo.framwork.commom.bui.vo.ListItem;
import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.core.service.BaseService;
import com.yshanginfo.framwork.sys.entity.DictionaryType;
import com.yshanginfo.framwork.sys.repository.DictionaryTypeRepository;

@Component
@Transactional
public class DictionaryTypeService extends BaseService<DictionaryType, String> {

	@Autowired
	private DictionaryTypeRepository dictionaryTypeRepository;

	public List<ListItem> findAllItems() {
		List<DictionaryType> list = this.dictionaryTypeRepository.findAll();
		List<ListItem> items = new ArrayList<ListItem>(list.size());
		for (DictionaryType dictionaryType : list) {
			ListItem item = new ListItem(dictionaryType.getName(),
					String.valueOf(dictionaryType.getId()));
			items.add(item);
		}
		return items;
	}

	@Override
	public BaseJapRepository<DictionaryType, String> getRepository() {
		return this.dictionaryTypeRepository;
	}

}
