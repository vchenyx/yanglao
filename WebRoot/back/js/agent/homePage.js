layui.config({
	base: basePath + 'back/plugins/layui/modules/'
});

var isOpen = true;
var open_close = false;

init();

//初始化（表格、单选框初始化）
function init(){
	layui.use(['layer','form'], function() {
		$ = layui.jquery,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
		
	});
}

layui.use(['layer','form'], function() {
	$ = layui.jquery,
	form = layui.form,
	layer = parent.layer === undefined ? layui.layer : parent.layer;
});





