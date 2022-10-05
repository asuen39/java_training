/**
 * top
 */
 
// document.write("Hello, World!");

//戻る
function Return(){   
	
	var toi_number = document.querySelector('.toi_number');
	console.log(toi_number);

    //画面遷移
    document.location.href= "/java_training/edit?edit_id=" + toi_number;
}