/**
 * register
 */

//戻る
function Return(){   
    //画面遷移
    document.location.href= "./list";
}

//テキストボックス追加
document.addEventListener('DOMContentLoaded', function(load){
	//親要素の取得
	var parent = document.getElementById('ul_list');
	
	
	//一つ目の削除ボタン排除
	function execFunction(){
		
		//ul_listの1番目を取得。取得したら下層にあるボタンを指定
		var list0 = parent.children[0].children[1];
		//1番目リストのボタンを削除。
		list0.remove();
	}
	
	//追加ボタンイベント
	function AdditionClick(){
		//list要素を子要素ごとコピー
		var close_area = document.querySelector('.addition_content').cloneNode(true);
		
		//inputの入力内容を削除
		close_area.children[0].value = "";
		
		//ul要素の最後に追加
		parent.appendChild(close_area);
	}
	
	// 引数に指定したclassの値をもつ要素を取得
	const addition_btn = document.getElementById('addition_btn');
	
	//ボタンを関係の処理設置
	addition_btn.addEventListener('click' , AdditionClick);
	
	//画面読み込み時に実行
	window.addEventListener("load", execFunction);
	
});

//テキストボックス削除
//thisで渡るようにしているから識別不要
function delete_btn(obj){
	// thisで押された場所を明確にさせる。親元指定後に削除
	obj.parentNode.remove();
}