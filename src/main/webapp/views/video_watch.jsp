<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/views/layout/modal_input.jsp"></jsp:include>
	<header>
		<jsp:include page="/views/layout/header.jsp"></jsp:include>
	</header>
	
	<div class="body-content">
       	<jsp:include page="/views/layout/breadcrumb.jsp"></jsp:include>
       	
        <div class="video">
            <div class="video-frame">
                <span class="video-report" style="margin: 5px;">
                    Báo lỗi
                </span>
                <video src="https://www.w3schools.com/tags/movie.mp4"></video>
            </div>
            <div class="video-episode">
                <span>Tập 1 </span>
                <span>Tập 1 </span>
                <span>Tập 1 </span>
                <span>Tập 1 </span>
                <span>Tập 13 </span>
            </div>
        </div>
        <jsp:include page="/views/layout/comment.jsp"></jsp:include>
    </div>
    
    <style>
        .video-frame {
            margin: 15px;
            border: 1px solid wheat;
        }

        .video-frame video {
            display: block;
            margin: 0 auto;
        }

        .video-report {
            float: right;
            border: 1px solid gainsboro;
            padding: 2px;
            border-radius: 5px;
            cursor: pointer;
        }

        .video-episode {
            display: grid;
            grid-template-columns: repeat(auto-fill, 45px);
            gap: 10px;
            border: 1px solid gainsboro;
            padding: 5px;
            margin: 10px;
        }

        .video-episode span {
            font-size: 12px;
            border: 1px solid gray;
            text-align: center;
            border-radius: 5px;
            padding: 2px;
            cursor: pointer;
        }

        .video-episode span:hover {
            background-color: papayawhip;
        }

    </style>
	
	<footer>
		<jsp:include page="/views/layout/footer.jsp"></jsp:include>
	</footer>
</body>
</html>