(function($,undefinded){
	/*
   	 * cookie插件
   	 */  
   $.cookie = function(name, value, options) {
        if ( typeof value != 'undefined') {// name and value given, set cookie
            options = options || {};
            if (value === null) {
                value = '';
                options.expires = -1;
            }
            var expires = '';
            if (options.expires && ( typeof options.expires == 'number' || options.expires.toUTCString)) {
                var date;
                if ( typeof options.expires == 'number') {
                    date = new Date();
                    date.setTime(date.getTime() + (options.expires * 60 * 1000));
                } else {
                    date = options.expires;
                }
                expires = '; expires=' + date.toUTCString();
                // use expires attribute, max-age is not supported by IE
            }
            var path = options.path ? '; path=' + options.path : '';
            var domain = options.domain ? '; domain=' + options.domain : '';
            var secure = options.secure ? '; secure' : '';
            document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
        } else {// only name given, get cookie
            var cookieValue = null;
            if (document.cookie && document.cookie != '') {
                var cookies = document.cookie.split(';');
                for (var i = 0; i < cookies.length; i++) {
                    var cookie = jQuery.trim(cookies[i]);
                    // Does this cookie string begin with the name we want?
                    if (cookie.substring(0, name.length + 1) == (name + '=')) {
                        cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                        break;
                    }
                }
            }
            return cookieValue;
        }
    };
  	/*
   	 * 提示框插件
   	 * .eg :  1.$.msg('ccc')
   	 * 			--弹出提示框，会自动关闭
   	 *        2.$.msg({text:'ccc'}).done(function(){console.log('done')}).fail(function(){console.log('fail')})
   	 *        	--弹出提示框，需手动确认/取消
   	 *        2.$.msg({text:'ccc',nocancel:true}).done(function(){console.log('done')})
   	 *        	--弹出提示框，需手动确认
   	 */  
    $.msg = function(options){
    	var width = (typeof window.$analog_mobile=='boolean' && window.$analog_mobile)?"620px":"320px";
        var self = arguments.callee, msg, defaultOptions={"modal" : false, "width" : width,"closeOnEscape" : true};
        if(!self.uiDialog){
	        self.uiDialog = $('<div title="提示"></div>').appendTo('body');
	        self.uiDialog.dialog({autoOpen : false });
        }
        clearTimeout(self._timer);
        self.uiDialog.dialog("close");
        if(typeof options === 'string'){
            msg = options;
            options = $.extend({},defaultOptions,{
                "open" : function(event, ui) {
                    self._timer = setTimeout(function(){
                        self.uiDialog.dialog('close');
                    },3000);
                },
                "buttons" : {}
            });
        }else if(typeof options === 'object'){
			self.callback = $.Deferred();
            msg = options["text"];
			$.extend(options,defaultOptions);
            if(typeof options["nocancel"]==="boolean" && options["nocancel"]){
 				options["buttons"] = {
					"确定" : function(){ $( this ).dialog( "close" ); self.callback.resolveWith(options);}
                };
			}else{
				options["buttons"] = {
					"确定" : function(){ $( this ).dialog( "close" ); self.callback.resolveWith(options);},
					"取消" : function(){ $( this ).dialog( "close" ); self.callback.rejectWith(options);}
                };
			}
        }
        self.uiDialog.html('<p>'+ msg +'</p>');
        self.uiDialog.dialog("option",options).dialog('open');
        if(self.callback){
        	return self.callback.promise();
        }else{
        	return false;
        }
    };
    /*
   	 * 提示框插件
   	 *        2.$.msg({text:'ccc'}).done(function(){console.log('done')}).fail(function(){console.log('fail')})
   	 *        	--弹出提示框，需手动确认/取消
   	 *        2.$.msg({text:'ccc',nocancel:true}).done(function(){console.log('done')})
   	 *        	--弹出提示框，需手动确认
   	 */  
    $.msgNotTimer = function(options){
    	var width = (typeof window.$analog_mobile=='boolean' && window.$analog_mobile)?"620px":"320px";
        var self = arguments.callee, msg, defaultOptions={"modal" : false, "width" : width,"closeOnEscape" : true};
        if(!self.uiDialog){
	        self.uiDialog = $('<div title="提示"></div>').appendTo('body');
	        self.uiDialog.dialog({autoOpen : false });
        }
        self.uiDialog.dialog("close");
        if(typeof options === 'object'){
			self.callback = $.Deferred();
            msg = options["text"];
			$.extend(options,defaultOptions);
            if(typeof options["nocancel"]==="boolean" && options["nocancel"]){
 				options["buttons"] = {
					"确定" : function(){ $( this ).dialog( "close" ); self.callback.resolveWith(options);}
                };
			}else{
				options["buttons"] = {
					"确定" : function(){ $( this ).dialog( "close" ); self.callback.resolveWith(options);},
					"取消" : function(){ $( this ).dialog( "close" ); self.callback.rejectWith(options);}
                };
			}
        }
        self.uiDialog.html('<p>'+ msg +'</p>');
        self.uiDialog.dialog("option",options).dialog('open');
        if(self.callback){
        	return self.callback.promise();
        }else{
        	return false;
        }
    };
    
    //全角字符转为半角
	$.fullToDBC = function (str) {
		var DBCStr = "";
		for(var i = 0; i < str.length; i++){
			var c = str.charCodeAt(i);
			if(c == 12288){
				DBCStr += String.fromCharCode(32);
				continue;
			}
			if(c > 65280 && c < 65375){
				DBCStr += String.fromCharCode(c - 65248);
				continue;
			}
			DBCStr += String.fromCharCode(c);
		}
		return DBCStr.toUpperCase();
	};
	
	/*
   	 * 详情提示插件
   	 */  
	$.fn.detailTip = function(){
		var atitle = $(this).attr( "atitle" ) || "";
		if(atitle!=''){
			$.msg({text:atitle,nocancel:true});
		}
	};
	
	/*
   	 * 身份证校验器插件
   	 * .eg :  1.$.IDCardChecker(idCardNumer).done(function(){console.log('校验成功')}).fail(function(error){console.log(error)})
   	 */  
	$.IDCardChecker = function(idCardNumer) {
		var dfd = $.Deferred();
		var idCardNumer = $.trim(idCardNumer);
		if (15 != idCardNumer.length && 18 != idCardNumer.length) {
			dfd.reject("身份证(长度)输入有误，请检查");
		}
		if (15 == idCardNumer.length) {
			var year = idCardNumer.substring(6, 8);
			var month = idCardNumer.substring(8, 10);
			var day = idCardNumer.substring(10, 12);
			var p = idCardNumer.substring(14, 15); // 性别位
			var birthday = new Date(year, parseFloat(month) - 1, parseFloat(day));
			if (birthday.getYear() != parseFloat(year) || birthday.getMonth() != parseFloat(month) - 1 || birthday.getDate() != parseFloat(day)) {
				dfd.reject("身份证(出生年月)输入有误，请检查");
			} else {
				dfd.resolve();
			}
		}
		if (18 == idCardNumer.length) {
			var year = idCardNumer.substring(6, 10);
			var month = idCardNumer.substring(10, 12);
			var day = idCardNumer.substring(12, 14);
			var p = idCardNumer.substring(14, 17);
			var birthday = new Date(year, parseFloat(month) - 1, parseFloat(day));
			if (birthday.getFullYear() != parseFloat(year) || birthday.getMonth() != parseFloat(month) - 1 || birthday.getDate() != parseFloat(day)) {
				dfd.reject("身份证(出生年月)输入有误，请检查");
			}
			var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子
			var Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X
			// 验证校验位
			var sum = 0; // 声明加权求和变量
			var _idCardNumer = idCardNumer.split("");
			if (_idCardNumer[17].toLowerCase() == 'x') {
				_idCardNumer[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
			}
			for ( var i = 0; i < 17; i++) {
				sum += Wi[i] * _idCardNumer[i];// 加权求和
			}
			var i = sum % 11;// 得到验证码所位置
			if (_idCardNumer[17] != Y[i]) {
				dfd.reject("身份证(验证码)输入有误，请检查");
			}else{
				dfd.resolve();
			}
		}
		return dfd.promise();
	};
	
	/** 简单form提交插件 */  
    $.simpleFormSubmit = function(url,data){
		if($.trim(url)==''){
			$.msg("simpleFormSubmit(url,data) : url is blank!");
			return ;
		}else{
			data = data || {};
	        var form = document.createElement("form");
			document.body.appendChild(form);
			$.each(data,function(name,value){
				var inputTemp = document.createElement("input");
				inputTemp.type = "hidden";
				inputTemp.value = value;
				inputTemp.name = name;
				form.appendChild(inputTemp);
			});
			form.action = url;
			form.method = "post";
			form.submit();
			document.body.removeChild(form);
		}
    };
	/** 简单form提交插件1 */  
	form_submit = function (form){
		form.submit();
	};
})(jQuery);

$(function(){
	/** 注册详情提示框 */
	$("body").on("click","[atitle!='']:not(a)",function(){
		$(this).detailTip();
	});
	
	/** 全局的ajax访问，处理ajax清求时sesion超时 */
	$.ajaxSetup({ 
		contentType : "application/x-www-form-urlencoded;charset=utf-8", 
		complete : function(XMLHttpRequest,textStatus){
			//通过XMLHttpRequest取得响应头-Session-Status
	   		var sessionStatus = XMLHttpRequest.getResponseHeader("Session-Status"); 
	   		if(typeof(sessionStatus)=='string' 
				&& (sessionStatus=="timeout" || sessionStatus=="expired")){
//				$.unblockUI(); 
				$.msg({text:'会话超时，请重新登录。',nocancel:true}).done(function(){
					window.location.href = window.BASE+"/guide/logining?_e="+sessionStatus;
				});
	        } 
	    } 
	});
	
	/** 全局的ajax访问，blockUI统一锁住/解锁当前页面 */
//	window.onunload = function(){};//解除特性(部分浏览器后退延续之前同步提交blockUI状态)
//	$(document).ajaxStart(function(){
//		$.blockUI({
//			message:"正在加载...",
//			css:{
//				border:'none',
//				width: '90px',
//				top:  ($(window).height() - 100) /2 + 'px', 
//	        	left: ($(window).width() - 100) /2 + 'px', 
//				padding:'10px',
//				backgroundColor:'#000',
//				'-webkit-border-radius':'10px',
//				'-moz-border-radius':'10px',
//				opacity:0.5,
//				color:'#fff'
//			}
//		});
//	}).ajaxStop(function(){
//		$.unblockUI();
//	});

	/** 输入参数自动提示插件 */
	var AUTOCOMPLETE_CACHE = {}; 
	$("input.autocp").each(function(){
		var $this = $(this);
		$this.autocomplete({
			minLength: 6,//优化查询，至少输入6个字
			select: function( event, ui ) {
				$this.val(ui.item.value);
				$this.trigger("blur");
			},
			source: function (request, response) {
				var request_term = $.trim(request.term);
				var param_type = $.trim($this.attr("param_type"));
				//1895-8498-501 0579-1027-1140 5711-UBD1-9323647 
				if(request_term.length<6 || request_term.length>8) return;
				var req_id = param_type + request_term; 
				if (req_id in AUTOCOMPLETE_CACHE) { 
					response(AUTOCOMPLETE_CACHE[req_id]); 
					return; 
				}else{
					$.ajax({
						url: window.BASE+"/guide/requestParamsCache/getAutoCompleteList", 
						global: false,
						data: {param_value: request_term,param_type: function(){return param_type;}},
						dataType: 'json',
						success: function (res,status){
				            if(res.code==='0'){
								AUTOCOMPLETE_CACHE[req_id] = res.data; 
								response(res.data); 
							}
			        	}
					});
				} 
	    	}
		});
	});
});