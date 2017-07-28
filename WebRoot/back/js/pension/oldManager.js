layui.config({
	base : basePath + 'back/plugins/layui/modules/'
});

var pagesize = 20;
var currentpage = 1;
var isOpen = true;
var open_close = false;
init();

$(function() {
	searchInfo();
})

// 模糊查询
function searchInfo() {
	searchCount();
}

//分页查询总页数
function searchCount(){ 
	layui.use(['laypage','layer'],function() {
		var $ = layui.jquery, 
		laypage = layui.laypage, 
		layer = parent.layer === undefined ? layui.layer : parent.layer; 
		//查询总数
		$.ajax({ 
			url:basePath + "pension/getAllOndCount.do",
			data:{ 
				"name":$.trim($("#name").val()), 
				"idCard":$.trim($("#idCard").val()),
				"phone":$.trim($("#phone").val()), 
				"pagesize":pagesize 
			}, 
			type:"post", 
			async:false, 
			success:function(json){ 
				var pageCount = json.pageCount;//总页数 // page 
				var totalCount = json.totalCount;//总页数 // page 
				$(".pageCount").html(pageCount);
				$(".totalCount").html(totalCount);
				laypage({ 
					cont : 'page', 
					pages : pageCount, 
					groups : 5, 
					jump : function(obj, first) { 
						currentpage = obj.curr;
						if (!first) { 
							searchList(); 
						} 
					}
				});
				searchList();
			}, 
			error:function(){
			}
		}); 
	});
}

function searchList() {
	$.ajax({
		url : basePath + "pension/getAllOldList.do",
		type : "get",
		data:{ 
			"name":$.trim($("#name").val()), 
			"idCard":$.trim($("#idCard").val()),
			"phone":$.trim($("#phone").val()), 
			"pagesize":pagesize,
			"currentpage":currentpage
		}, 
		dataType : "json",
		success : function(data) {
			if (data.length > 0) {
				var str = "";
				for (var i = 0; i < data.length; i++) {
					var obj = data[i];
					str += '<tr>';
					str += '<td>' + (i + 1) + '</td>';
					str += '<td>' + obj.name + '</td>';
					str += '<td>' + (obj.sex == 0 ? '男' : '女')
							+ '</td>';
					str += '<td>' + myDate.longToAge(obj.birthday) + '</td>';
					str += '<td>' + obj.idCard + '</td>';
					str += '<td>' + obj.phone + '</td>';
					str += '<td>'
							+ (obj.linkName == null ? ""
									: obj.linkName) + '</td>';
					str += '<td>'
							+ (obj.linkName == null ? ""
									: obj.linkPhone) + '</td>';
					str += '<td>' + obj.address + '</td>';
					str += '<td>';
					str += '<a href="javascript:;" data-id="1" data-opt="del" onclick="showHealthy('
							+ obj.id
							+ ')" class="layui-btn layui-btn-danger layui-btn-mini">查看健康状况</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '<a href="javascript:;" data-id="1" data-opt="del" onclick="updateOlder('
						+ obj.id
						+ ')" class="layui-btn layui-btn-danger layui-btn-mini">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					str += '<a href="javascript:;" msg=\''
							+ JSON.stringify(obj)
							+ '\' data-id="1" data-opt="del" onclick="deleteOlder(this)" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>';
					str += '</td>';
					str += '</tr>';
				}
				$("#show_tbd").html(str);
			}
		}
	});
}

// 初始化（表格、单选框初始化）
function init() {
	layui.use([ 'icheck', 'laypage', 'layer', 'form' ],function() {
			$ = layui.jquery, laypage = layui.laypage, 
			form = layui.form(),
			layer = parent.layer === undefined ? layui.layer: parent.layer;
		$('input').iCheck({
			checkboxClass : 'icheckbox_flat-green'
		});

		$('.site-table tbody tr').on('click', function(event) {
			var $this = $(this);
			var $input = $this.children('td').eq(0).find('input');
			$input.on('ifChecked', function(e) {
				$this.css('background-color', '#EEEEEE');
			});
			$input.on('ifUnchecked', function(e) {
				$this.removeAttr('style');
			});
			$input.iCheck('toggle');
		}).find('input').each(function() {
			var $this = $(this);
			$this.on('ifChecked', function(e) {
				$this.parents('tr').css('background-color', '#EEEEEE');
			});
			$this.on('ifUnchecked', function(e) {
				$this.parents('tr').removeAttr('style');
			});
		});
		$('#selected-all').on(
				'ifChanged',
				function(event) {
					var $input = $('.site-table tbody tr td').find(
							'input');
					$input.iCheck(event.currentTarget.checked ? 'check'
							: 'uncheck');
				});

	});
}



/*layui.use([ 'laypage', 'layer', 'form' ], function() {
	$ = layui.jquery, laypage = layui.laypage, form = layui.form,
			layer = parent.layer === undefined ? layui.layer : parent.layer;
	// page
	laypage({
		cont : 'page',
		pages : 25, // 总页数
		groups : 5, // 连续显示分页数
		jump : function(obj, first) {
			// 得到了当前页，用于向服务端请求对应数据
			var curr = obj.curr;
			if (!first) {
				layer.msg('第 ' + obj.curr + ' 页');
			}
		}
	});

	// 条件搜索查询框触发
	$('#search').on('click', function() {
		if (isOpen) {
			isOpen = false;
			if (open_close) {
				$(".layui-form").css("overflow", "hidden");
				$(".layui-form").animate({
					"height" : "0"
				}, 400);
				open_close = false;
			} else {
				$(".layui-form").css("overflow", "");
				$(".layui-form").animate({
					"height" : "100px"
				}, 400);
				open_close = true;
			}
			setTimeout(function() {
				isOpen = true;
			}, 400);
		}
	});

});*/

// 添加
function addMESS() {
	layui.use([ 'layer' ], function() {
		var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer
				: parent.layer;
		layer.open({
			type : 2,
			title : '',
			content : basePath + 'popups/pension/addOldDetailInfo.jsp',
			btn : [ '保存', '取消' ],
			area : [ '1300px', '90%' ],
			maxmin : true,
			full : function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on(
						'resize',
						function() {
							var $this = $(this);
							elem.width($this.width()).height($this.height())
									.css({
										top : 0,
										left : 0
									});
							elem.children('div.layui-layer-content').height(
									$this.height() - 95);
						});
			},
			success : function(layero, index) {
				// 成功加载iframe
				var body = layer.getChildFrame('body', index);
			},
			yes : function(index, layero) {
				var body = layer.getChildFrame('body', index);
				var rtnJson = getAllValue(body);
				 $.ajax({
					 url: basePath + "oldInfo/saveOldInfo.do",
					 data: rtnJson,
					 type: "post",
					 dataType: "json",
					 success: function(data) {
						 if (data.rtn == 1) {
							 layer.alert("添加成功", function() {
								 layer.closeAll();
							 });
						 }
					 }
				 })
			},
			end : function() {
				searchInfo();
			}
		});
	});
}

