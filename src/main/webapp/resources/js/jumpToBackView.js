$(function(){

	$(".membersssssli").on("click",function(){
		window.location.href = "/ssm/member/showMemberInfo";
	})
	$('#login_btn').on('click', function(){
		
	     var num=0;
		 var str="";
     $("input[type$='text'],input[type$='password']").each(function(n){
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
			//   backToContrlView().done(function(result){
			// 	  if(result.code == "0"){
			// 		  if(result.role == "1"){
						//   $(" ul .nav_list li #productLiElem ").hide();
						// document.getElementById("platformAuditLiElem").show();
					//   }

					layer.alert('登陆成功！',{
					title: '提示框',				
					icon:1,		
					});
					location.href="/ssm/view/index.html";
					layer.close(index);
					 
			// 	}else{
			// 		alert(res);
			// 	}
			//   })
		  }	     						
		
	});

		backToContrlView().done(function(result){
				  if(result.code == "0"){
					  if(result.role == "1"){
						//  console.log($('#productLiElem').innerHTML);
						//  document.getElementById('#productLiElem').style.display="block";
						//   $('#productLiElem').style.display="block";
						  $('#lpatformTotalLiElem').show();
						  $('#messagepushLiElem').show();
						//   $(" ul .nav_list li #productLiElem ").hide();
						// document.getElementById("platformAuditLiElem").show();
					  }
				}else{
					alert(res);
				}
			  })

  $(document).ready(function(){
	 $("input[type='text'],input[type='password']").blur(function(){
        var $el = $(this);
        var $parent = $el.parent();
        $parent.attr('class','frame_style').removeClass(' form_error');
        if($el.val()==''){
            $parent.attr('class','frame_style').addClass(' form_error');
        }
    });
	$("input[type='text'],input[type='password']").focus(function(){		
		var $el = $(this);
        var $parent = $el.parent();
        $parent.attr('class','frame_style').removeClass(' form_errors');
        if($el.val()==''){
            $parent.attr('class','frame_style').addClass(' form_errors');
        } else{
			 $parent.attr('class','frame_style').removeClass(' form_errors');
		}
		});
	  })
});

function backToContrlView(){
	var dfd = $.Deferred();
	var name = "";
	var password = "";
	var params={'name':name,
				'password':password};
	$.ajax({
		url:'/ssm/account/doAccount',
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
			alert(msg)
		}
	});
	return dfd.promise();
}

