<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
<!-- CSS読み込み -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="global_area">
		<div class="top_area">
			<div class="top_box">
				<button class="auto-right logout_button">Top</button>
				<button class="auto-right logout_button">logout</button>
			</div>
			<div class="main_area">
				<ul>
					<li class="edit_area space_between">1
						<label class="label_textarea"></label>
					</li>
					<li class="edit_area space_between">回答
						<input type="text" style="width:93%;">
					</li>
					<li class="edit_area space_between">2
						<label class="label_textarea"></label>
					</li>
					<li class="edit_area space_between">回答
						<input type="text" style="width:93%;">
					</li>
				</ul>
			</div>
			<div class="saiten_box">
				<button class="auto-right logout_button">採点</button>
			</div>
		</div>
	</div>
</body>
</html>