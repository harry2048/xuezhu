$(function(){
	//左侧高度
	function left_height() {
		var _height = $(window).height();
		var _left_height = _height-100;
		$(".menu").height(_left_height);
		$(".main_right").height(_left_height);
	}
	left_height();
	$(window).resize(function() {
		left_height();
	});

	//左侧
	$(".menu_div").click(function() {
		if($(this).parent().find(".second_menu").css("display")=="block"){
			$(this).parent().find(".iconfont").removeClass('icon-up').addClass('icon-down');
			$(this).parent().find(".second_menu").hide();
		}else{
			$(this).parent().find(".iconfont").removeClass('icon-down').addClass('icon-up');
			$(this).parent().find(".second_menu").show();

		}
		$(this).parent().siblings().find(".second_menu").hide();
		$(this).parent().siblings().find(".iconfont").removeClass('icon-up').addClass('icon-down');
	});
	$(".second_menu li").click(function() {
		$(this).addClass('active');
		$(this).siblings().removeClass('active');
		var _class = $(this).attr("msg");
		$("."+_class+"").show();
		$("."+_class+"").siblings().hide()
	});
	// 名词解释
	$(".see_info").click(function() {
		$(".jieshi").show();
	});
	$(".jieshi .iconfont").click(function() {
		$(".jieshi").hide();
	});
	//订单详情
	$(".xiang").click(function() {
		$(".order_msg").show();
	});
	$(".order_msg .icon-error").click(function() {
		$(".order_msg").hide();
	});
	$(".tabs_li li").click(function() {
		var _index = $(this).index();
		$(this).addClass('current');
		$(this).siblings().removeClass('current');
		$(this).parent().siblings().find(".tabs_info").eq(_index).show();
		$(this).parent().siblings().find(".tabs_info").eq(_index).siblings().hide();
	});
	//点击tr第一列右箭头
	$(".info_list .iconfont").click(function(){
		if($(this).hasClass('icon-right')){
			$(this).removeClass('icon-right').addClass('icon-down');
			var _html = "<tr >"+
							"<td colspan='9'  class='msg_info'>"+
								"<ul>"+
									"<li>"+
										"<span class='title_name'>订单号</span>"+
										"<span class='title_info'>77752</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>姓名</span>"+
										"<span class='title_info'>张洪淘</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>申请时间</span>"+
										"<span class='title_info'>2019-06-24 10:47:31</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>贷款金额</span>"+
										"<span class='title_info'>39800.0</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>课程</span>"+
										"<span class='title_info'>java开发工程师</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>手机</span>"+
										"<span class='title_info'>18203470467</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>分期方案</span>"+
										"<span class='title_info'>6+18</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>状态</span>"+
										"<span class='title_info'>申请拒绝</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>机构名称</span>"+
										"<span class='title_info'>中软云际（北京）</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>咨询老师</span>"+
										"<span class='title_info'>陈宁宁</span>"+
									"</li>"+
									"<li>"+
										"<span class='title_name'>放款时间</span>"+
										"<span class='title_info'></span>"+
									"</li>"+
								"</ul>"+
							"</td>"+
						"</tr>";
			$(this).parent().parent().after(_html);
		}else{
			$(this).removeClass('icon-down').addClass('icon-right');
			$(this).parent().parent().next().remove();
		}
	})

	//保证金
	$(".money_tabs_li li").click(function() {
		/* Act on the event */
		var _index = $(this).index();
		$(this).addClass('current');
		$(this).siblings().removeClass('current');
		$(this).parent().siblings().find(".tabs_info").eq(_index).show();
		$(this).parent().siblings().find(".tabs_info").eq(_index).siblings().hide();
	});
	//保证金服务费 下拉
	$(".select_click").click(function() {
		/* Act on the event */
		if($(this).find(".iconfont").hasClass('icon-down')){
			$(this).find(".iconfont").removeClass('icon-down').addClass('icon-up');
			$(".select_div").show();
		}else{
			$(this).find(".iconfont").removeClass('icon-up').addClass('icon-down');
			$(".select_div").hide();
		}
	});
	$(".select_ul .select_li").click(function() {
		/* Act on the event */
		$(".select_span").removeClass('place');
		$(".select_span").text($(this).text());
		$(this).parent().parent().siblings().find(".iconfont").removeClass('icon-up').addClass('icon-down');
		$(".select_div").hide();
	});

})