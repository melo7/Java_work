$(function(){

    showInformation();

	var
　　　　 fileInput = document.getElementById('test-image-file'),
        info = document.getElementById('test-file-info'),
        preview = document.getElementById('test-image-preview');
       // 监听change事件:
       fileInput.addEventListener('change', function() {
       　　// 清除背景图片:
          preview.style.backgroundImage = '';
          // 检查文件是否选择:
          if(!fileInput.value) {
               info.innerHTML = '没有选择文件';
                   return;
          }                    
          // 获取File引用:
          var file = fileInput.files[0];
          //判断文件大小
          var size = file.size;
          if(size >= 1*1024*1024){
                alert('文件大于1兆不行!');
                return false;
          }
          // 获取File信息:
          info.innerHTML = '文件: ' + file.name + '<br>' +
                           '大小: ' + file.size + '<br>' +
                           '修改: ' + file.lastModifiedDate;
          if(file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
              alert('不是有效的图片文件!');
              return;
		  }
          // 读取文件:
          var reader = new FileReader();
                reader.onload = function(e) {
                　　var
                     　　data = e.target.result; // 'data:image/jpeg;base64,/9j/4AAQSk...(base64编码)...}'            
                     　　preview.style.backgroundImage = 'url(' + data + ')';
					 $("#file_upload").ajaxSubmit({
						success: function(res){
                            console.log(JSON.stringify(res));
							if (res.data.code == '0') {
								var data = res.data;
                                console.log(data);
								if(data.code == '0'){
                                    layer.alert('图片上传成功!',{
                                    title: '提示框',				
                                    icon:1,		
                                    });
								}else {
                                    layer.alert('图片上传失败!',{
                                    title: '提示框',				
                                    icon:1,		
                                    });
								}
							}
						}
					});
               };
                // 以DataURL的形式读取文件:
                reader.readAsDataURL(file);
	   });
});
	/*******添加广告*********/
 $('#ads_add').on('click', function(){
	  layer.open({
        type: 1,
        title: '添加广告',
		maxmin: true, 
		shadeClose: false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#add_ads_style'),
		btn:['提交','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".add_adverts input[type$='text']").each(function(n){
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
              saveAdvertisingInfo().done(function(result){
                    layer.alert('添加成功！',{
                    title: '提示框',				
                    icon:1,		
                    });
                    layer.close(index);	
              });
			  
		  }		  		     				
		}
    });
})		

function saveAdvertisingInfo(){
    var dfd = $.Deferred();
    var imgName = $("input[name='picName']").val();
    var imgheight = $("input[name='imgheight']").val();
    var imgweight = $("input[name='imgweight']").val();
    var sort = $("input[name='sort']").val();
    var content = $("input[name='content']").val();
    var params = {
        'imgName':imgName,
        'imgheight':imgheight,
        'imgweight':imgweight,
        'sort':sort,
        'content':content
    }
    $.ajax({
		url:'/ssm/uploadPhoto/savePhotoInfo',
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

function showInformation(){
		$.ajax({
			type:"POST",
			url:"/ssm/uploadPhoto/showAdvertisingInfo",
			dataType:"json",
			data:"",
			success:function(result){
				var advertisingList = result.advertisingList;
				var bodyStr = "";
				if(advertisingList){
					$.each(advertisingList,function(n,val){
						bodyStr += "<tr><td><label><input type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label></td><td>"+val.id+"</td><td>"+val.photoName+"</td><td>"+val.createTime+"</td><td>"+val.createBy+"</td><td>"+val.photoSize+"</td><td>"+val.title+"</td><td>"+val.photoContent+"</td><td class=\"td-manage\"><a onClick=\"advertising_stop(this,'10001')\" href=\"javascript:;\" title=\"停用\" class=\"btn btn-xs btn-success\"><i class=\"icon-ok bigger-120\"></i></a><a onClick=\"advertising_edit("+val.id+")\" href=\"javascript:;\" title=\"编辑\" class=\"btn btn-xs btn-info\"><i class=\"icon-edit bigger-120\"></i></a><a onClick=\"advertising_del(this,"+val.id+")\" href=\"javascript:;\" title=\"删除\" class=\"btn btn-xs btn-warning\"><i class=\"icon-trash bigger-120\"></i></a></td></tr>";
									
						$("#tbody_reflush_advertising").empty();
						$("#tbody_reflush_advertising").append(bodyStr);
					});
				}
				$(".showNumber_advertising").html(result.length);
				$(".topshownbr_agent_advertising").html(result.length);
			},
			error:function(msg){
				layer.alert('刷新列表失败，请重试！',{
				title: '提示框',				
				icon:1,		
				});
			}
		});
}


/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url+'#?='+id,w,h);
}
function advertising_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="advertising_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function advertising_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="advertising_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}

function advertising_edit(id){
	$.ajax({
			type:"POST",
			url:"/ssm/uploadPhoto/updateAdvertisingInfo",
			dataType:"json",
			data:{'id':id},
			success:function(result){
				console.log(JSON.stringify(result));
				if(result.code == "0"){
					var advertisingList = result.advertisingList;


                    var dfd = $.Deferred();
                    $("input[name='update_picName']").val(advertisingList.photoName);
                   $("input[name='update_imgheight']").val(advertisingList.photoSize);
                    $("input[name='update_imgweight']").val(advertisingList.photoSize);
                    $("input[name='update_sort']").val(advertisingList.title);
                    $("input[name='update_content']").val(advertisingList.photoContent);
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
        title: '修改广告图信息',
		maxmin: true, 
		shadeClose:false, //点击遮罩关闭层
        area : ['800px' , ''],
        content:$('#update_ads_style'),
		btn:['修改','取消'],
		yes:function(index,layero){	
		 var num=0;
		 var str="";
     $(".update_adverts input[type$='text']").each(function(n){
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

				var imgName = $("input[name='update_picName']").val();
                var imgheight = $("input[name='update_imgheight']").val();
                var imgweight = $("input[name='update_imgweight']").val();
                var sort = $("input[name='update_sort']").val();
                var content = $("input[name='update_content']").val();
                var params = {
                    'id':id,
                    'imgName':imgName,
                    'imgheight':imgheight,
                    'imgweight':imgweight,
                    'sort':sort,
                    'content':content
                }
			  $.ajax({
					url:'/ssm/uploadPhoto/saveAdvertisingInfo',
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
function advertising_del(obj,id){
	console.log(JSON.stringify(id));
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url:'/ssm/uploadPhoto/deleteAdvertisingInfo',
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