<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>history</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="global_area">
		<div class="top_area">
			<div class="result_title_area">
				<h2 class="result_title">履歴</h2>
				<div class="top_box align_center">
					<button class="auto-right logout_button">Top</button>
					<button class="auto-right logout_button">logout</button>
				</div>
			</div>
			<div class="main_area">
				<table class="history_table">
					<tr>
						<td>氏名</td>
						<td>得点</td>
						<td>採点時間</td>
					</tr>
					<tr>
						<td>松村</td>
						<td>77点</td>
						<td>2019/06/23　12:00:13</td>
					</tr>
					<tr>
						<td>松村</td>
						<td>77点</td>
						<td>2019/06/23　12:00:13</td>
					</tr>
					<tr>
						<td>松村</td>
						<td>77点</td>
						<td>2019/06/23　12:00:13</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>