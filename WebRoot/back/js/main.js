var element;
var form;
layui.config({
	base: basePath + 'back/js/'
}).use(['element', 'layer', 'navbar', 'tab','form'], function() {
	element = layui.element();
	form = layui.form();
	
	var $ = layui.jquery,
		layer = layui.layer,
		navbar = layui.navbar(),
		tab = layui.tab({
			elem: '.admin-nav-card' //设置选项卡容器
		});
	
	/*var active = {
			tabAdd: function(){
		      //新增一个Tab项
				element.tabAdd('admin-tab', {
			        title: '新选项',
			        content: '内容',
			        id: "100"
		      });
		    },
		    tabDelete: function(othis){
		      //删除指定Tab项
		      element.tabDelete('demo', '44'); //删除：“商品管理”
		      
		      
		      othis.addClass('layui-btn-disabled');
		    },
		    tabChange: function(){
		    	alert("change");
		      //切换到指定Tab项
		    	element.tabChange('admin-tab', '100'); //切换到：用户管理
		    }
	};
	
	$('.site-demo-active').on('click', function(){
	    var othis = $(this), type = othis.data('type');
	    active[type] ? active[type].call(this, othis) : '';
	});*/
	
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
	
	
/*	setTimeout(function(){
		console.log(element)
		element.tabAdd('admin-tab',{
		  title: '来电弹屏',
		  content:'<iframe scrolling="no" style="width:100%; height:'+$content.height()+'px; border:1px solid red;" src="'+basePath+'turn/callAlert.do"></iframe>',
		  closed:true,
		  id: 'aa'
		});
	},3000)*/
	
	
	
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
			var dataUrl = $(".layui-tab-title").find("li");
			var data_url = "";
			for(var i = 0;i<dataUrl.length;i++){
				if(dataUrl.eq(i).attr("data-url")){
					data_url = dataUrl.eq(i).attr("data-url");
				}
			}
			for(var i = 0; i<Cite.length;i++){
				$("#admin-navbar-side .layui-nav a").eq(i).parent().removeClass("layui-this");
				if(src == $("#admin-navbar-side .layui-nav a").eq(i).attr("data-url")){
					$("#admin-navbar-side .layui-nav a").eq(i).parent().addClass("layui-this");
				}
			}
			if(data_url){
				if(src.split("turn")[1] == data_url.split("turn")[1]){
					for(var i = 0; i<Cite.length;i++){
						$("#admin-navbar-side .layui-nav a").eq(i).parent().removeClass("layui-this");
					}
					console.log($("#admin-navbar-side .layui-nav a").eq(28).parent())
					$("#admin-navbar-side .layui-nav a").eq(28).parent().addClass("layui-this");
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
			window.location.href = basePath + "login/logout.do";
		})
	})
		
	
});

function callInAlertModal(phone) {
	$("#callInPhone").val(phone);
	var a = $("#admin-navbar-side .layui-nav a");
	for(var i = 0;i<a.length;i++){
		if(a.eq(i).find("cite").html() == "来电弹屏"){
			a.eq(i).click();
		}
	}

	
	/*$("#callInPhone").val(phone);
	$("#tabTitle").find("li").removeClass("layui-this");
	var childs = $("#tabContent").find("div");
	console.log($(child).length);
	for ( var child in childs) {
		if ($(child).hasClass("lay-show")) {
			console.log("lay-show");
		}
	}
	$("#tabContent").find("div").removeClass("lay-show");
	var str1 = '<li class="layui-this" lay-id="callAlert"><cite style="padding-right:10px;">来电弹屏</cite></li>';
	var str2 = '<div style="height:100%;" class="layui-tab-item lay-show"><iframe src="' + basePath + 'turn/callAlert.do"></iframe></div>';
	console.log($("#callInPhone").val());
	$("#tabTitle").append(str1);
	//$("#tabTitle").find(".layui-this").click();
	$("#tabContent").append(str2);
	element.tabChange('admin-tab', "callAlert");
	form.render();
	console.log(element);*/
	
//	$("#tabAdd").click();
//	$("#tabChange").click();
	
	
	/*element.tabAdd("admin-tab", {
		title: phone + '来电',
		content: '<iframe src="' + basePath + 'turn/callAlert.do"></iframe>',
//		content: basePath + 'popups/agent/callAlert.jsp',
		id: '11'
	});
	setTimeout(function() {
		element.tabChange("admin-tab", '11');
	}, 3000);
	console.log(1);
	console.log(2);*/
}

