$(function(){
			
	
	showInformation();

	$(".btn-showRoadRescue").on("click",function(){
		showInformation();
	});
	$('table th input:checkbox').on('click' , function(){
		var that = this;
		$(this).closest('table').find('tr > td:first-child input:checkbox')
		.each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
			
	});


	$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
	function tooltip_placement(context, source) {
		var $source = $(source);
		var $parent = $source.closest('table');
		var off1 = $parent.offset();
		var w1 = $parent.width();

		var off2 = $source.offset();
		var w2 = $source.width();

		if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
		return 'left';
	}
});

/*用户-添加*/
 $('#roadRescue_add').on('click', function(){
    layer.open({
        type: 1,
        title: '添加用户',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_roadRescue_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".add_roadRescue input[type$='text']").each(function(n){
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
			  insertRoadRescueInfo().done(function(result){
			   console.log(result)
              if(result.code == "0"){
                  layer.alert('添加成功！',{
               title: '提示框',				
                icon:1,		
                  });
                  layer.close(index);
				  $.ajax({
						type:"POST",
						url:"/ssm/roadRescue/showRoadRescueInfo",
						dataType:"json",
						data:"",
						success:function(result){
							var roadRescueList = result.roadRescueList;
							console.log(JSON.stringify(roadRescueList));
							var bodyStr = "";
							if(roadRescueList){
								$.each(roadRescueList,function(n,val){
									bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.callName+"</td><td>"+val.rescueTime+"</td><td>"+val.rescueAdderss+"</td><td>"+val.rescueName+"</td><td>"+val.rescueResult+"</td><td>"+val.remark+"</td><td class=\"td-manage\"><a onClick=\"serviceVoucher_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"serviceVoucher_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
									
									$("#tbody_reflush_roadRescue").empty();
									$("#tbody_reflush_roadRescue").append(bodyStr);
								});
							}
							$(".showNumber_roadRescue").html(result.length);
			  				$(".topshownbr_roadRescue").html(result.length);
						},
						error:function(msg){
							layer.alert('刷新会员列表失败，请重试！',{
							title: '提示框',				
							icon:1,		
							});
						}
					});
				  //refresh();
              }else if(result.code == "2"){
				layer.alert('身份证号码必须为18位！',{
               title: '提示框',				
                icon:1,		
                  });
                  //layer.close(index);
				  //refresh();
			  }else if(result.code == "3"){
				layer.alert('不是有效的手机号码！',{
               title: '提示框',				
                icon:1,		
                  });
                  //layer.close(index);
				  //refresh();
			  }else if(result.code == "4"){
				layer.alert('邮箱地址不合法！',{
               title: '提示框',				
                icon:1,		
                  });
                  //layer.close(index);
				  //refresh();
			  }else{
                  layer.alert(result.message,{
                  title: '提示框',				
                  icon:1,		
                  });
              }
        	 });	
		  }		  		     				
		}
    });
});
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url+'#?='+id,w,h);
}
/*用户-停用*/
// $('#stopService').on("click",function(){
// 	alert(111)
// 	layer.confirm('确认要停用吗？',function(index){
// 		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
// 		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
// 		$(obj).remove();
// 		layer.msg('已停用!',{icon: 5,time:1000});
// 	});
// });
function roadRescue_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="roadRescue_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function roadRescue_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="roadRescue_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}

function roadRescue_del(obj,id){
	console.log(JSON.stringify(id));
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url:'/ssm/roadRescue/deleteRoadRescueInfo',
			type:'POST',
			data:{'id':id},
			dataType:'json',
			success:function(result){
				if(result.code == 0){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					layer.alert('删除失败！',{
               		title: '提示框',				
					icon:1,		
			  		});
				}
			},
			error:function(msg){
				layer.alert('请求删除失败！',{
               	title: '提示框',				
				icon:1,		
			  	});
			}
		});
		// window.location.href = "/ssm/member/deleteMemberInfo?id="+id;
	});
}
laydate({
    elem: '#start',
    event: 'focus' 
});		

/*添加服务包信息*/
function insertRoadRescueInfo(){
	var dfd = $.Deferred();
  var callName = $("input[name='roadRescue_callName']").val();
  var rescueTime = $("input[name='roadRescue_rescueTime']").val();
  var rescueAdderss = $("input[name='roadRescue_rescueAdderss']").val();
  var rescueName = $("input[name='roadRescue_rescueName']").val();
  var rescueResult = $("input[name='roadRescue_rescueResult']").val();
  var remark = $("input[name='roadRescue_remark']").val();
  var params={'callName':callName,
        'rescueTime':rescueTime,
        'rescueAdderss':rescueAdderss,
		'rescueName':rescueName,
        'rescueResult':rescueResult,
        'remark':remark};
	$.ajax({
		url:'/ssm/roadRescue/insert',
		type:'POST',
		data:params,
		dataType:'json',
		success:function(result){
			if(result.code == '0'){
				 dfd.resolve(result);
			}else{
				 dfd.resolve(result);
			}
		},
		error:function(msg){
			layer.alert('添加失败！',{
               title: '提示框',				
			icon:1,		
			  });
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

function showInformation(){
		$.ajax({
			type:"POST",
			url:"/ssm/roadRescue/showRoadRescueInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var roadRescueList = result.roadRescueList;
				var bodyStr = "";
				if(roadRescueList){
					$.each(roadRescueList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.callName+"</td><td>"+val.rescueTime+"</td><td>"+val.rescueAdderss+"</td><td>"+val.rescueName+"</td><td>"+val.rescueResult+"</td><td>"+val.remark+"</td><td class=\"td-manage\"><a onClick=\"serviceVoucher_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"serviceVoucher_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
									
						$("#tbody_reflush_roadRescue").empty();
						$("#tbody_reflush_roadRescue").append(bodyStr);
					});
				}
				$(".showNumber_roadRescue").html(result.length);
				$(".topshownbr_roadRescue").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}