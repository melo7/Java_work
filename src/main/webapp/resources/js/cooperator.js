$(function(){
			
	
	showInformation();

	$(".btn-showCooperator").on("click",function(){
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
 $('#cooperator_add').on('click', function(){
    layer.open({
        type: 1,
        title: '添加用户',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_cooperator_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".add_cooperator input[type$='text']").each(function(n){
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
			  insertCooperatorInfo().done(function(result){
			   console.log(result)
              if(result.code == "0"){
                  layer.alert('添加成功！',{
               title: '提示框',				
                icon:1,		
                  });
                  layer.close(index);
				  $.ajax({
						type:"POST",
						url:"/ssm/cooperator/showCooperatorInfo",
						dataType:"json",
						data:"",
						success:function(result){
							var cooperatorList = result.cooperatorList;
							console.log(JSON.stringify(cooperatorList));
							var bodyStr = "";
							if(cooperatorList){
								$.each(cooperatorList,function(n,val){
									bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.cooCompanyName+"</td><td>"+val.cooName+"</td><td>"+val.cooPNumber+"</td><td>"+val.city+"</td><td>"+val.createDate+"</td><td>"+val.createBy+"</td><td class=\"td-manage\"><a onClick=\"cooperator_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"cooperator_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"cooperator_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
						
									$("#tbody_reflush_cooperator").empty();
									$("#tbody_reflush_cooperator").append(bodyStr);
								});
							}
							$(".showNumber_cooperator").html(result.length);
							$(".topshownbr_cooperator").html(result.length);
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
function cooperator_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="cooperator_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function cooperator_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="cooperator_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}

function cooperator_del(obj,id){
	console.log(JSON.stringify(id));
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url:'/ssm/cooperator/deleteCooperatorInfo',
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
function insertCooperatorInfo(){
	var dfd = $.Deferred();
  var cooCompanyName = $("input[name='cooCompanyName']").val();
  var cooName = $("input[name='cooName']").val();
  var cooPNumber = $("input[name='cooPNumber']").val();
  var city = $("input[name='city']").val();
  var createBy = $("input[name='createBy']").val();
  var params={'cooCompanyName':cooCompanyName,
        'cooName':cooName,
        'cooPNumber':cooPNumber,
		'city':city,
        'createBy':createBy};
	$.ajax({
		url:'/ssm/cooperator/insert',
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
			url:"/ssm/cooperator/showCooperatorInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var cooperatorList = result.cooperatorList;
				var bodyStr = "";
				if(cooperatorList){
					$.each(cooperatorList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.cooCompanyName+"</td><td>"+val.cooName+"</td><td>"+val.cooPNumber+"</td><td>"+val.city+"</td><td>"+val.createDate+"</td><td>"+val.createBy+"</td><td class=\"td-manage\"><a onClick=\"cooperator_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"cooperator_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"cooperator_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
						
						$("#tbody_reflush_cooperator").empty();
						$("#tbody_reflush_cooperator").append(bodyStr);
					});
				}
				$(".showNumber_cooperator").html(result.length);
				$(".topshownbr_cooperator").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}


function cooperator_edit(id){
	$.ajax({
			type:"POST",
			url:"/ssm/cooperator/updateCooperatorInfo",
			dataType:"json",
			data:{'id':id},
			success:function(result){
				console.log(JSON.stringify(result));
				if(result.code == "0"){
					var cooperatorList = result.cooperatorList;

					$("input[name='update_cooCompanyName']").val(cooperatorList.cooCompanyName);
					$("input[name='update_cooName']").val(cooperatorList.cooName);
					$("input[name='update_cooPNumber']").val(cooperatorList.cooPNumber);
					$("input[name='update_city']").val(cooperatorList.city);
					$("input[name='update_createBy']").val(cooperatorList.createBy);

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
        content:$('#update_cooperator_style'),
		btn:['修改','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".update_cooperator input[type$='text']").each(function(n){
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

			var cooCompanyName = $("input[name='update_cooCompanyName']").val();
			var cooName = $("input[name='update_cooName']").val();
			var cooPNumber = $("input[name='update_cooPNumber']").val();
			var city = $("input[name='update_city']").val();
			var createBy = $("input[name='update_createBy']").val();

			var params={
						'id':id,
						'cooCompanyName':cooCompanyName,
						'cooName':cooName,
						'cooPNumber':cooPNumber,
						'city':city,
						'createBy':createBy};
			  $.ajax({
					url:'/ssm/cooperator/saveCooperatorInfo',
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