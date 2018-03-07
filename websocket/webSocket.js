/**
 * 
 */
$(document).ready(function(){
	if (location.pathname !== "/main.sqi" && location.pathname !== "/user/logout.sqi"){
		
		var webSocket = new WebSocket('wss://'+location.host+'/broadcasting');
		
		webSocket.onerror = function() {
			//is empty
		}
		
		webSocket.onopen = function() {
			//is empty
		}
		
		webSocket.onmessage = function(event) {
			
			var ms = event.data.split(",")[0];
			var ho = event.data.split(",")[1];
			
			if (ms === $('#user_id').val()) {
				post_goto("/user/logout.sqi",{'logout':'2','ho':ho},'');
				
			} else if (event.data === "refresh" || ms === "refresh"|| checkHo(ho)) {
				if ($('#isWorking').val() !== "YES" && $('#isUpload').val() !== "YES" ) {
					location.reload(true);
				}
				
			} else if (ms === "alert") {
				if (ho === "10" || ho ==="11" || ho ==="12") {
					
					if (ho === "10") {
						ho = "새로운 결재요청이 들어왔습니다."
					} else if (ho === "11") {
						ho = "결재 승인 처리되었습니다."
					} else if (ho === "12") {
						ho = "결재 반려 처리되었습니다."
					}
					
					alertReloadModalOn(ho, location);
				} else {
					alertModalOn(ho);
				}
			}
		}
	}
});

function checkHo(ho){
	return ho === location.pathname || ho === "all" || ho === "force";
}

function closeWebSocket() {
	webSocket.close();
}
