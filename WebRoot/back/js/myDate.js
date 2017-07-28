var myDate = {
	
};

myDate = {
		longToDate: function(longTime) {
			
			var date = new Date(parseInt(longTime));
			var year = date.getFullYear();
			var month = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
			var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
			var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
			var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
			var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
			return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
		},

		longToDateYMD: function(longTime) {
			var date = new Date(parseInt(longTime));
			var year = date.getFullYear();
			var month = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
			var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
			return year + "-" + month + "-" + day;
		},
		
		longToAge: function(longTime) {
			var birth = new Date(parseInt(longTime)).getFullYear();
			var now = new Date().getFullYear();
			return now - birth;
		},
		
		getYMDLong: function(dateStr) {
			var date = new Date(dateStr);
			date.setHours(0, 0, 0, 0);
			return date.getTime();
		}
}

 



