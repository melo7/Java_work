$(function(){
	// var oTable1 = $('#sample-table').dataTable( {
	// 		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	// "bStateSave": true,//状态保存
	// "aoColumnDefs": [
	// 	//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	// 	{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
	// ] } );
			
			
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
 $('#merchant_add').on('click', function(){
    layer.open({
        type: 1,
        title: '添加用户',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_merchant_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".merchant_add input[type$='text']").each(function(n){
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
			  insertMerchantInfo().done(function(result){
			   console.log(result)
              if(result.code == "0"){
                  layer.alert('添加成功！',{
               title: '提示框',				
                icon:1,		
                  });
                  layer.close(index);
				  $.ajax({
						type:"POST",
						url:"/ssm/merchant/showMerchantInfo",
						dataType:"json",
						data:"",
						success:function(result){
							var memberList = result.memberList;
							var bodyStr = "";
							if(memberList){
								$.each(memberList,function(n,val){
									bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.userName+"</td><td>"+val.idNumber+"</td><td>"+val.phoneNumber+"</td><td>"+val.emall+"</td><td>"+val.adderss+"</td><td>"+val.createDate+"</td><td>"+val.clientLevel+"</td><td>"+val.status+"</td><td class=\"td-manage\"><a onClick=\"merchant_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"merchant_edit("+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
									//"+val.id+","+val.userName+","+val.idNumber+","+val.phoneNumber+","+val.emall+","+val.adderss+","+val.createDate+","+val.clientLevel+","+val.status+"
									$("#tbody_reflush_merchant").empty();
									$("#tbody_reflush_merchant").append(bodyStr);
								});
							}
							$(".showNumber_merchant").html(result.length);
			  				$(".topshownbr_merchant").html(result.length);
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
function merchant_show(title,url,id,w,h){
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
function merchant_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="merchant_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function merchant_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="merchant_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}
/*用户-编辑*/
// $('#editService').on("click",function(){
// 	alert(222)
// 	layer.open({
//         type: 1,
//         title: '修改用户信息',
// 		maxmin: true, 
// 		shadeClose:false, //点击遮罩关闭层
//         area : ['800px' , ''],
//         content:$('#add_menber_style'),
// 		btn:['提交','取消'],
// 		yes:function(index,layero){	
// 		 var num=0;
// 		 var str="";
//      $(".add_menber input[type$='text']").each(function(n){
//           if($(this).val()=="")
//           {
               
// 			   layer.alert(str+=""+$(this).attr("name")+"不能为空！\r\n",{
//                 title: '提示框',				
// 				icon:0,								
//           }); 
// 		    num++;
//             return false;            
//           } 
// 		 });
// 		  if(num>0){  return false;}	 	
//           else{
// 			  layer.alert('添加成功！',{
//                title: '提示框',				
// 			icon:1,		
// 			  });
// 			   layer.close(index);	
// 		  }		  		     				
// 		}
//     });
// });
function merchant_edit(id){
	$.ajax({
			type:"POST",
			url:"/ssm/merchant/updateMerchantInfo",
			dataType:"json",
			data:{'id':id},
			success:function(result){
				console.log(JSON.stringify(result));
				if(result.code == "0"){
					var merchantList = result.merchantList;
					$("input[name='update_merchant_name']").val(merchantList.userName);
					$("input[name='update_merchant_realname']").val(merchantList.realName);
					$("input[name='update_merchant_idNumber']").val(merchantList.idNumber);
					$("input[name='update_merchant_phone']").val(merchantList.phoneNumber);
					$("input[name='update_merchant_emall']").val(merchantList.emall);
					$("select[name='update_merchant_statusoption']").val(merchantList.status);
					$("select[name='update_merchant_selectoption']").val(merchantList.clientLevel);
					$("input[name='update_merchant_adderss']").val(merchantList.adderss);
				}else{
					layer.alert('更新会员列表失败，请重试！',{
					title: '提示框',				
					icon:1,		
					});
				}
			},
			error:function(msg){
				layer.alert('更新会员列表失败',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
	  //------------------------------------------
	  layer.open({
        type: 1,
        title: '修改用户信息',
		maxmin: true, 
		shadeClose:false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#update_merchant_style'),
		btn:['修改','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".update_merchant input[type$='text']").each(function(n){
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
			  
			   var name = $("input[name='update_merchant_name']").val();
				var realname = $("input[name='update_merchant_realname']").val();
				var idNumber = $("input[name='update_merchant_idNumber']").val();
				var phone = $("input[name='update_merchant_phone']").val();
				var emall = $("input[name='update_merchant_emall']").val();
				var status = $("select[name='update_merchant_statusoption']").val();
				var memberLevel = $("select[name='update_merchant_selectoption']").val();
				var adderss = $("input[name='update_merchant_adderss']").val();
				var params={
						'id':id,
						'name':name,
						'realname':realname,
						'idNumber':idNumber,
						'phone':phone,
						'emall':emall,
						'memberLevel':memberLevel,
						'adderss':adderss,
						'status':status};
			  $.ajax({
					url:'/ssm/merchant/saveMerchantInfo',
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
						layer.alert('请求删除失败！',{
						title: '提示框',				
						icon:1,		
						});
					}
				});	
		  }		  		     				
		}
    });
}
/*用户-删除*/
// $('#deleteService').on("click",function(){
// 	alert(333)
// 	layer.confirm('确认要删除吗？',function(index){
// 		$(obj).parents("tr").remove();
// 		layer.msg('已删除!',{icon:1,time:1000});
// 	});
// });
function merchant_del(obj,id){
	console.log(JSON.stringify(id));
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url:'/ssm/merchant/deleteMerchantInfo',
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

/*添加会员信息*/
function insertMerchantInfo(){
	var dfd = $.Deferred();
  var name = $("input[name='merchant_name']").val();
  var realname = $("input[name='merchant_realname']").val();
  var idNumber = $("input[name='merchant_idNumber']").val();
  var phone = $("input[name='merchant_phone']").val();
  var emall = $("input[name='merchant_emall']").val();
  var status = $("select[name='merchant_statusoption']").val();
  var memberLevel = $("select[name='merchant_selectoption']").val();
  var adderss = $("input[name='merchant_adderss']").val();
  var params={'name':name,
        'realname':realname,
        'idNumber':idNumber,
        'phone':phone,
        'emall':emall,
        'memberLevel':memberLevel,
        'adderss':adderss,
		'status':status};
	$.ajax({
		url:'/ssm/merchant/insertMerchant',
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