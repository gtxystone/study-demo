<!DOCTYPE HTML>
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
 <head>
  <title>${sessionScope.logon_user.systemName}</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <%@include file="../../common/import-tags.jsp"%>
   <%@include file="../../common/import-static.jsp"%>
 </head>
 <body >

  
  <div class="container">
     <div class="row">
      <div class="span6">
        <div class="panel">
        <form id = "tree">
          <div class="panel-header clearfix">
            <!--  <h3 class="pull-left">资源</h3> -->
            <div id="tbar" class="pull-right"></div>
          </div>
          <div id="t1">
          </div>
          </form> 
        </div>
      </div>
      <div>
        <div style="overflow: hidden;padding-left: 5px;">
          <div id="grid">
          </div>
        </div>
      </div>
          <!-- 修改新增 -->
   <div id="addOrUpdate" class="hide">
      <form id="addOrUpdateForm" class="form-horizontal">
        <div class="row">
          <div class="control-group span8">
            <label class="control-label"><s>*</s>部门名称</label>
            <div class="controls">
              <input name="name" type="text" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
         
        </div>
     
        <div class="row">
          <div class="control-group span8">
            <label class="control-label">编号</label>
            <div class="controls">
              <input name="code" type="text" data-rules="{required:true}" class="input-normal control-text">
            </div>
          </div>
          <div class="control-group span10">
            <label class="control-label">上级部门</label>
              <input id="parent_name" type="text" data-rules="" class="input-normal control-text">
              <input name="parent_id" type="hidden" data-rules="" class="input-normal control-text">
            </div>
          </div>
        </div>
        <input type="hidden" name="id" value="">
      </form>
    </div>
  </div>
  </div>
<script type="text/javascript">
BUI.use(['bui/tree','bui/data','bui/toolbar','bui/select','bui/ux/crudgrid','bui/mask','bui/common/page'],
		function (Tree,Data,Toolbar,Select,CrudGrid,Mask) {
	
	
	//定义页面权限
	var add=false,update=false,del=false,list=false;
	//"framwork:crudPermission"会根据用户的权限给add，update，del,list赋值
	<framwork:crudPermission resource="/admin/sys/ou"/>

			var level = 1;//添加资源时需要使用到
			var parentId = 0;
			var enumPermission = {};

			
			//权限选择框
	       /* 	var selectStore = new Data.Store({
	        	url : '${ctx}/admin/sys/permission/permissionCheckList',
	        	autoLoad : true
	      	});
 	       	selectStore.on('load',function(e){
	       		var result = selectStore.getResult();
	            BUI.each(result,function(item){
	            	enumPermission[item.value] = item.text;
	              });
	       	}); 
	      	var select = new Select.Select({  
	        	render:'#permissionList',
	        	valueField:'#permissionIds',
	        	multipleSelect : true,
	        	store : selectStore
	      	});
	      	select.render(); */
			
		     //资源树开始
	        //一次性异步加载所有数据
	        var treeStore = new Data.TreeStore({
	            url : '${ctx}/admin/sys/ou/ouTree', 
	            autoLoad : true,
	    	    root : {                  //由于要显示根节点，所以需要配置根节点
	    	       id : '0',
	    	       text : '资源列表',
	    	       expanded : true,
	    	       cls : 'icon-home'
	    	     }
	        });
	        
	    	 //由于这个树，不显示根节点，所以可以不指定根节点
	    	 var tree = new Tree.TreeList({
	    		 //itemTpl : '<li id="{value}">{text}</li>',
	    	     render : '#t1',
	    	     store : treeStore,
	    	     showLine : true,
	    	     height: BUI.viewportHeight()-90,
	    	     dirCls : 'icon-tasks',
	    	     leafCls : 'icon-leaf',
	    	     showLine : true, //显示连接线
	    	     showRoot : true,
	    	     loadMask : new Mask.LoadMask({el : '#t1'})
	    	   });
	    	 tree.render();
	         
	         
	        tree.on('selectedchange',function(ev){
	        	var node = ev.item;
	        	//store.set('lastParams',{});
	        	//store.load({'search_LIKE_code' : node.code,start : 0});
	        	
	        	/* level = node.level+1;
	           	parentId = node.id;
		        if(node.level==0){
		       		store.set('lastParams',{});//清空上次的参数
		        	store.load({search_EQ_level : 1,start : 0});
		            //加载对应的数据，同时将分页回复到第一页
		        }else if(node.level==1 || node.level==2){
		        	store.set('lastParams',{});
		        	store.load({'search_EQ_parent.id' : node.id,start : 0});
		         }else if(node.level==3){
		        	store.set('lastParams',{});
		        	store.load({'search_EQ_id' : node.id,start : 0});
		         } */
	        });
	        
	        //树上方按键
	        var bar = new Toolbar.Bar({
	            render : '#tbar',
	            elCls : 'button-group',
	            children : [{
	                elCls : 'button button-small button-primary',
	                content : '<i class="icon-refresh icon-white"></i>刷新',
	                elAttrs:{title : '刷新'},
	                handler : function(){
	                	treeStore.load();
	                }
	              },
	              {
	            	  elCls : 'button button-small button-primary',
	            	  content : '<i class="icon-plus icon-white"></i>展开',
	            	  elAttrs:{title : '展开'},
	            	  handler : function(){
	            		  tree.expandAll();
	            	}
	              },
	              {
	            	  elCls : 'button button-small button-primary',
	            	  content : '<i class="icon-minus icon-white"></i>收缩',
	            	  elAttrs:{title : '收缩'},
	            	  handler : function(){
	            		  tree.collapseAll();
	            	}
	              }
	            ]
	         });
	        bar.render();
	          
	       
});
 
</script>
 
</body>
</html>
