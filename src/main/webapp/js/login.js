/**
 * login
 */
 
 $(function(){
	//inputに入力時に判定する。
	$(document).on('input','.inputLoginId', function(e){
		//Ajaxで非同期通信をさせている。
		$.ajax({
			url: "./AjaxServlet",
			type: "GET",
			data: {num : $(".inputLoginId").val()},
			dataType: "JSON",
			success: function(result){
				//AjaxServletからresultの値を受け取る。
				if(result == 1){
					$("#text1").html("該当IDあります");
				} else {
					$("#text1").html("IDないです");
				}
		    },
		    error: function(){
		      // エラー処理
		    }

		});
	});
});