function updateOlder(id) {
	 $.ajax({
		 url: basePath + "oldInfo/findByid.do",
		 data: {"id":id},
		 type: "post",
		 dataType: "json",
		 success: function(data) {
		var list = data.list;
	layui.use([ 'layer' ], function() {
		var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer
				: parent.layer;
		layer.open({
			type : 2,
			title : '添加老人',
			content : basePath + 'popups/pension/addOldDetailInfo.jsp',
			btn : [ '保存', '取消' ],
			area : [ '1300px', '90%' ],
			maxmin : true,
			full : function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on(
						'resize',
						function() {
							var $this = $(this);
							elem.width($this.width()).height($this.height())
									.css({
									top : 0,
									left : 0
									});
							elem.children('div.layui-layer-content').height(
									$this.height() - 95);
						});
			},
			success : function(layero, index) {
				// 成功加载iframe
				var body = layer.getChildFrame('body', index);
				body.find("#peopleName").val(list.name); // 姓名
				//性别
				if(list.sex == 0){
					body.find('#sex').prop("checked","checked");
				}else {
					body.find('#sex1').prop("checked","checked");
				}
				var birthday = new Date(list.birthday).toLocaleDateString();
				body.find("#birthday").val(birthday.split("/").join("-")); // 出生日期
				body.find("#communityName").val(list.communityName);//所属社区
				body.find("#cencusAddr").val(list.cencusAddr);//户籍地址
				body.find("#address").val(list.address);//详细社区
				body.find("#cellphone").val(list.phone);//家庭电话
				body.find("#linkPhone").val(list.linkPhone);//紧急联系人手机号
				body.find("#linkName").val(list.linkName);//紧急联系人
				
				body.find("#nation").val(list.nation);//民族
				
				//职业
				if(list.workState == 0){
					body.find('#workState').prop("checked","checked");
				} else if (list.workState == 1) {
					body.find('#workState1').prop("checked","checked");
				} else {
					body.find('#workState2').prop("checked","checked");
				}
				body.find('#work').val(list.workInfo);
				//职业结束				
				
				//老人类别
				var oldType = list.oldType;
				if (oldType.indexOf("其他") >= 0) {
					body.find("#oldType").val("其他");
					body.find("#oldTypeOther").val(oldType.substring(3));
					body.find(".oldTypeOther").css("display", "block");
				} else {
					body.find("#oldType").val(list.oldType);
				}
				//body.find("#oldType").find("option").eq((list.oldType)).prop("selected","selected");
				
				//居家养老情况
				body.find("#yanglao").val(list.yanglao);//find("option").eq((list.yanglao - 1)).prop("selected","selected");
				//医疗费用支付方式
				body.find("#zhifu").val(list.payType);//find("option").eq((list.payType - 1)).prop("selected","selected");
				//血型
				body.find("#bloodType").val(list.bloodType);//find("option").eq((list.bloodType - 1)).prop("selected","selected");
				//过敏史
				body.find("#guomin").val(list.allergy);//find("option").eq((list.allergy - 1)).prop("selected","selected");
				if (list.allergy == 5) {
					body.find("#guominOther").val(list.allergyTarget);
					body.find("#guominOther").parent().css("display","block");
				}
				//暴露史
				body.find("#baolu").val(list.expose);//find("option").eq((list.expose - 1)).prop("selected","selected");
				if (list.expose == 5) {
					body.find("#baoluOther").val(list.exposeTarget);
					body.find("#baoluOther").parent().css("display","block");
				}
				body.find("#idCard").val(list.idCard);//身份证号
				//是否户籍所在地
				if(list.isCensus ==0){
					body.find('#huji').prop("checked","checked");
				}else {
					body.find('#feihuji').prop("checked","checked");
				}
				
				
				//既往史-疾病
				//高血压&123123;糖尿病&12432423;@撒的发生多个&234234,撒的发生多个&21342134,是的根深蒂固&2345235432
				if (list.jibing.length > 0) {
					var arr = list.jibing.split(";");
					
					var valArr = [];
					
					var htmlStr = "";
					for (var i = 0; i < arr.length; i++) {
						var single = arr[i];
						if (single.indexOf("@") >= 0) {
							valArr.push("其他");
							single = single.substring(1);
							var splitArr = single.split(",");
							body.find("#addJibing").css("display", "block");
							for (var j = 0; j < splitArr.length; j++) {
								var singleBing = splitArr[j].split("&");
								htmlStr += "<div class='layui-input-block jibing' flag='val其他' style='margin-top:20px;'>"
									+ "<label class='layui-form-label' style='width:120px; padding:9px 0; text-align:right;'><font color='red' style='size:2px' >*</font>疾病名称：</label>"
									+ "<div class='layui-input-inline' style='margin-top:0;'>"
									+ "<input type='text' value='" + singleBing[0] + "' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
									+ "</div>"
									+ "<label class='layui-form-label' style='width:100px; padding:9px 0; text-align:right;'><font color='red' style='size:2px' >*</font>时间：</label>"
									+ "<div class='layui-input-inline' style='margin-top:0;'>"
									+ "<input type='text' value='" + singleBing[1] + "' class='layui-input' style='width:242px;' />"
									+ "</div>"
									+ "<div class='layui-input-inline' style='margin-top:3px; width: 80px;'>"
									+ "<a href='javascript:;' class='layui-btn layui-btn-small' onclick='removed(this)' style='margin-left: 60px; background: #3983e1;'>"
									+ "<i class='layui-icon'>&#xe608;</i> 删除"
									+ "</a>"
									+ "</div>"
									+ "</div>";
							}
						} else {
							var singleBing = single.split("&");
							valArr.push(singleBing[0]);
							var html = "<div class='layui-input-block jibing' style='margin-top:20px;' flag='val"+singleBing[0]+"'>"
							+ "<label class='layui-form-label' style='width:120px; padding:9px 0; text-align:right;'><font color='red' style='size:2px' >*</font>"+singleBing[0]+"：</label>"
							+ "<input type='text' class='layui-input' value='"+singleBing[1]+"' style='width:242px;' />"
							+ "</div>";
							body.find("#timer").append(html);
						}
					}
					body.find("#timer").append(htmlStr);
					checkboxWrite(body, valArr, "jibing");
				}
				//既往史-手术
				var shoushu1 = list.shoushu;
				if(shoushu1.length > 1){
					body.find('#shoushuyou').prop("checked","checked");
					var shoushu = list.shoushu.split(";");
					body.find("#shoushu").html(" ");
					for(var i = 0;i<shoushu.length;i++){
						var html = "<div class='layui-form-item shoushu' style='margin-top:20px;'>"
							+ "<label class='layui-form-label' style='width:80px; padding-left: 0px'><font color='red' style='size:2px'>*</font>手术名称：</label>"
							+ "<div class='layui-input-inline' style='width:242px;'>"
							+ "<input type='text' value='"+shoushu[i].split("&")[0]+"' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
							+ "</div>"
							+ "<label class='layui-form-label' style='width:50px;'><font color='red' style='size:2px' >*</font>时间：</label>"
							+ "<div class='layui-input-inline' style='width:242px;'>"
							+ "<input class='layui-input' value='"+shoushu[i].split("&")[1]+"' onclick='layui.laydate({elem:this,istime:true})' style='width:242px;'/>"
							+ "</div>"
							+ "<div class='layui-input-inline' style='margin-top:3px; width: 80px; margin-right: 0px;'>"
							+ "<a href='javascript:;' class='layui-btn layui-btn-small' onclick='removed(this)' style='background: #3983e1;'>"
							+ "<i class='layui-icon'>&#xe608;</i> 删除"
							+ "</a>"
							+ "</div>"
							+ "</div>";
						body.find("#shoushu").append(html);
					}
					body.find("#shoushu").css("display","block");
					body.find(".btn_shoushu").css("display","block");
				}
				//既往史-外伤
				var waishang1 = list.waishang;
				if(waishang1.length > 1){
					body.find('#waishangyou').prop("checked","checked");
					var waishang = list.waishang.split(";");
					body.find("#waishang").html(" ");
					for(var i = 0;i<waishang.length;i++){
						var html = "<div class='layui-form-item waishang' style='margin-top:20px;'>"
								 + "<label class='layui-form-label' style='width:80px; padding-left: 0px'><font color='red' style='size:2px' >*</font>外伤名称：</label>"
								 + "<div class='layui-input-inline'  style='width: 242px'>"
								 + "<input type='text' value='"+waishang[i].split("&")[0]+"' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
								 + "</div>"
								 + "<label class='layui-form-label' style='width:50px;'><font color='red' style='size:2px' >*</font>时间：</label>"
								 + "<div class='layui-input-inline' style='width: 242px'>"
								 + "<input class='layui-input' value='"+waishang[i].split("&")[1]+"' onclick='layui.laydate({elem:this,istime:true})' style='width:242px;'/>"
								 + "</div>"
								 + "<div class='layui-input-inline' style='margin-top:3px; width: 80px; margin-right: 0px;'>"
								 + "<a href='javascript:;' class='layui-btn layui-btn-small' onclick='removed(this)' style='background: #3983e1;'>"
								 + "<i class='layui-icon'>&#xe608;</i> 删除"
								 + "</a>"
								 + "</div>"
								 + "</div>";
						body.find("#waishang").append(html);
					}
					body.find("#waishang").css("display","block");
					body.find(".btn_waishang").css("display","block");
				}
				//既往史-输血
				var shuxue1 = list.shuxue;
				if(shuxue1.length > 1){
					body.find('#shuxueyou').prop("checked","checked");
					var shuxue = list.shuxue.split(";");
					body.find("#shuxue").html(" ");
					for(var i = 0;i<shuxue.length;i++){
						var html = "<div class='layui-form-item shuxue' style='margin-top:20px;'>"
								 + "<label class='layui-form-label' style='width:80px; padding-left: 0px'><font color='red' style='size:2px' >*</font>输血原因：</label>"
								 + "<div class='layui-input-inline' style='width:242px;'>"
								 + "<input type='text' value='"+shuxue[i].split("&")[0]+"' autocomplete='off' class='layui-input' style='width:242px; margin-right:30px;'>"
								 + "</div>"
								 + "<label class='layui-form-label' style='width:50px;'><font color='red' style='size:2px' >*</font>时间：</label>"
								 + "<div class='layui-input-inline' style='width:242px;'>"
								 + "<input class='layui-input' value='"+shuxue[i].split("&")[1]+"' onclick='layui.laydate({elem:this,istime:true})' style='width:242px;'/>"
								 + "</div>"
								 + "<div class='layui-input-inline' style='margin-top:3px; width: 80px;'>"
								 + "<a href='javascript:;' class='layui-btn layui-btn-small' onclick='removed(this)' style='background: #3983e1;'>"
								 + "<i class='layui-icon'>&#xe608;</i> 删除"
								 + "</a>"
								 + "</div>"
								 + "</div>";
						body.find("#shuxue").append(html);
					}
					body.find("#shuxue").css("display","block");
					body.find(".btn_shuxue").css("display","block");
				}
				//家族史-父亲
				var arrFather = list.father.split(";");
				var arrNumFather = [];
				for(var i = 0;i<arrFather.length;i++){
					arrNumFather.push(arrFather[i].split("&")[0]);
					if (arrFather[i].indexOf("&") >= 0) {
						body.find("#fatherOther").val(arrFather[i].substring(1));
					}
				}
				var inpFather = body.find("#father").find("input");
				for(var i = 0;i<arrNumFather.length;i++){
					for(var j = 0;j<inpFather.length;j++){
						if(inpFather.eq(j).val() == arrNumFather[i]){
							inpFather.eq(j).prop("checked","checked");
							
						}
					}
				}
				//body.find("#fatherOther").
				//家族史-母亲
				var arrMother = list.mother.split(";");
				var arrNumFather = [];
				for(var i = 0;i<arrMother.length;i++){
					arrNumFather.push(arrMother[i].split("&")[0]);
					if (arrMother[i].indexOf("&") >= 0) {
						body.find("#motherOther").val(arrMother[i].substring(1));
					}
				}
				var inpMother = body.find("#mother").find("input");
				for(var i = 0;i<arrMother.length;i++){
					for(var j = 0;j<inpMother.length;j++){
						if(inpMother.eq(j).val() == arrMother[i]){
							inpMother.eq(j).prop("checked","checked");
							
						}
					}
				}
				//家族史-兄弟姐妹
				var arrBrother = list.brother.split(";");
				var arrNumBrother = [];
				for(var i = 0;i<arrBrother.length;i++){
					arrNumBrother.push(arrBrother[i].split("&")[0]);
					if (arrBrother[i].indexOf("&") >= 0) {
						body.find("#brotherOther").val(arrBrother[i].substring(1));
					}
				}
				var inpBrother = body.find("#brother").find("input");
				for(var i = 0;i<arrNumBrother.length;i++){
					for(var j = 0;j<inpBrother.length;j++){
						if(inpBrother.eq(j).val() == arrNumBrother[i]){
							inpBrother.eq(j).prop("checked","checked");
							
						}
					}
				}
				//家族史-子女
				var arrSonSister = list.sonSister.split(";");
				var arrNumSonSister = [];
				for(var i = 0;i<arrSonSister.length;i++){
					arrNumSonSister.push(arrSonSister[i].split("&")[0]);
					if (arrSonSister[i].indexOf("&") >= 0) {
						body.find("#sonOther").val(arrSonSister[i].substring(1));
					}
				}
				var inpSonSister = body.find("#son").find("input");
				for(var i = 0;i<arrNumSonSister.length;i++){
					for(var j = 0;j<inpSonSister.length;j++){
						if(inpSonSister.eq(j).val() == arrNumSonSister[i]){
							inpSonSister.eq(j).prop("checked","checked");
						}
					}
				}
				//遗传病史
				if(list.yichuan != ""){
					body.find('#yichuan').prop("checked","checked");
					body.find(".yichuan").css("display","block");
					body.find("#yichuanbing").val(list.yichuan);
				}
				//有无残疾
				if(list.canji != ""){
					body.find('#canji').prop("checked","checked");
					var canjiNum = list.canji.split("&")[0];
					for(var i = 0;i<canjiNum.length;i++){
						body.find(".canji").find("input").eq(canjiNum[i]).prop("checked","checked");
					}
					body.find(".canji").css("display","block");
					body.find("#canjihao").val(list.canji.split("&")[1]);
				}
				
				
				//疾病用药情况
				var fuyao = list.fuyao;
				radioWrite(body, fuyao, "fuyao");
				var kaiyao = list.kaiyao;
				if (kaiyao.length > 1) {
					var kaiyaoArr = kaiyao.split(",");
					radioWrite(body, kaiyaoArr[0], "kaiyao");
					if (kaiyaoArr[0] == 0) { // 月
						body.find("#kaiyaoDay").val(kaiyaoArr[1]);
					} else { // 天
						body.find("#kaiyaoDate").val(kaiyaoArr[1]);
					}
				} else {
					radioWrite(body, kaiyao, "kaiyao");
				}
				
				var drugStoreArr = list.drugStore.split(";");
				for (var i = 0; i < drugStoreArr.length; i++) {
					var drugStore = drugStoreArr[i];
					if (i == 0) {
						body.find("#firstDrug").val(drugStore);
					} else {
						var str = '<div><input value="' + drugStore + '" type="text" name="drugStore" style="margin-top:10px; width:400px; height:20px; border:none; border-bottom: 1px solid #ccc;" /><span onclick="javascript:delHospital(this);" style="cursor: pointer; color:red"><i class="layui-icon">&#xe640;</i></span></div>';
						body.find("#hospitals").append(str);
					}
				}
				
				var drugListArr = list.drugList.split("@");
				for (var i = 0; i < drugListArr.length; i++) {
					var drugList = drugListArr[i];
					var tdArr = drugList.split("&");
					if (i == 0) {
						var firstTrArr = body.find("#firstTr").find("input");
						$(firstTrArr[0]).val(tdArr[0]);
						$(firstTrArr[1]).val(tdArr[1]);
						$(firstTrArr[2]).val(tdArr[2]);
						$(firstTrArr[3]).val(tdArr[3]);
						$(firstTrArr[4]).val(tdArr[4]);
						$(firstTrArr[5]).val(tdArr[5]);
					} else {
						var html = '<tr>'
							+ '<td><input type="text" value="' + tdArr[0] + '"/></td>'
							+ '<td><input type="text" value="' + tdArr[1] + '"/></td>'
							+ '<td><input type="text" value="' + tdArr[2] + '"/></td>'
							+ '<td><input type="text" value="' + tdArr[3] + '"/></td>'
							+ '<td><input type="text" value="' + tdArr[4] + '"/></td>'
							+ '<td><input type="text" value="' + tdArr[5] + '"/></td>'
							+ '<td><span style="cursor: pointer; color:red" onclick="javascript:delYongYao(this);"><i class="layui-icon">&#xe640;</i></span></td>'
							+ '</tr>';
						body.find("tbody").append(html);
					}
				}
				body.find("#xiyang").val(list.xiyang);
				radioWrite(body, list.liugan, "liugan");
				radioWrite(body, list.feiyan, "feiyan");
				body.find("#otherVaccine").val(list.otherVaccine);
				
				//补充评估信息
				var jiawu = list.jiawu.split(";");
				for (var i = 0; i < jiawu.length; i++) {
					if (isNaN(jiawu[i])) {
						body.find("#qitaJiawu").val(jiawu[i]);
						body.find("#qitaJiawu").parent().css("display", "block");
						jiawu[i] = 5;
					}
				}
				checkboxWrite(body, jiawu, "jiawu");
				
				//补充评估信息
				var xiyi = list.xiyi.split(";");
				checkboxWrite(body, xiyi, "clear");
				
				
				var zhushi = list.zhushi.split(";");
				for (var i = 0; i < zhushi.length; i++) {
					if (isNaN(zhushi[i])) {
						body.find("#qitaZhushi").val(zhushi[i]);
						body.find("#qitaZhushi").parent().css("display", "block");
						zhushi[i] = 3;
					}
				}
				checkboxWrite(body, zhushi, "zhushi");
				
				body.find("#income").val(list.income);
				body.find("#maritalStatus").val(list.maritalStatus);
				
				body.find("#community").val(list.communityId + "&" + list.communityName);
				
				var fushi = list.fushi.split(";");
				for (var i = 0; i < fushi.length; i++) {
					if (isNaN(fushi[i])) {
						body.find("#qitaFushi").val(fushi[i]);
						body.find("#qitaFushi").parent().css("display", "block");
						fushi[i] = 3;
					}
				}
				checkboxWrite(body, fushi, "fushi");
				
				var shiwuguomin = list.shiwuguomin.split(";");
				for (var i = 0; i < shiwuguomin.length; i++) {
					if (isNaN(shiwuguomin[i])) {
						body.find("#qitaShiwuguomin").val(shiwuguomin[i]);
						body.find("#qitaShiwuguomin").parent().css("display", "block");
						shiwuguomin[i] = 4;
					}
				}
				checkboxWrite(body, shiwuguomin, "shiwuguomin");
				
				
				var duanlian = list.duanlian;
				if (duanlian != 0) {
					radioWrite(body, 1, "duanlian");
					var duanlianArr = duanlian.split(";");
					if (duanlianArr[0] == 0) {
						radioWrite(body, 0, "duanlianpinci");
					} else {
						body.find("#pinci").val(duanlianArr[0]);
					}
					
					radioWrite(body, duanlianArr[1], "duanlianshijian");
					body.find("#jianchi").val(duanlianArr[2]);
					
					var duanlianType = duanlianArr[3].split("&");
					for (var i = 0; i < duanlianType.length; i++) {
						if (isNaN(duanlianType[i])) {
							body.find("#qitaDuanlianfangshi").val(duanlianType[i]);
							body.find("#qitaDuanlianfangshi").parent().css("display", "block");
							duanlianType[i] = 4;
						}
					}
					checkboxWrite(body, duanlianType, "duanlianfangshi");
					body.find("#duanlian").css("display", "block");
				} else {
					radioWrite(body, duanlian, "duanlian");
 				}
				
				var paibian = list.paibian.split("@");
				radioWrite(body, paibian[0], "paibian");
				body.find("#paibianPinci").val(paibian[1]);
				
				var qiye = list.qiye.split("@");
				radioWrite(body, qiye[0], "qiye");
				body.find("#qiyePinci").val(qiye[1]);
				
				radioWrite(body, list.shuimian, "shuimian");
				
				var xiyu = list.xiyu.split("@");
				radioWrite(body, xiyu[0], "xiyu");
				body.find("#xiyuPinci").val(xiyu[1]);
				
				body.find("#qitaqingkuang").val(list.otherInfo);
				
				radioWrite(body, list.fuju, "fuju");
				radioWrite(body, list.sizhi, "sizhi");
				radioWrite(body, list.guanjie, "guanjie");
				radioWrite(body, list.jingmaiquzhang, "jingmaiquzhang");
				radioWrite(body, list.shuizhong, "shuizhong");
				radioWrite(body, list.xingzou, "xingzou");
				radioWrite(body, list.louti, "louti");
				radioWrite(body, list.zhuangkuang, "zhuangkuang");
				
				var fujuleixing = list.fujuleixing.split(";");
				checkboxWrite(body, fujuleixing, "fujuleixing");
				
				var zhaohu = list.zhaohu.split(";");
				checkboxWrite(body, zhaohu, "zhaohu");
				if (list.zhaohu.indexOf("-其他-") >= 0) {
					var vvv = list.zhaohu.substring(list.zhaohu.indexOf("-其他-") + 4);
					body.find("#zhaohuQita").val(vvv);
				}
				
				var shenghuo = list.shenghuo.split(";");
				checkboxWrite(body, shenghuo, "shenghuo");
				
				
				var yiliao = list.yiliao.split(";");
				checkboxWrite(body, yiliao, "yiliao");
				if (list.yiliao.indexOf("-其他-") >= 0) {
					var vvv = list.yiliao.substring(list.yiliao.indexOf("-其他-") + 4);
					body.find("#yiliaoQita").val(vvv);
				}
				
				var anquan = list.anquan.split(";");
				checkboxWrite(body, anquan, "anquan");
				if (list.anquan.indexOf("-其他-") >= 0) {
					var vvv = list.anquan.substring(list.anquan.indexOf("-其他-") + 4);
					body.find("#anquanQita").val(vvv);
				}
				
				
				body.find("#inp").click();
			},
			yes : function(index, layero) {
				var body = layer.getChildFrame('body', index);
				var rtnJson = getAllValue(body);
				rtnJson["id"] = id;
				 $.ajax({
					 url: basePath + "oldInfo/updateOldInfo.do",
					 data: rtnJson,
					 type: "post",
					 dataType: "json",
					 success: function(data) {
						 if (data.rtn == 1) {
							 layer.alert("修改成功", function() {
								 layer.closeAll();
							 });
						 }
						 searchInfo();
					 },
					 error:function(){
					 }
				 })
			},
			end : function() {
			}
		});
	});
		 }
	 })
}


