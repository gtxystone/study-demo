/**
 * @author 
 * @version 1.0
 * @since  2015-11-16 14:22:32
 * @desc Ou
 */

package com.yshanginfo.framwork.sys.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yshanginfo.framwork.commom.bui.vo.TreeItem;
import com.yshanginfo.framwork.core.repository.BaseJapRepository;
import com.yshanginfo.framwork.core.service.BaseService;
import com.yshanginfo.framwork.sys.entity.Ou;
import com.yshanginfo.framwork.sys.repository.OuRepository;


@Component
@Transactional
public class OuService extends BaseService<Ou, java.lang.String> {
	@Autowired
	private OuRepository ouRepository;
	
	@Override
	public BaseJapRepository<Ou, java.lang.String> getRepository() {
		return this.ouRepository;
	}
	
	
	public List<TreeItem> getOuList(){
		/*List<Resource> resources = this.ouRepository.findByLevel(1);
		List<TreeItem> trees = new ArrayList<TreeItem>(resources.size());
		for(Resource resource : resources){
			trees.add(this.resourceToTree(resource));
		}
		return trees;*/
		
		List<Ou> list = ouRepository.findTree();
		List<TreeItem> trees = new ArrayList<TreeItem>(list.size());
		
		
		
		return trees;
		
	}
}
