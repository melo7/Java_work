$(function(){
			
	
	showInformation();

	$(".btn-showUserStatistics").on("click",function(){
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
 $('#userStatistics_add').on('click', function(){
    layer.open({
        type: 1,
        title: '添加用户',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_userStatistics_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".add_userStatistics input[type$='text']").each(function(n){
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
			  insertUserStatisticsInfo().done(function(result){
			   console.log(result)
              if(result.code == "0"){
                  layer.alert('添加成功！',{
               title: '提示框',				
                icon:1,		
                  });
                  layer.close(index);
				  $.ajax({
						type:"POST",
						url:"/ssm/userStatistics/showUserStatisticsInfo",
						dataType:"json",
						data:"",
						success:function(result){
							var userStatisticsList = result.userStatisticsList;
							console.log(JSON.stringify(userStatisticsList));
							var bodyStr = "";
							if(userStatisticsList){
								$.each(userStatisticsList,function(n,val){
									bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.singup+"</td><td>"+val.upNbr+"</td><td>"+val.createDate+"</td>";
						
									$("#tbody_reflush_userStatistics").empty();
									$("#tbody_reflush_userStatistics").append(bodyStr);
								});
							}
							$(".showNumber_userStatistics").html(result.length);
			  				$(".topshownbr_userStatistics").html(result.length);
						},
						error:function(msg){
							layer.alert('刷新会员列表失败，请重试！',{
							title: '提示框',				
							icon:1,		
							});
						}
					});
				  //refresh();
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
function userStatistics_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="userStatistics_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function userStatistics_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="userStatistics_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}

function userStatistics_del(obj,id){
	console.log(JSON.stringify(id));
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url:'/ssm/userStatistics/deleteUserStatisticsInfo',
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
function insertUserStatisticsInfo(){
	var dfd = $.Deferred();
  var singup = $("input[name='userStatistics_singup']").val();
  var upNbr = $("input[name='userStatistics_upNbr']").val();
  var params={'singup':singup,
        'upNbr':upNbr};
	$.ajax({
		url:'/ssm/userStatistics/insert',
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
			url:"/ssm/userStatistics/showUserStatisticsInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var userStatisticsList = result.userStatisticsList;
				console.log(JSON.stringify(userStatisticsList));
				var bodyStr = "";
				if(userStatisticsList){
					$.each(userStatisticsList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.userStatistics_singup+"</td><td>"+val.userStatistics_upNbr+"</td><td>"+val.createDate+"</td>";
						
						$("#tbody_reflush_userStatistics").empty();
						$("#tbody_reflush_userStatistics").append(bodyStr);
					});
				}
				$(".showNumber_userStatistics").html(result.length);
				$(".topshownbr_userStatistics").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}


function userStatistics_edit(id){
	$.ajax({
			type:"POST",
			url:"/ssm/userStatistics/updateUserStatisticsInfo",
			dataType:"json",
			data:{'id':id},
			success:function(result){
				console.log(JSON.stringify(result));
				if(result.code == "0"){
					var userStatisticsList = result.userStatisticsList;

					$("input[name='update_userStatistics_singup']").val(userStatisticsList.sort);
					$("input[name='update_userStatistics_upNbr']").val(userStatisticsList.title);

				}else{
					layer.alert('更新服务列表失败，请重试！',{
					title: '提示框',				
					icon:1,		
					});
				}
			},
			error:function(msg){
				layer.alert('更新服务列表失败',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
	  layer.open({
        type: 1,
        title: '修改服务信息',
		maxmin: true, 
		shadeClose:false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#update_userStatistics_style'),
		btn:['修改','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".update_userStatistics input[type$='text']").each(function(n){
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

			var singup = $("input[name='update_userStatistics_singup']").val();
			var upNbr = $("input[name='update_userStatistics_upNbr']").val();

			var params={
					'id':id,
					'singup':singup,
					'upNbr':upNbr};
			  $.ajax({
					url:'/ssm/userStatistics/saveUserStatisticsInfo',
					type:'POST',
					data:params,
					dataType:'json',
					success:function(result){
						if(result.code == 0){
							 layer.alert('修改成功！',{
								title: '提示框',				
								icon:1,		
								});
								layer.close(index);
						}else{
							layer.alert('修改失败！',{
							title: '提示框',				
							icon:1,		
							});
						}
					},
					error:function(msg){
						layer.alert('请求修改失败！',{
						title: '提示框',				
						icon:1,		
						});
					}
				});	
		  }		  		     				
		}
    });
}

$('#year_statistics').on('click', function(){
	layer.alert('暂未开通！',{
		title: '提示框',				
		icon:1,		
		});
});
$('#month_statistics').on('click', function(){
	layer.alert('暂未开通！',{
		title: '提示框',				
		icon:1,		
		});
});
$('#day_statistics').on('click', function(){
	layer.alert('暂未开通！',{
		title: '提示框',				
		icon:1,		
		});
});