$(function(){
			
	
	showInformation();

	$(".btn-showSingin").on("click",function(){
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
			url:"/ssm/singin/showSinginInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var singinList = result.singinList;
				var bodyStr = "";
				if(singinList){
					$.each(singinList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.singinName+"</td><td>"+val.createDate+"</td>";
									
						$("#tbody_reflush_singin").empty();
						$("#tbody_reflush_singin").append(bodyStr);
					});
				}
				$(".showNumber_singin").html(result.length);
				$(".topshownbr_singin").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}