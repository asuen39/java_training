/**
 * top
 */
 
// document.write("Hello, World!");

//戻る

//テキストボックス追加

function Return(){
	
	var questionId = document.querySelector('.questionId');
	
	//画面遷移
    document.location.href= "../edit?edit_id=" + questionId.value;
}