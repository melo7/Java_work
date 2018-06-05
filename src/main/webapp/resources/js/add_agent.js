$(function(){
			
	
	showInformation();

	$(".btn-showAgent").on("click",function(){
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
 $('#agent_add').on('click', function(){
    layer.open({
        type: 1,
        title: '添加用户',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_agent_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".add_agent input[type$='text']").each(function(n){
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
			  insertAgentInfo().done(function(result){
			   console.log(result)
              if(result.code == "0"){
                  layer.alert('添加成功！',{
               title: '提示框',				
                icon:1,		
                  });
                  layer.close(index);
				  $.ajax({
						type:"POST",
						url:"/ssm/agent/showAgentInfo",
						dataType:"json",
						data:"",
						success:function(result){
							var agentList = result.agentList;
							console.log(JSON.stringify(agentList));
							var bodyStr = "";
							if(agentList){
								$.each(agentList,function(n,val){
									bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.agentGroupName+"</td><td>"+val.agentPName+"</td><td>"+val.createDate+"</td><td>"+val.adderss+"</td><td>"+val.status+"</td><td>"+val.updateDate+"</td><td>"+val.createBy+"</td><td class=\"td-manage\"><a onClick=\"agent_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"agent_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"agent_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
						
									$("#tbody_reflush_agent").empty();
									$("#tbody_reflush_agent").append(bodyStr);
								});
							}
							$(".showNumber_agent").html(result.length);
			  				$(".topshownbr_agent").html(result.length);
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
function agent_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="agent_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function agent_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="agent_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}

function agent_del(obj,id){
	console.log(JSON.stringify(id));
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url:'/ssm/agent/deleteAgentInfo',
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
function insertAgentInfo(){
	var dfd = $.Deferred();
  var agentName = $("input[name='agent_agentName']").val();
  var pName = $("input[name='agent_pName']").val();
  var createBy = $("input[name='agent_createBy']").val();
  var status = $("input[name='agent_status']").val();
  var adderss = $("input[name='agent_adderss']").val();
  var params={'agentGroupName':agentName,
        'agentPName':pName,
        'createBy':createBy,
		'status':status,
		'adderss':adderss};
	$.ajax({
		url:'/ssm/agent/insert',
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
			url:"/ssm/agent/showAgentInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var agentList = result.agentList;
				console.log(JSON.stringify(agentList));
				var bodyStr = "";
				if(agentList){
					$.each(agentList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.agentGroupName+"</td><td>"+val.agentPName+"</td><td>"+val.createDate+"</td><td>"+val.adderss+"</td><td>"+val.status+"</td><td>"+val.updateDate+"</td><td>"+val.createBy+"</td><td class=\"td-manage\"><a onClick=\"agent_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"agent_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"agent_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
						
						$("#tbody_reflush_agent").empty();
						$("#tbody_reflush_agent").append(bodyStr);
					});
				}
				$(".showNumber_agent").html(result.length);
				$(".topshownbr_agent").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}