function checkboxWrite(body, valArr, name) {
	var arr = body.find("[name='" + name + "']");
	for (var i = 0; i < arr.length; i++) {
		var chkVal = $(arr[i]).val();
		for (var j = 0; j < valArr.length; j++) {
			var val = valArr[j] + "";
			if (val.indexOf("-其他-") >= 0) {
				val = "其他";
			}
			if (chkVal == val) {
				$(arr[i]).prop("checked", "checked");
			}
		}
	}
}


function deleteOld(id) {
	layui.confirm("确定删除?", {
		icon : 5
	}, function() {
		alert(1);
	})
}

function showHealthy(id) {
	window.open(basePath + 'turn/toHealth.do?id=' + id);
}

function bindDevice(event) {
	var objStr = $(event).attr("msg");
	var obj = eval("(" + objStr + ")");
	layui.use([ 'layer' ], function() {
		var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer
				: parent.layer;
		layer.open({
			type : 2,
			title : '绑定设备',
			content : basePath + 'popups/pension/bindDevice.jsp',
			btn : [ '确定', '取消' ],
			area : [ '600px', '500px' ],
			success : function(layero, index) {

			},
			yes : function(index, layero) {
				var body = layer.getChildFrame('body', index);
			}
		})
	});
}

function radioWrite(body, val, name) {
	var arr = body.find("[name='" + name + "']");
	for (var i = 0; i < arr.length; i++) {
		var elem = $(arr[i]);
		if (elem.val() == val) {
			elem.prop("checked", "checked");
			$(elem).click();
		} else {
			elem.prop("checked", "");
		}
	}
}

