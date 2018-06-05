$(function(){
	//start llc js
//	$(".submit").on("click",function(){
//		var username = $.trim($("input[name='login']").val());
//		var password = $.trim($("input[name='password']").val());
//		var dfd = $.Deferred();
//		var params ={
//				username:"username",
//				password:"password"
//		}
////		loginIndex(username,password).done(function(res){
////			if(res == 0){
////				$.simpleFormSubmit("/ssm/llc/successToLogin");
////			}
////		});
////		$.simpleFormSubmit("/ssm/llc/login",params);
//		$.ajax({
//			url:'/ssm/llc/login',
//	        type: 'POST',
//	        data: {
//	            'username': username,
//	            'password': password
//	        },
//	        dataType: 'json',
//	        success: function(result) {
//	        	console.log(JSON.stringify(result.message))
//	            if (result.code=='0') {
//	                $.simpleFormSubmit("/ssm/llc/successToLogin");
//	                dfd.resolve(result.data);
//	            } else {
//	                alert(result.message);
//	            }
//	        },
//	        error:function(msg){
//	        	dfd.reject("登录有误,请重试");
//	        }
//		});
//	});
	
	$("#article_save_submit").on("click",function(){
		var num=0;
		 var str="";
    $(".Mandatory input[type$='text']").each(function(n){
         if($(this).val()=="")
         {
              
			   layer.alert(str+=""+$(this).attr("name")+"不能为空！\r\n",{
               title: '提示框',				
				icon:0,								
         }); 
		    num++;
           return false;            
         } 
		 });
		  if(num>0){  return false;}	 	
         else{
        	 
        	 insertArticleAdd().done(function(res){
        		 if(res == "0"){
        			 layer.alert('添加成功！',{
        				 title: '提示框',				
        				 icon:1,		
        			 });
        			 refresh();
//        			 layer.close(index);	
        		 }else{
        			 alert(res)
        		 }
        	 });
		  }
	});
	
	/**
	 * 关闭编辑页
	 */
	$("#layer_close").on("click",function(){
		history.go(-1);
	});
	$(function(){
		var ue = UE.getEditor('editor');
	});
	/*radio激发事件*/
	function Enable(){ $('.date_Select').css('display','block');}
	function closes(){$('.date_Select').css('display','none')}
	/**日期选择**/
	var start = {
	    elem: '#start',
	    format: 'YYYY/MM/DD',
	    min: laydate.now(), //设定最小日期为当前日期
	    max: '2099-06-16', //最大日期
	    istime: true,
	    istoday: false,
	    choose: function(datas){
	         end.min = datas; //开始日选好后，重置结束日的最小日期
	         end.start = datas //将结束日的初始值设定为开始日
	    }
	};
	var end = {
	    elem: '#end',
	    format: 'YYYY/MM/DD',
	    min: laydate.now(),
	    max: '2099-06-16 ',
	    istime: true,
	    istoday: false,
	    choose: function(datas){
	        start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	};
	laydate(start);
	laydate(end);
});

function insertArticleAdd(){
	var dfd = $.Deferred();
	var article_title = $("input[name='article_title']").val();
	var article_head = $("input[name='article_head']").val();
	var article_sort = $("input[name='article_sort']").val();
	var selectoption = $("select[name='selectoption']").val();
	//var article_info = $("span[name='article_info']").html();
	var article_info=UE.getEditor('editor').getPlainTxt();
	alert(article_info);
	var params ={'article_title':article_title,
				 'article_head':article_head,
				 'article_sort':article_sort,
				 'selectoption':selectoption,
				 'article_info':article_info};
	$.ajax({
		url:'/ssm/car/insertArticleAdd',
		type:'POST',
		data:params,
		dataType:'json',
		success:function(result){
			console.log(JSON.stringify(result))
			if(result.code == "0"){
				dfd.resolve(result.code);
			}else{
				dfd.resolve(result.message);
			}
		},
		error:function(msg){
			alert(msg);
		}
	});
	return dfd.promise();
}
function refresh(){
    window.location.reload();//刷新当前页面.
     
    //或者下方刷新方法
    //parent.location.reload()刷新父亲对象（用于框架）--需在iframe框架内使用
    // opener.location.reload()刷新父窗口对象（用于单开窗口
  //top.location.reload()刷新最顶端对象（用于多开窗口）
}