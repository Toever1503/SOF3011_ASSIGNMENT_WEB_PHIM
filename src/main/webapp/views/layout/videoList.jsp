<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="video-container">
	<c:forEach var="video" items="${videoList }">
		<div class="video-item">
			<a
				href="${pageContext.request.contextPath}/film/${video.title }/${video.id}">
				<div class="video-image"
					style="background-image: url('${video.imageBanner}'); background-size: cover; min-height: 250px;">
					<!-- 					<img src="https://animenews.life//uploads/chars/chara06shin_a.png" alt=""> -->
				</div>
				<div class="video-title">
					<h4>${video.title}</h4>
				</div>
			</a>
			<div class="video-action" data-id='${video.id }'>
				<div class="video-like-action">Like
				</div>
				<div class="video-share-action">Share</div>
			</div>
		</div>
	</c:forEach>

	<script type="text/javascript">
		let videoLikes = document.getElementsByClassName('video-like-action');
		for(let i = 0; i< videoLikes.length; ++i){
			videoLikes[i].addEventListener('click', function(){
				let likeBtn = this;
				fetch("http://localhost:8080/SOF3011_ASSIGNMENT/ajaxAdmin?action=likeVideo&video="+this.parentElement.dataset.id)
				.then(res=> res.json())
				.then(data=>{
				console.log(data)
					if(data.result =='success'){
						if(data.type =='remove'){
							likeBtn.innerHTML = 'Like';
						}
						else{
							likeBtn.innerHTML = 'Unlike';
						}
					}else{
						alert('Server got error, code: -1');
					}
				}).catch(error=> {
					alert('You need login to like');
				});
			});
		}
		
	</script>
</div>

<div class="video_page">
	<div class="page_index">
		<c:if test="${total !=0 }">
			<c:if test="${page != total}">
				<span style="background: #b0f9a7"><a href="?page=${page }">${page}</a></span>
			</c:if>
			<span><a href="?page=${page+2 < total ? page: page-1 }">${page+2 < total ? page: page-1 }</a></span>
			<span><a href="?page=${page+2 < total ? page: page-2 }">${page+2 < total ? page: page-2 }</a></span>
			
			<span><a href="?page=${page == total ? total-1 : page+1}">...</a></span>
			<c:if test="${page < $total-1 }">
				<span><a href="?page=${total -1}">${total -1}</a></span>
			</c:if>
			<c:if test="${page==total || page==total-1 }">
				<span><a href="?page=${total }">${total }</a></span>
			</c:if>
		</c:if>
		<c:if test="${total ==0 }">
			<span><a href="#">1</a></span>
		</c:if>
	</div>
	<style>
                .video_page {
                    margin: 10px auto;
                    max-width: 230px;
                }
                .video_page .page_index{
                    display: grid;
                    gap: 3px;
                    grid-template-columns: repeat(auto-fill, 35px)
                }
                .video_page .page_index span{
                    border: 1px solid gainsboro;
                    text-align: center;
                    border-radius: 5px;
                    font-size: 13px;
                    transition: 0.4s ease-in-out;
                }
                .video_page .page_index span:hover{
                    background-color: #b0f9a7;
                }
	</style>
</div>
<style>
.body-container .video-container {
	display: grid;
	grid-template-columns: repeat(auto-fill, 20%);
}

.body-container .video-container .video-item {
	height: min-height;
	text-align: center;
	background: azure;
	outline-style: double;
	outline-color: aqua;
	border-radius: 4px;
	margin: 10px 5px;
}

.body-container .video-container .video-item .video-title {
	font-size: 12px;
	margin-top: 5px;
}

.body-container .video-container .video-item .video-action div {
	display: inline-block;
	margin: 10px 5px;
	border: 1px solid gainsboro;
	padding: 5px;
	border-radius: 10px;
	background: honeydew;
	cursor: pointer;
	transition: 0.3s ease-in-out;
	font-size: 12px;
}

.body-container .video-container .video-item .video-action div:hover {
	background: cyan
}
</style>
<!-- end container -->

<script type="text/javascript">
document.querySelectorAll('.video-container > .video-item > .video-action > .video-share-action')
.forEach(element=>{
	element.addEventListener('click', ()=>{
		this.parentElement.dataset.id
	})
})
</script>