// 查看全部信息selectManager
function selectManager(event) {
	var objStr = $(event).attr("msg");
	var obj = eval("(" + objStr + ")");
	layui.use([ 'layer' ], function() {
		var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer
				: parent.layer;
		layer.open({
			type : 2,
			title : '全部信息',
			content : basePath + 'popups/pension/ckMangager.jsp',
			btn : [ '确定', '取消' ],
			area : [ '1000px', '800px' ],
			success : function(layero, index) {

			},
			yes : function(index, layero) {
				var body = layer.getChildFrame('body', index);
			}
		})
	});
}


	
 

function deleteOlder(event) {
	var objStr = $(event).attr("msg");
	layui.layer.confirm("确定删除?", function() {
		$.ajax({
			url : basePath + "pension/deleteOlder.do",
			data : eval("(" + objStr + ")"),
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.flag == 1) {
					layui.layer.alert("删除成功", function() {
						layui.layer.closeAll();
						searchInfo();
					});
				}
			}
		});
	})
}


function getAllValue(body) {
	var json = {};
	
	// 个人基本信息取值
	var peopleName = body.find("#peopleName").val(); // 姓名
	json["name"] = peopleName;
	var sex = body.find("input[name='sex']:checked").val(); // 性别
	json["sex"] = sex;
	var birthday = body.find("#birthday").val(); // 出生日期
	json["birthday"] = new Date(birthday).getTime();
	
	
	
	var community = body.find("#community").val().split("&"); // 详细地址
	json["communityId"] = community[0];
	json["communityName"] = community[1];
	var address = body.find("#address").val(); // 详细地址
	json["address"] = address;
	var cencusAddr = body.find("#cencusAddr").val(); // 详细地址
	json["cencusAddr"] = cencusAddr;
	var cellphone = body.find("#cellphone").val(); // 家庭电话
	json["phone"] = cellphone;
	var linkName = body.find("#linkName").val(); // 紧急联系人姓名
	json["linkName"] = linkName;
	var linkPhone = body.find("#linkPhone").val(); // 紧急联系人电话
	json["linkPhone"] = linkPhone;
	var huji = body.find("input[name='huji']:checked").val(); // 是否户籍所在地
	json["isCensus"] = huji;
	var nation = body.find("#nation").val(); // 民族
	json["nation"] = nation;
	var workState = body.find("input[name='workState']:checked").val(); // 职业状态
	json["workState"] = workState;
	var work = body.find("#work").val(); // 职业详情
	json["workInfo"] = work;
	var oldType = body.find("#oldType").val(); // 老人类型
	if (oldType == '其他') {
		oldType = '其他&' + body.find("#oldTypeOther").val();
	}
	json["oldType"] = oldType;
	var yanglao = body.find("#yanglao").val(); // 居家养老情况
	json["yanglao"] = yanglao;
	var zhifu = body.find("#zhifu").val(); // 医疗费用支付
	json["payType"] = zhifu;
	var payTarget = body.find("#payTarget").val(); // 其他方式详情
	json["payTarget"] = payTarget;
	var bloodType = body.find("#bloodType").val(); // 其他方式详情
	json["bloodType"] = bloodType;
	var guomin = body.find("#guomin").val(); // 药物过敏史
	json["allergy"] = guomin;
	var guominOther = body.find("#guominOther").val(); // 药物过敏史其他
	json["allergyTarget"] = guominOther;
	var baolu = body.find("#baolu").val(); // 暴露史
	json["expose"] = baolu;
	var baoluOther = body.find("#baoluOther").val(); // 暴露史其他
	json["exposeTarget"] = baoluOther;
	var idCard = body.find("#idCard").val(); // 暴露史其他
	json["idCard"] = idCard;
	var maritalStatus = body.find("#maritalStatus").val(); // 暴露史其他
	json["maritalStatus"] = maritalStatus;
	var income = body.find("#income").val(); // 暴露史其他
	json["income"] = income;

	var bingArr = body.find(".jibing");
	var jibing = "";
	var qitaBing = "";
	for (var i = 0; i < bingArr.length; i++) {
		var bingType = $(bingArr[i]).attr("flag");
		bingType = bingType.substring(3);
		if (bingType == '其他') {
			var vals = $(bingArr[i]).find("input");
			var bingName = $(vals[0]).val();
			var bingDate = $(vals[1]).val();
			qitaBing += "," + bingName + "&" + bingDate;
		} else {
			var bingDate = $(bingArr[i]).find("input").val();
			jibing += ";" + bingType + "&" + bingDate;
		}
	}
	if (jibing.length > 0) {
		jibing = jibing.substring(1);
	}
	if (qitaBing.length > 0) {
		qitaBing = qitaBing.substring(1);
		jibing += ";@" + qitaBing;
	}
	json["jibing"] = jibing;
	var shoushuArr = body.find(".shoushu");
	var shoushu = "";
	if (shoushuArr.length > 0) {
		for (var i = 0; i < shoushuArr.length; i++) {
			var inputArr = $(shoushuArr[i]).find("input");
			var shoushuName = $(inputArr[0]).val();
			var shoushuDate = $(inputArr[1]).val();
			shoushu += ";" + shoushuName + "&" + shoushuDate;
		}
		shoushu = shoushu.substring(1);
	} else {
		shoushu = "";
	}
	json["shoushu"] = shoushu;
	
	
	var waishangArr = body.find(".waishang");
	var waishang = "";
	if (waishangArr.length > 0) {
		for (var i = 0; i < waishangArr.length; i++) {
			var inputArr = $(waishangArr[i]).find("input");
			var waishangName = $(inputArr[0]).val();
			var waishangDate = $(inputArr[1]).val();
			waishang += ";" + waishangName + "&" + waishangDate;
		}
		waishang = waishang.substring(1);
	} else {
		waishang = "";
	}
	json["waishang"] = waishang;

	var shuxueArr = body.find(".shuxue");
	var shuxue = "";
	if (shuxueArr.length > 0) {
		for (var i = 0; i < shuxueArr.length; i++) {
			var inputArr = $(shuxueArr[i]).find("input");
			var shuxueName = $(inputArr[0]).val();
			var shuxueDate = $(inputArr[1]).val();
			shuxue += ";" + shuxueName + "&" + shuxueDate;
		}
		shuxue = shuxue.substring(1);
	} else {
		shuxue = "";
	}
	json["shuxue"] = shuxue;

	

	var fatherCheckAtt = body.find("#father").find("input");
	var father = "";
	for (var i = 0; i < fatherCheckAtt.length; i++) {
		if ($(fatherCheckAtt[i]).attr("checked") == "checked") {
			father += ";" + $(fatherCheckAtt[i]).attr("value");
		}
	}
	var fatherOther = body.find("#fatherOther").val();
	if (fatherOther != "") {
		father += ";&" + fatherOther;
	}
	if (father.length > 0) {
		father = father.substring(1);
	}
	json["father"] = father;
	
	// /////////
	var motherCheckAtt = body.find("#mother").find("input");
	var mother = "";
	for (var i = 0; i < motherCheckAtt.length; i++) {
		if ($(motherCheckAtt[i]).attr("checked") == "checked") {
			mother += ";" + $(motherCheckAtt[i]).attr("value");
		}
	}
	var motherOther = body.find("#motherOther").val();
	if (motherOther != "") {
		mother += ";&" + motherOther;
	}
	if (mother.length > 0) {
		mother = mother.substring(1);
	}
	json["mother"] = mother;
	
	// //////////////////////
	var brotherCheckAtt = body.find("#brother").find("input");
	var brother = "";
	for (var i = 0; i < brotherCheckAtt.length; i++) {
		if ($(brotherCheckAtt[i]).attr("checked") == "checked") {
			brother += ";" + $(brotherCheckAtt[i]).attr("value");
		}
	}
	var brotherOther = body.find("#brotherOther").val();
	if (brotherOther != "") {
		brother += ";&" + brotherOther;
	}
	if (brother.length > 0) {
		brother = brother.substring(1);
	}
	json["brother"] = brother;
	/////////////////////
	var sonCheckAtt = body.find("#son").find("input");
	var son = "";
	for (var i = 0; i < sonCheckAtt.length; i++) {
		if ($(sonCheckAtt[i]).attr("checked") == "checked") {
			son += ";" + $(sonCheckAtt[i]).attr("value");
		}
	}
	var sonOther = body.find("#sonOther").val();
	if (sonOther != "") {
		son += ";&" + sonOther;
	}
	if (son.length > 0) {
		son = son.substring(1);
	}
	json["sonSister"] = son;

	var yichuan = "";
	var yichuanbing = body.find("#yichuanbing").val();
	if (yichuanbing != "") {
		yichuan = yichuanbing;
	}
	json["yichuan"] = yichuan;
	
	var canji = body.find("input[name='canji']:checked").val(); // 职业状态
	if (canji == 1) {
		var canjihao = body.find("#canjihao").val();
		var canjiStr = body.find("#canjiStr").val();
		if (canjihao != "") {
			canji = canjihao;
		}
		if (canjiStr != "") {
			canjiStr = canjiStr.substring(1);
		}
		canji = canjiStr + "&" + canjihao;
	}
	json["canji"] = canji;

	// 疾病用药情况取值
	var fuyao = body.find("input[name='fuyao']:checked").val(); // 服药依从性
	json["fuyao"] = fuyao;
	var kaiyao = body.find("input[name='kaiyao']:checked").val(); // 开药周期
	if (kaiyao == 0) {
		var kaiyaoDay = body.find("#kaiyaoDay").val(); // 开药日期
		kaiyao = "0,"+kaiyaoDay;
	} else if (kaiyao == 1) {
		var kaiyaoDate = body.find("#kaiyaoDate").val(); // 开药日期
		kaiyao = "1,"+kaiyaoDate;
	}
	json["kaiyao"] = kaiyao;
	var drugStoreArr = body.find("[name='drugStore']"); //开药医院名称
	var drugStore = "";
	for (var i = 0; i < drugStoreArr.length; i++) {
		drugStore += ";" + $(drugStoreArr[i]).val();
	}
	if (drugStore.length > 0) {
		drugStore = drugStore.substring(1);
	}
	json["drugStore"] = drugStore;
	//药单div
	var trs = body.find("#yaoTbody").find("tr");
	var drugList = "";
	for (var i = 0; i < trs.length; i++) {
		drugList += "@";
		var tr = $(trs[i]);
		var tds = tr.find("td");
		var listI = "";
		for (var j = 0; j < tds.length; j++) {
			if (j != tds.length - 1) {
				var tdVal = $(tds[j]).find("input").val();
				listI += "&" + tdVal;
			}
		}
		listI = listI.substring(1);
		drugList += listI;
	}
	if (drugList.length > 0) {
		drugList = drugList.substring(1);
	}
	json["drugList"] = drugList;
	var xiyang = body.find("#xiyang").val(); //吸氧
	json["xiyang"] = xiyang;
	var liugan = body.find("input[name='liugan']:checked").val(); // 流感疫苗接种
	json["liugan"] = liugan;
	var feiyan = body.find("input[name='feiyan']:checked").val(); // 肺炎疫苗接种
	json["feiyan"] = feiyan;
	var otherVaccine = body.find("#otherVaccine").val(); //其他疫苗
	json["otherVaccine"] = otherVaccine;
	
	
	//补充评估信息
	var jiawuAtt = body.find("[name='jiawu']"); // 家务
	var jiawu = "";
	for (var i = 0; i < jiawuAtt.length; i++) {
		if ($(jiawuAtt[i]).attr("checked") == "checked") {
			if ($(jiawuAtt[i]).attr("value") == 5) {
				jiawu += ";" + body.find("#qitaJiawu").val();
			} else {
				jiawu += ";" + $(jiawuAtt[i]).attr("value");
			}
		}
	}
	if (jiawu.length > 0) {
		jiawu = jiawu.substring(1);
	}
	json["jiawu"] = jiawu;
	
	
	var clearAtt = body.find("[name='clear']"); // 洗衣
	var xiyi = "";
	for (var i = 0; i < clearAtt.length; i++) {
		if ($(clearAtt[i]).attr("checked") == "checked") {
			xiyi += ";" + $(clearAtt[i]).attr("value");
		}
	}
	if (xiyi.length > 0) {
		xiyi = xiyi.substring(1);
	}
	json["xiyi"] = xiyi;
	
	
	var zhushiAtt = body.find("[name='zhushi']"); // 主食
	var zhushi = "";
	for (var i = 0; i < zhushiAtt.length; i++) {
		if ($(zhushiAtt[i]).attr("checked") == "checked") {
			if ($(zhushiAtt[i]).attr("value") == 3) {
				zhushi += ";" + body.find("#qitaZhushi").val();
			} else {
				zhushi += ";" + $(zhushiAtt[i]).attr("value");
			}
		}
	}
	if (zhushi.length > 0) {
		zhushi = zhushi.substring(1);
	}
	json["zhushi"] = zhushi;
	
	var fushiAtt = body.find("[name='fushi']"); // 副食
	var fushi = "";
	for (var i = 0; i < fushiAtt.length; i++) {
		if ($(fushiAtt[i]).attr("checked") == "checked") {
			if ($(fushiAtt[i]).attr("value") == 3) {
				fushi += ";" + body.find("#qitaFushi").val();
			} else {
				fushi += ";" + $(fushiAtt[i]).attr("value");
			}
		}
	}
	if (fushi.length > 0) {
		fushi = fushi.substring(1);
	}
	json["fushi"] = fushi;
	
	var shiwuguominAtt = body.find("[name='shiwuguomin']"); // 食物过敏
	var shiwuguomin = "";
	for (var i = 0; i < shiwuguominAtt.length; i++) {
		if ($(shiwuguominAtt[i]).attr("checked") == "checked") {
			if ($(shiwuguominAtt[i]).attr("value") == 4) {
				shiwuguomin += ";" + body.find("#qitaShiwuguomin").val();
			} else {
				shiwuguomin += ";" + $(shiwuguominAtt[i]).attr("value");
			}
		}
	}
	if (shiwuguomin.length > 0) {
		shiwuguomin = shiwuguomin.substring(1);
	}
	json["shiwuguomin"] = shiwuguomin;
	
	var duanlian = body.find("input[name='duanlian']:checked").val(); // 锻炼
	if (duanlian == 1) {
		var duanlianpinci = body.find("input[name='duanlianpinci']:checked").val(); // 锻炼
		if (duanlianpinci == 1) {
			duanlianpinci = body.find("#pinci").val(); //每周频次
		}
		duanlian = duanlianpinci;
		var duanlianshijian = body.find("input[name='duanlianshijian']:checked").val(); // 锻炼时间
		duanlian = duanlian + ";" + duanlianshijian;
		var jianchi = body.find("#jianchi").val(); //坚持XX年
		duanlian = duanlian + ";" + jianchi;
		
		var duanlianfangshiAtt = body.find("[name='duanlianfangshi']"); // 锻炼方式
		var duanlianfangshi = "";
		for (var i = 0; i < duanlianfangshiAtt.length; i++) {
			if ($(duanlianfangshiAtt[i]).attr("checked") == "checked") {
				if ($(duanlianfangshiAtt[i]).attr("value") == 4) {
					duanlianfangshi += "&" + body.find("#qitaDuanlianfangshi").val();
				} else {
					duanlianfangshi += "&" + $(duanlianfangshiAtt[i]).attr("value");
				}
			}
		}
		if (duanlianfangshi.length > 0) {
			duanlianfangshi = duanlianfangshi.substring(1);
		}
		duanlian = duanlian + ";" + duanlianfangshi;
	}
	json["duanlian"] = duanlian;
	
	var paibian = body.find("input[name='paibian']:checked").val(); // 排便
	var paibianPinci = body.find("#paibianPinci").val(); // 排便频次
	json["paibian"] = paibian + "@" + paibianPinci;
	
	var qiye = body.find("input[name='qiye']:checked").val(); // 是否起夜
	var qiyePinci = body.find("#qiyePinci").val(); // 起夜频次
	json["qiye"] = qiye + "@" + qiyePinci;
	
	var shuimian = body.find("input[name='shuimian']:checked").val(); // 睡眠质量
	json["shuimian"] = shuimian;
	
	var xiyu = body.find("input[name='xiyu']:checked").val(); // 睡眠质量
	var xiyuPinci = body.find("#xiyuPinci").val(); // 洗浴频次
	json["xiyu"] = xiyu + "@" + xiyuPinci;
	
	var qitaqingkuang = body.find("#qitaqingkuang").val(); // 其他情况
	json["otherInfo"] = qitaqingkuang;
	
	var fuju = body.find("input[name='fuju']:checked").val(); // 是否使用辅具
	json["fuju"] = fuju;
	var fujuleixingAtt = body.find("#fujuleixing").find("input"); // 辅具类型
	var fujuleixing = "";
	for (var i = 0; i < fujuleixingAtt.length; i++) {
		if ($(fujuleixingAtt[i]).attr("checked") == "checked") {
			if ($(fujuleixingAtt[i]).attr("value") == "其他") {
				fujuleixing += ";" + body.find("#yiliaoQita").val();
			} else {
				fujuleixing += ";" + $(fujuleixingAtt[i]).attr("value");
			}
		}
	}
	if (fujuleixing.length > 0) {
		fujuleixing = fujuleixing.substring(1);
	}
	json["fujuleixing"] = fujuleixing;
	
	var sizhi = body.find("input[name='sizhi']:checked").val(); // 四肢情况
	json["sizhi"] = sizhi;
	var guanjie = body.find("input[name='guanjie']:checked").val(); // 是否关节畸形
	json["guanjie"] = guanjie;
	var jingmaiquzhang = body.find("input[name='jingmaiquzhang']:checked").val(); // 下肢静脉曲张情况
	json["jingmaiquzhang"] = jingmaiquzhang;
	var shuizhong = body.find("input[name='shuizhong']:checked").val(); // 下肢水肿
	json["shuizhong"] = shuizhong;
	var xingzou = body.find("input[name='xingzou']:checked").val(); // 平地行走
	json["xingzou"] = xingzou;
	var louti = body.find("input[name='louti']:checked").val(); // 上下楼梯
	json["louti"] = louti;
	var zhuangkuang = body.find("input[name='zhuangkuang']:checked").val(); // 身体健康状况
	json["zhuangkuang"] = zhuangkuang;
	
	var zhaohuAtt = body.find("#zhaohu").find("input"); // 辅具类型
	var zhaohu = "";
	for (var i = 0; i < zhaohuAtt.length; i++) {
		if ($(zhaohuAtt[i]).attr("checked") == "checked") {
			if ($(zhaohuAtt[i]).attr("value") == "其他") {
				zhaohu += ";-其他-" + body.find("#zhaohuQita").val();
			} else {
				zhaohu += ";" + $(zhaohuAtt[i]).attr("value");
			}
		}
	}
	if (zhaohu.length > 0) {
		zhaohu = zhaohu.substring(1);
	}
	json["zhaohu"] = zhaohu;	
	
	var shenghuoAtt = body.find("#shenghuo").find("input");
	var shenghuo = "";
	for (var i = 0; i < shenghuoAtt.length; i++) {
		if ($(shenghuoAtt[i]).attr("checked") == "checked") {
			shenghuo += ";" + $(shenghuoAtt[i]).attr("value");
		}
	}
	if (shenghuo.length > 0) {
		shenghuo = shenghuo.substring(1);
	}
	json["shenghuo"] = shenghuo;
	
	var yiliaoAtt = body.find("#yiliao").find("input");
	var yiliao = "";
	for (var i = 0; i < yiliaoAtt.length; i++) {
		if ($(yiliaoAtt[i]).attr("checked") == "checked") {
			if ($(yiliaoAtt[i]).attr("value") == "其他") {
				yiliao += ";-其他-" + body.find("#yiliaoQita").val();
			} else {
				yiliao += ";" + $(yiliaoAtt[i]).attr("value");
			}
		}
	}
	if (yiliao.length > 0) {
		yiliao = yiliao.substring(1);
	}
	json["yiliao"] = yiliao;
	
	var anquanAtt = body.find("#anquan").find("input");
	var anquan = "";
	for (var i = 0; i < anquanAtt.length; i++) {
		if ($(anquanAtt[i]).attr("checked") == "checked") {
			if ($(anquanAtt[i]).attr("value") == "其他") {
				anquan += ";-其他-" + body.find("#anquanQita").val();
			} else {
				anquan += ";" + $(anquanAtt[i]).attr("value");
			}
		}
	}
	if (anquan.length > 0) {
		anquan = anquan.substring(1);
	}
	json["anquan"] = anquan;
	
	
	return json;
}
