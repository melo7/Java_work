$(function(){
			
	
	showInformation();

	$(".btn-showRefuelRecharge").on("click",function(){
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
			url:"/ssm/refuelRecharge/showRefuelRechargeInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var refuelRechargeList = result.refuelRechargeList;
				var bodyStr = "";
				if(refuelRechargeList){
					$.each(refuelRechargeList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.refuel_name+"</td><td>"+val.refuel_phone+"</td><td>"+val.refuel_cardNbr+"</td><td>"+val.singTime+"</td>";
									
						$("#tbody_reflush_refuelRecharge").empty();
						$("#tbody_reflush_refuelRecharge").append(bodyStr);
					});
				}
				$(".showNumber_refuelRecharge").html(result.length);
				$(".topshownbr_refuelRecharge").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}