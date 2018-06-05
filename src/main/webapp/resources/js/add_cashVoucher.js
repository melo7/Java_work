$(function(){
			
	
	showInformation();

	$(".btn-showCashVoucher").on("click",function(){
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
 $('#cashVoucher_add').on('click', function(){
    layer.open({
        type: 1,
        title: '添加用户',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_cashVoucher_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".add_cashVoucher input[type$='text']").each(function(n){
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
			  insertCashVoucherInfo().done(function(result){
			   console.log(result)
              if(result.code == "0"){
                  layer.alert('添加成功！',{
               title: '提示框',				
                icon:1,		
                  });
                  layer.close(index);
				  $.ajax({
						type:"POST",
						url:"/ssm/cashVoucher/showCashVoucherInfo",
						dataType:"json",
						data:"",
						success:function(result){
							var cashVoucherList = result.cashVoucherList;
							console.log(JSON.stringify(cashVoucherList));
							var bodyStr = "";
							if(cashVoucherList){
								$.each(cashVoucherList,function(n,val){
									bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.packageName+"</td><td>"+val.packageNbr+"</td><td>"+val.startTime+"</td><td>"+val.endTime+"</td><td>"+val.createDate+"</td><td>"+val.createBy+"</td><td class=\"td-manage\"><a onClick=\"cashVoucher_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"cashVoucher_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"cashVoucher_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
									
									$("#tbody_reflush_cashVoucher").empty();
									$("#tbody_reflush_cashVoucher").append(bodyStr);
								});
							}
							$(".showNumber_cashVoucher").html(result.length);
							$(".topshownbr_cashVoucher").html(result.length);
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
function cashVoucher_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="cashVoucher_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function cashVoucher_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="cashVoucher_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}

function cashVoucher_del(obj,id){
	console.log(JSON.stringify(id));
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url:'/ssm/cashVoucher/deleteCashVoucherInfo',
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
function insertCashVoucherInfo(){
	var dfd = $.Deferred();
  var packageName = $("input[name='cashVoucher_name']").val();
  var packageNbr = $("input[name='cashVoucher_code']").val();
  var createBy = $("input[name='cashVoucher_add']").val();
  var params={'packageName':packageName,
        'packageNbr':packageNbr,
        'createBy':createBy};
	$.ajax({
		url:'/ssm/cashVoucher/insert',
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
			url:"/ssm/cashVoucher/showCashVoucherInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var cashVoucherList = result.cashVoucherList;
				var bodyStr = "";
				if(cashVoucherList){
					$.each(cashVoucherList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.packageName+"</td><td>"+val.packageNbr+"</td><td>"+val.startTime+"</td><td>"+val.endTime+"</td><td>"+val.createDate+"</td><td>"+val.createBy+"</td><td class=\"td-manage\"><a onClick=\"cashVoucher_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"cashVoucher_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"cashVoucher_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
									
						$("#tbody_reflush_cashVoucher").empty();
						$("#tbody_reflush_cashVoucher").append(bodyStr);
					});
				}
				$(".showNumber_cashVoucher").html(result.length);
				$(".topshownbr_cashVoucher").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}


function cashVoucher_edit(id){
	$.ajax({
			type:"POST",
			url:"/ssm/cashVoucher/updateCashVoucherInfo",
			dataType:"json",
			data:{'id':id},
			success:function(result){
				console.log(JSON.stringify(result));
				if(result.code == "0"){
					var cashVoucherList = result.cashVoucherList;

					$("input[name='update_cashVoucher_name']").val(cashVoucherList.packageName);
					$("input[name='update_cashVoucher_code']").val(cashVoucherList.packageNbr);
					$("input[name='update_cashVoucher_add']").val(cashVoucherList.createBy);

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
        content:$('#update_cashVoucher_style'),
		btn:['修改','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".update_cashVoucher input[type$='text']").each(function(n){
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

			var packageName = $("input[name='update_cashVoucher_name']").val();
			var packageNbr = $("input[name='update_cashVoucher_code']").val();
			var createBy = $("input[name='update_cashVoucher_add']").val();

			var params={
					'id':id,
					'packageName':packageName,
					'packageNbr':packageNbr,
					'createBy':createBy};
			  $.ajax({
					url:'/ssm/cashVoucher/saveCashVoucherInfo',
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