/**
 * Created by hzwangshuyan@corp.netease.com.
 */
$(function(){

	//可选角色
    $(".j-umentrole").bind("click",function(e){
            var element=$(e.target);
            if(element.parent().attr("class")=="m-s")
            {
                $(this).find("span").removeClass().addClass("none");
                 element.removeClass().addClass("select");
            }
    });
	//tab 菜单 切换
    $(".u-tab").bind("click",function(e){
        var element=$(e.target);

        if(element.is("[title]"))
        {
            var t=element.index();
            $(".u-tab a").removeClass();
            element.addClass("select");
             $(".j-page").hide().eq(t).show()
        }
    })
 	//jq  checkbox状态
    $(".u-iptUnit").bind("click",function(e){
           var element=e.target;
            if(element.nodeName.toLowerCase()=="b" || element.nodeName.toLowerCase()=="i")
            {
                $(this).find("b").attr("class","uncheck");
                $(element).parent().find("b").attr("class","checked");
            }
           else if(element.nodeName.toLowerCase()=="span")
            {
                $(this).find("b").attr("class","uncheck");
                 $(element).find("b").attr("class","checked");
            }
    });

   //选择县城
    $(".j-choseTown").bind("click",function(){
        $(".opendiv-w").show();
    });

    $(".opendiv-w").bind("click",function(e){
          var element= e.target;
          if(element.nodeName.toLowerCase()=="a")
          {
              $(".j-town").html($(element).html());
			  $("input[name=c4Name]").val($(element).html());
              $(".opendiv-w").hide();
          }
    })
    $(".opendiv-w .close").bind("click",function(){
        $(".opendiv-w").hide();
    });

    //表单焦点状态 FIXED-luoxm原来的判读逻辑存在问题
    $("input.ipt[type=text]").each(function(){
		$(this).on("focusin", function(){
			var dfh = $(this).attr("default_value");
	        if($.trim($(this).val()) === dfh){
	            $(this).val('');
	            $(this).css("color","#000");
	        }
	    });
		$(this).on("change", function(){
			var dfh = $(this).attr("default_value");
			if($.trim($(this).val()) === ""){
	            $(this).val(dfh);
	            $(this).removeAttr("style");
	        }else{
				if($.trim($(this).val()) != dfh){
		            $(this).css("color","#000");
		        }
			}
	    });
		$(this).on("blur", function(){
			var dfh = $(this).attr("default_value");
	        if($.trim($(this).val()) === ""){
	            $(this).val(dfh);
	            $(this).removeAttr("style");
	        }
	    });
	});
	//表单初始化-适用后退
	$("input.ipt[type=text],select.ipt").each(function(){
		var dfh = $(this).attr("default_value");
		if($(this).val()!=dfh){
            $(this).css("color","#000");
        }
	});
    //照片选择
    $(".m-gallery .unit").click(function(){
        $(this).find("b").toggleClass("selectok");
    })
})
//弹窗样式
function opendiv(){
    $(".opendiv").css("top",($(window).height() -300)/2+"px").show()
    $('.mask').css({height: $(window).height(),width:640}).show();
}
$(".opendiv .close").click(function(){
    $(".opendiv").hide();
    $('.mask').hide();
})