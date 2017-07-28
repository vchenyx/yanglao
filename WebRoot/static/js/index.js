layui.config({
	base: 'js/'
}).use(['element', 'layer', 'navbar', 'tab','form'], function() {
	var element = layui.element(),
		$ = layui.jquery,
		layer = layui.layer,
		navbar = layui.navbar(),
		form = layui.form(),
		tab = layui.tab({
			elem: '.admin-nav-card' //设置选项卡容器
		});
	//设置navbar
	navbar.set({
		spreadOne: true,
		elem: '#admin-navbar-side',
		cached: false,
//		url: 'datas/nav.json'
		data: navs
			/*cached:true,
			url: 'datas/nav.json'*/
		
	});
	//渲染navbar
	navbar.render();
	//监听点击事件
	navbar.on('click(side)', function(data) {
		tab.tabAdd(data.field);
	});
	
	//iframe自适应
	var $content = $('.admin-nav-card .layui-tab-content');
	var header_bottom = $(".header_bottom").height();
	var header_top = $(".header_top").height();
	var content_height = $(".content");
	var layui_tab_title = $(".layui-tab-title").height() + 1;
	var header = $(".header").height();
	$(window).on('resize', function() {
		content_height.height($(this).height() - header )
		$content.height($(this).height() - header - layui_tab_title -5);
		
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	}).resize();
	
	//选项卡溢出隐藏时点击隐藏按钮展示所有选项卡
	$(".layui-tab-title").delegate(".layui-tab-bar","click",function(){
		layui_tab_title = $(".layui-tab-title").height() + 1;
		$content.height($(window).height() - $(".header").height() - layui_tab_title -5);
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	})
	
	var oPen = true;
	$("#show").click(function(){
		if(oPen){
			$(".header").css("display","none");
			$(this).find("i").html("&#xe61a;");
			content_height.height($(window).height());
			$content.height($(window).height() - 50);
			
			$content.find('iframe').each(function() {
				$(this).height($content.height());
			});
			$(window).on('resize', function() {
				content_height.height($(this).height())
				$content.height($(this).height() - 50);
				
				$content.find('iframe').each(function() {
					$(this).height($content.height());
				});
			}).resize();
			oPen = false;
		}
		else{
			$(".header").css("display","block");
			$(this).find("i").html("&#xe619;");
			
			$(window).on('resize', function() {
				content_height.height($(this).height() - header )
				$content.height($(this).height() - header - layui_tab_title -5);
				
				$content.find('iframe').each(function() {
					$(this).height($content.height());
				});
			}).resize();
						
			oPen = true;
		}
	})
	
	//点击选项卡切换
	element.on('tab(admin-tab)', function(data) {
		if($(this).index() != -1){
			var src = $(".layui-tab-content .layui-tab-item").eq($(this).index()).find("iframe").attr("src");
			var Cite = $("#admin-navbar-side .layui-nav a");
			for(var i = 0; i<Cite.length;i++){
				$("#admin-navbar-side .layui-nav a").eq(i).parent().removeClass("layui-this");
				if(src == $("#admin-navbar-side .layui-nav a").eq(i).attr("data-url")){
					$("#admin-navbar-side .layui-nav a").eq(i).parent().addClass("layui-this");
//					$("#admin-navbar-side .layui-nav a").eq(i).parent().parent().parent().addClass("layui-nav-itemed").siblings().removeClass("layui-nav-itemed");
				}
			}
		}
	})
	
	//修改密码
	$('#change').on('click', function() {
//		$.get('change-password.html', null, function(form) {
			layer.open({
				type: 2,
				title: '修改密码',
				content: 'change-password.html',
				btn: ['保存', '取消'],
				area: ['700px', '400px'],
				maxmin: true,
				yes: function(index) {
					console.log(index);
					var body = layer.getChildFrame('body',index);
					var oldPassword = body.find("#oldPassword").val();
					var newPassword = body.find("#newPassword").val();
					var sureNewPassword = body.find("#sureNewPassword").val();
					//进行判断，修改成功后跳转到登录页重新登录同时关闭弹层
					
					//layer.close(index)
					
				},
				full: function(elem) {
					var win = window.top === window.self ? window : parent.window;
					$(win).on('resize', function() {
						var $this = $(this);
						elem.width($this.width()).height($this.height()).css({
							top: 0,
							left: 0
						});
						elem.children('div.layui-layer-content').height($this.height() - 95);
					});
				}
			});
//		});
	});
	
	//注销
	$("#exit").click(function(){
		layer.confirm("确认注销？",{
			btn:['确认','取消'],
			btn2:function(index,layero){
//				alert('取消')
			}
		},function(index,layero){
			window.location.href = "login.html";
		})
	})
		
	
});



















