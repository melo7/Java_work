$(function(){
			
	
	showInformation();

	$(".btn-showIntegral").on("click",function(){
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
			url:"/ssm/integral/showIntegralInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var integralList = result.integralList;
				var bodyStr = "";
				if(integralList){
					$.each(integralList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.intergralName+"</td><td>"+val.integralAmount+"</td><td>"+val.startTime+"</td><td>"+val.endTime+"</td><td class=\"td-manage\"><a onClick=\"integral_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"integral_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"integral_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
									
						$("#tbody_reflush_integral").empty();
						$("#tbody_reflush_integral").append(bodyStr);
					});
				}
				$(".showNumber_integral").html(result.length);
				$(".topshownbr_integral").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}

function integral_edit(id){
		$.ajax({
			type:"POST",
			url:"/ssm/integral/updateIntegralInfo",
			dataType:"json",
			data:{'id':id},
			success:function(result){
				console.log(JSON.stringify(result));
				if(result.code == "0"){
					var integralList = result.integralList;
					$("input[name='update_integralName']").val(integralList.integralName);
					$("input[name='update_integralAmount']").val(integralList.integralAmount);
					
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
	  layer.open({
        type: 1,
        title: '修改用户信息',
		maxmin: true, 
		shadeClose:false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#update_integral_style'),
		btn:['修改','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".update_integral input[type$='text']").each(function(n){
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
			  	var integralName = $("input[name='update_integralName']").val();
				var integralAmount = $("input[name='update_integralAmount']").val();
				
				var params={
						'id':id,
						'integralAmount':integralAmount,
						'integralName':integralName
						};
			  $.ajax({
					url:'/ssm/integral/saveIntegralInfo',
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
function intergral_del(obj,id){
	console.log(JSON.stringify(id));
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url:'/ssm/integral/deleteIntegralInfo',
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