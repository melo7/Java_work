$(function(){
	// $.simpleFormSubmit("/ssm/member/showMemberInfo");

	// window.location.href = "/ssm/member/showMemberInfo";

	showPersonalInformation();
	// $.ajax({
	// 		type:"POST",
	// 		url:"/ssm/member/showMemberInfo",
	// 		dataType:"json",
	// 		data:"",
	// 		success:function(result){
	// 			var memberList = result.memberList;
	// 			var bodyStr = "";
	// 			if(memberList){
	// 				$.each(memberList,function(n,val){
	// 					bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.userName+"</td><td>"+val.idNumber+"</td><td>"+val.phoneNumber+"</td><td>"+val.emall+"</td><td>"+val.adderss+"</td><td>"+val.createDate+"</td><td>"+val.clientLevel+"</td><td>"+val.status+"</td><td class=\"td-manage\"><a onClick=\"member_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"member_edit('550')\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"member_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
						
	// 					$("#tbody_reflush").empty();
	// 					$("#tbody_reflush").append(bodyStr);
	// 				});
	// 			}
	// 			$(".showNumber").html(result.length);
	// 			$(".topshownbr").html(result.length);
	// 		},
	// 		error:function(msg){
	// 			layer.alert('刷新会员列表失败，请重试！',{
	// 			title: '提示框',				
	// 			icon:1,		
	// 			});
	// 		}
	// 	});

	$(".btn-showPesonal").on("click",function(){
		showPersonalInformation();
	// 	$.ajax({
	// 	type:"POST",
	// 	url:"/ssm/member/showMemberInfo",
	// 	dataType:"json",
	// 	data:"",
	// 	success:function(result){
	// 		  var memberList = result.memberList;
	// 		  var bodyStr = "";
	// 		  if(memberList){
	// 			$.each(memberList,function(n,val){
	// 				//alert(value.id);
	// 				bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.userName+"</td><td>"+val.idNumber+"</td><td>"+val.phoneNumber+"</td><td>"+val.emall+"</td><td>"+val.adderss+"</td><td>"+val.createDate+"</td><td>"+val.clientLevel+"</td><td>"+val.status+"</td><td class=\"td-manage\"><a onClick=\"member_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"member_edit('550')\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"member_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
					
	// 				$("#tbody_reflush").empty();
	// 				$("#tbody_reflush").append(bodyStr);
	// 				layer.alert('刷新会员列表成功！',{
    //           		title: '提示框',				
	// 				icon:1,		
	// 		  		});
	// 			});
	// 		  }
	// 		  $(".showNumber").html(result.length);
	// 		  $(".topshownbr").html(result.length);
	// 	},
	// 	error:function(msg){
	// 		layer.alert('刷新会员列表失败，请重试！',{
    //            title: '提示框',				
	// 		icon:1,		
	// 		  });
	// 	}
	// });
	})
});

function showPersonalInformation(){
		$.ajax({
			type:"POST",
			url:"/ssm/personal/showPersonalInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var memberList = result.memberList;
				var bodyStr = "";
				if(memberList){
					$.each(memberList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.userName+"</td><td>"+val.idNumber+"</td><td>"+val.phoneNumber+"</td><td>"+val.emall+"</td><td>"+val.adderss+"</td><td>"+val.createDate+"</td><td>"+val.clientLevel+"</td><td>"+val.status+"</td><td class=\"td-manage\"><a onClick=\"personal_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"personal_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"personal_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
						
						$("#tbody_reflush_personal").empty();
						$("#tbody_reflush_personal").append(bodyStr);
					});
				}
				$(".showNumber_personal").html(result.length);
				$(".topshownbr_personal").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新会员列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}