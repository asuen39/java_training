/**
 * top
 */
 
// document.write("Hello, World!");

//ログアウト
function Logout(){   
    //画面遷移
    document.location.href= "./login";
}

//問題と答えを確認・登録
function Question(){
    console.log("clickされた");
    
    //画面遷移
    document.location.href= "./list";
}

//テスト
function Test(){   
    //画面遷移
    document.location.href= "./test";
}

//過去の採点結果s
function PastGradeResults(){   
    //画面遷移
    document.location.href= "./history";
}