/**
 * @fileOverview 保存和更新的对话框
 * @ignore
 */
define('bui/ux/savedialog',['bui/common','bui/form','bui/overlay'],
		function (require) {
  var BUI = require('bui/common'),
    Overlay = require('bui/overlay'),
    Form = require('bui/form');
  /**
   * @class SaveDialog
   * 增删改查基本
   */
  function SaveDialog(config){
	  SaveDialog.superclass.constructor.call(this, config);
	  this._init();
  }

  SaveDialog.ATTRS = {
	entityName : {
		
	},
    formId : {
        value : 'form'
    },
    form :{
    	
    },
    formCfg:{
    	
    },
    submitUrl : {
    	add : null,
    	update : null
    },
    /**
     * 做update，当传入的record为且recordUrl不为空时空,会从后端加载记录并设置给Form
     */
    recordUrl : {
    	
    },
    /**
     * 增加和修改的对话框对应的DOM ID
     * @type {String}
     */
    dialogContentId : {
    	
    },
    /**
     * 增加和修改的对话框
     * @type {Object}
     */
    dialog : {
    	
    },
    dialogCfg : {
    	
    },
    saveType : {
    	
    },
    titlePrefix : {
    	value :{add : '新增',update :'修改'}
    },
    events : {
        value : [
           /**
            * 显示添加窗口前的事件
            * @event beforeAddShow
            *  @param {dialog} 新增和修改的窗口
            *  @param {Form} 新增和修改的Form
            */ 
            'beforeAddShow',
            /**
             * 显示修改窗口前的事件
             * @event beforeUpdateShow
             *  @param {dialog} dialog 新增和修改的窗口
             *  @param {Form}  form 新增和修改的Form
             *  @param {Object} record 修改的列的数据
             */ 
            'beforeUpdateShow',
            
            /**
             * 提交form 之前的事件
             * @event beforeSubmit
             *  @param {dialog} dialog 新增和修改的窗口
             *  @param {Form}  form 新增和修改的Form
             */ 
            'beforeSubmit',
            
            /**
             * 显示修改窗口前的事件
             * @event beforeSave
             *  @param {data} 返回的结果
             *  @param {dialog} dialog 新增和修改的窗口
             *  @param {Form}  form 新增和修改的Form
             */ 
            'afterSubmit'
        ]
    }
  };

  BUI.extend(SaveDialog,BUI.Base);

  BUI.augment(SaveDialog,{
    _init : function(){
      var _self = this;
      _self._initForm();
      _self._initdialog();
      //_self.set('self',_self);
    },
    //初始化Searchform
    _initForm : function(){
      var _self = this,
      form = _self.get('form');
      if(!form){
        var formCfg = BUI.merge(_self.get('formCfg'),{
          srcNode : '#' + _self.get('formId'),
          method:'post'
        });
        form = new BUI.Form.HForm(formCfg);
        form.render();
        _self.set('form',form);
      }
    },
    _initdialog : function(){
    	var _self = this,
    	form = _self.get('form');
    	dialog = _self.get('dialog');
    	if(!dialog){
    		var dialogContentId = _self.get('dialog'),
    		dialogContentId = _self.get('dialogContentId'),
    			entityName = _self.get('entityName'),
    			dialogCfg = _self.get('dialogCfg'),
    			saveType = _self.get('saveType');
    		var successCallBack = function () {
    			var saveType = _self.set('saveType');
    			var result = _self.fire('beforeSubmit',dialog,form,saveType);
    			if(typeof(result) != "undefined" && !result){
    				return false;
    			}
    			form.valid();
               	 if(form.isValid()){
               		 form.ajaxSubmit({
               			 success :function(data){
               				var saveType = _self.set('saveType');
               				var result = _self.fire('afterSubmit',data,dialog,form,saveType);
               				if(typeof(result) == "undefined" || result){
               					if(data.success){
                       	  	 		BUI.Message.Alert("操作成功！",'success');
                              		 form.clearFields();
                               		 dialog.close();
                       	  		}else{
                       			 BUI.Message.Alert("操作失败："+data.msg,'warning');
                       		 }
						   }
               			}
					 });
               	 }
            };
            var cancel = function(){
           		form.clearFields();
           };
           var cfg = {
                   title:'新增'+entityName,
                   //width:500,
                   //height:320,
                   //配置DOM容器的编号
                   contentId: dialogContentId,
                   success: successCallBack,
                   cancel : cancel
               };
           
           cfg = BUI.merge(cfg,dialogCfg);
           
            dialog = new BUI.Overlay.Dialog(cfg);
            dialog.on('show',function(){
            	form.clearErrors();
            });
          _self.set('dialog',dialog);
    	}
    },
    add : function(){
    	var _self = this,
    		dialog = _self.get('dialog'),
    		form = _self.get('form'),
    		url = _self.get('url'),
    		titlePrefix = _self.get('titlePrefix'),
    		entityName = _self.get('entityName');
    	var prefix = titlePrefix['add'] ? titlePrefix['add']:'';
    	_self.set('saveType','add');
    	dialog.set('title',prefix+entityName);
    	form.set('action',url['add']);
        /**
         * 显示添加窗口前的事件
         * @event beforeAddShow
         */ 
    	_self.fire('beforeAddShow',dialog,form);
    	dialog.show();
    },
    /**
     * record 传入的内存中需要修改的对象
     * loadParams 当record为空且Savedialog的recordUrl不为空时，则会使用loadParams去远端加载数据
     */
    update : function(record,loadParams){
    	var _self = this,
    		dialog = _self.get('dialog'),
    		form = _self.get('form'),
    		url = _self.get('url'),
    		titlePrefix = _self.get('titlePrefix'),
    		entityName = _self.get('entityName');
    	var prefix = titlePrefix['update'] ? titlePrefix['update']:'';
    	_self.set('saveType','update');
    	dialog.set('title',prefix+entityName);
    	form.set('action',url['update']);
    	if(record){
    		form.setRecord(record);
    	}else{
    		var recordUrl = _self.get('recordUrl');
    		if(recordUrl){
    			$.getJSON(recordUrl, loadParams, function(record){
    				form.setRecord(record);
    		        /**
    		         * 显示添加窗口前的事件
    		         * @event beforeAddShow
    		         */ 
    		    	_self.fire('beforeUpdateShow',dialog,form,record);
    		    	dialog.show();
    			});
    			return;
    		}
    	}
    	
        /**
         * 显示添加窗口前的事件
         * @event beforeAddShow
         */ 
    	_self.fire('beforeUpdateShow',dialog,form,record);
    	dialog.show();
    }
  });
  return SaveDialog;
});
  