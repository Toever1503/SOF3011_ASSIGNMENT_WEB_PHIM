<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	<%@include file='/static/css/login.css' %>
</style>
<script type="text/javascript">
<%@include file='/static/Js/debouce.js' %>
</script>	
</head>
<body>
	<jsp:include page="layout/header.jsp"></jsp:include>
	<div class="body-content">
		<jsp:include page="layout/aside.jsp"></jsp:include>
		<div class="main-content">
			<div class="upload_image">
				<span onclick="">Quay Lại</span>
				<div class="upload">
					<input type="file" name="upload" style="margin-bottom: 10px;">
					<img width="300px" height="400px"
						src="https://seo-tools.pro/images/free_seo_html_parser_online.png"
						alt="">
				</div>
				<style>
.upload_image span {
	border: 1px solid gainsboro;
	padding: 5px;
	margin: 10px;
	background-color: thistle;
	transition: 0.3s ease-in-out;
	cursor: pointer;
}

.upload_image span:hover {
	background: cornflowerblue;
}

.upload_image {
	padding: 20px;
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	background: #ffeeeeb5;
	z-index: 11;
	display: none;
}

.upload {
	position: absolute;
	width: 500px;
	height: 500px;
	top: 15%;
	left: 40%;
}
</style>
			</div>
			<!-- upload -->

			<div class="route-action">
				<span class="route-item">
					<a href="/SOF3011_ASSIGNMENT/admin/video">Quay lại</a>
				</span>
			</div>

			<section class="video login">
				<form action="" onsubmit='return formValidate(this)'
					style="width: unset" method="post" autocomplete="off">
					<div>
						<label for="title">Tiêu Đề</label>
						 <input type="text" name="title" value="${video.title }"
							id="title" placeholder="Video title............"
							onkeyup='handleInputChange(this)'>
						<div class="hasError">
							<span></span>
						</div>
					</div>
					<div>
						<label for="description">Mô Tả</label>
						<textarea rows="5" name="description" id="description"
							placeholder="Description................"
							onkeyup='handleInputChange(this)'>${video.description }</textarea>
						<div class="hasError">
							<span></span>
						</div>
					</div>

					<div>
						<label for="category">Thể Loại</label> <input name="cats"
							id="category" style="display: none">
						<div class="category container-box">
							<c:forEach var="v_cat" items="${video.categores }">
								<span class="container-item item_checked" data-id='${v_cat.id }'>${v_cat.name }</span>
							</c:forEach>
							
							<c:forEach var="cat" items="${catList }">
								<span class="container-item" data-id='${cat.id }'>${cat.name }</span>
							</c:forEach>
							
							<script type="text/javascript">
							</script>
						</div>
						<div class="hasError">
							<span></span>
						</div>
						<script>
                            addEvenContainerBoxClick(document.getElementsByClassName('container-box')[0])
                            function addEvenContainerBoxClick(box) {
                                let cats = box.children;
                                for (let i = 0; i < cats.length; i++) {
                                    cats[i].addEventListener('click', function () {
                                        this.className = this.className.indexOf('item_checked') == -1 ? (this.className + ' item_checked').trim() : this.className.replace('item_checked', '').trim()
                                    })
                                }
                            }
                        </script>
					</div>

					<div>
						<label for="staff">Staff</label> 
						<input type="text" name="staff" placeholder="Staff name separate betweem by ," id="staff">
						<input type="text" name="addedStaff" id="addedStaff" style="display: none">
						<div class="staff container-box-suggest">
								<c:forEach var="staff" items="${video.staffs}">
									<div>
		                               <span class="container-item" data-id="${staff.id }">${staff.name }</span>
		                                <i></i>
		                            </div>
								</c:forEach>
							<style>
                               .container-box-suggest>div {
                                    border: 1px solid gainsboro;
                                    border-radius: 3px;
                                    padding: 2px;
                                    margin-top: 5px;
                                    background-color: #e6e6ff;
                                    box-shadow: inset 5px;
                                    position: relative;
                                    display: inline-block;
                                    margin: 5px 2px;
                                }

                                .container-box-suggest>div>i::after {
                                    position: absolute;
                                    top: 1px;
                                    right: -1px;
                                    cursor: pointer;
                                    width: 10px;
                                    background: #6ed392;
                                    height: 2px;
                                    transform: rotate(-45deg);
                                    content: '';
                                }
                                .container-box-suggest>div>i::before {
                                    position: absolute;
                                    top: 1px;
                                    right: -1px;
                                    cursor: pointer;
                                    width: 10px;
                                    background: #6ed392ed;
                                    height: 2px;
                                    transform: rotate(45deg);
                                    content: '';
                                }
                            </style>
						</div>
						<div class="suggest-container">
							
						</div>
						<script>
							document.querySelectorAll('.container-box-suggest .container-item + i').forEach(element=>{
                                element.addEventListener('click', (e)=>{
                                    let span  = e.target.parentElement;
                                    span.parentElement.removeChild(span)
                                })
                            });
							let sa;
								
                                function addSuggestClick(container, input) {
                                    let suggests = container.parentElement.querySelector('.suggest-container').children;
                                    
                                    for (let i = 0; i < suggests.length; i++) {
                                        suggests[i].addEventListener('click', function () {
                                        	console.log('click item')
                                        	let divTag = document.createElement('div');
                                            let item = document.createElement('span');
                                            let iTag = document.createElement('i');
                                            
                                            item.className = 'container-item';
                                            item.innerHTML = this.innerHTML;
                                            item.dataset.id = this.dataset.id;
                                            
                                            
                                            divTag.appendChild(item);
                                            divTag.appendChild(iTag);
                                            
                                            container.appendChild(divTag);
                                            
                                            iTag.addEventListener('click', (e)=>{
                                            	console.log('click remove')
			                                    let span  = e.target.parentElement;
			                                    span.parentElement.removeChild(span)
			                                });
                                            
                                            this.parentElement.parentElement.removeChild(this.parentElement);
                                            input.value = '';
                                        })
                                    }
                                }
                                
                                function callApiSuggest(e, container_box, url){
                                	let suggestContainer = e.parentElement.querySelector('.suggest-container');
                                    if(suggestContainer != null)
                                   	   e.parentElement.removeChild(suggestContainer);
                                    
                                    fetch(url)
                                    .then(res => res.json())
                                    .then(data => {
                                    	console.log(data);
                                    	if(data.result=='success')
                                        {
                                    		container_box.parentElement.appendChild(createSuggestContainer(data.data));
											addSuggestClick(staffContainer, e);
                                        }else{
                                        	let sg = e.parentElement.querySelector('.suggest-container');
                                        	console.log(sg)
                                        	if(sg!= null)
                                        		e.parentElement.removeChild(sg);
                                        }
                                    }).catch(error => console.log(error));
                                }
                                
                                function createSuggestContainer(data){ // data only contains  field id and name
                                	suggestContainer = document
                                    .createElement('div');
                                    suggestItem = suggestContainer
                                        .cloneNode(true);
                                    suggestContainer.className = 'suggest-container';
                                    suggestItem.className = 'suggest-item';
                                    
									data.forEach(element => {
										let its = suggestItem.cloneNode(true);
										its.innerHTML = element.name;
										its.dataset.id = element.id;
										suggestContainer.appendChild(its);
               						 });
									return suggestContainer;
                                }
                                
                                document.getElementById('staff').addEventListener('keydown',  debouce((e)=> {
                                	staffContainer = document.getElementsByClassName('staff container-box-suggest')[0];
                                	callApiSuggest(e.target, staffContainer, "http://localhost:8080/SOF3011_ASSIGNMENT/ajaxAdmin?action=staffSearch&search="+e.target.value);
                                	
                                }, 500));
                            </script>
						<style>
.suggest-container {
	position: absolute;
	top: 60px;
	left: 0;
	background: aliceblue;
	border: 1px solid gainsboro;
	z-index: 10;
}

.suggest-container .suggest-item {
	padding: 5px;
	min-width: 250px;
	max-width: 500px;
	cursor: pointer;
}

.suggest-container .suggest-item:hover {
	background-color: bisque;
}
</style>
						<div class="hasError">
							<span></span>
						</div>
					</div>
					
					<div>
						<label for="studio">Studio</label> 
						<input type="text" name="studio" placeholder="Studio name separate betweem by ," id="studio">
						<input type="text" name="addedStudio" id="addedStudio" style="display: none">
						
						<div class="studio container-box-suggest">
								<c:forEach var="studio" items="${video.staffs}">
									<div>
		                               <span class="container-item" data-id="${studio.id }">${studio.name }</span>
		                                <i></i>
		                            </div>
								</c:forEach>
						</div>
						<div class="suggest-container">
						</div>
						
						<script type="text/javascript">
							document.getElementById('studio').addEventListener('keydown',  debouce((e)=> {
	                        	staffContainer = document.getElementsByClassName('studio container-box-suggest')[0];
	                        	callApiSuggest(e.target, staffContainer, "http://localhost:8080/SOF3011_ASSIGNMENT/ajaxAdmin?action=studioSearch&search="+e.target.value);
	                        	
	                        }, 500));
						</script>
						
						<div class="hasError">
							<span></span>
						</div>
					</div>
					
					<div>
						<label for="char">Character</label> 
						<input type="text" name="char" placeholder="Character name separate betweem by ," id="char">
						<input type="text" name="addedChar" id="addedChar" style="display: none">
						<div class="char container-box-suggest">
								<c:forEach var="chars" items="${video.chars}">
									<div>
		                               <span class="container-item" data-id="${chars.id }">${chars.name }</span>
		                                <i></i>
		                            </div>
								</c:forEach>
						</div>
						<div class="suggest-container">
						</div>
						<script type="text/javascript">
							document.getElementById('char').addEventListener('keydown',  debouce((e)=> {
	                        	staffContainer = document.getElementsByClassName('char container-box-suggest')[0];
	                        	callApiSuggest(e.target, staffContainer, "http://localhost:8080/SOF3011_ASSIGNMENT/ajaxAdmin?action=charSearch&search="+e.target.value);
	                        	
	                        }, 500));
						</script>
					</div>
					
					<div>
						<label for="tag">Tag</label> 
						<input type="text" name="tag" placeholder="Tag name separate betweem by ," id="tag">
						<input type="text" name="addedTag" id="addedTag" style="display: none">
						<div class="tag container-box-suggest">
								<c:forEach var="tag" items="${video.chars}">
									<div>
		                               <span class="container-item" data-id="${tag.id }">${tag.name }</span>
		                                <i></i>
		                            </div>
								</c:forEach>
						</div>
						<div class="suggest-container">
						</div>
						<script type="text/javascript">
							document.getElementById('tag').addEventListener('keydown',  debouce((e)=> {
	                        	staffContainer = document.getElementsByClassName('tag container-box-suggest')[0];
	                        	callApiSuggest(e.target, staffContainer, "http://localhost:8080/SOF3011_ASSIGNMENT/ajaxAdmin?action=tagSearch&search="+e.target.value);
	                        	
	                        }, 500));
						</script>
					</div>

					<div>
						<label for="view">Lượt xem:</label> 
						<input type="number"
							name="views" id="view" value="${video.views }" min="0"
							onkeyup='handleInputChange(this)'>
						<div class="hasError">
							<span></span>
						</div>
					</div>

					<div>
						<label for="trailer">Trailer</label> <input type="text" value="${video.trailer}"
							name="trailer" id="trailer" placeholder="Link youtube trailer"
							onkeyup='handleInputChange(this)'>
						<div class="hasError">
							<span></span>
						</div>
					</div>

					<div>
						<label for="imageUpload">Image Banner</label> 
							<input
							style="display: none;" type="file" accept='image/*'
							name="imageUpload" id="imageUpload">
							 <input
							style="display: none; border: none" name='imageBanner' value="${video.imageBanner }"
							id='imageBanner'> 
							<a id="uploadFile"
							style="text-decoration: underline; cursor: pointer; font-size: 14px;">Upload
							ảnh</a> 
							<span class="image_uploaded"
							style="color: cornflowerblue; margin-left: 15px">${video.imageBanner }</span>

						<script type="text/javascript">
							document.addEventListener("DOMContentLoaded", function(){
								document.getElementById("uploadFile").addEventListener('click', function(){
									document.getElementById('imageUpload').click();
							});
							// end choose upfile
							
							document.getElementById("imageUpload").addEventListener('change', function(e){
								console.log("uploading file")
								
								if(e.target.files.length != 0){
									let formData = new FormData();
									formData.enctype='multipart/form-data'>
									formData.append('action', 'uploadImage');
									formData.append('data', e.target.files[0]);
									
									fetch("http://localhost:8080/SOF3011_ASSIGNMENT/uploadFile",{
										method: 'post',
										cache: 'no-cache',
										body: formData
									})
									.then(res=> res.json())
									.then(result=>{
										console.log(result)
										if(result.result ==='success'){
											console.log(result.data[0])
											imgBanner = document.getElementById("imageBanner").value = result.data[0];
											document.getElementsByClassName('image_uploaded')[0].innerHTML = result.data[0].replace(/(\d+\/\d+\/)/, '');
										}
										
									}).catch(error => console.log(error))
								}
								
							});
							// end up file
							});

						
							
							</script>
					</div>

					<c:if test="${result !=null }">
						<p
							style="text-align: center; background: antiquewhite; padding: 5px; display: block;">
							${message }</p>
					</c:if>

					<input type="submit" value="Cập Nhật">
				</form>

				<script>
                    function formValidate(e) {
                        let title = document.getElementById('title');
                        let description = document.getElementById('description');
                        let trailer = document.getElementById('trailer');
						let check = 0;
                        
                        if (title.value.length == 0) {
                            title.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Title không được bỏ trống!';
                        }
                        else {
                            title.nextElementSibling.getElementsByTagName('span')[0].innerHTML = '';
                            check++
                        }
                        if (description.value.length == 0) {
                            description.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Description không được bỏ trống!';
                        }
                        else {
                            description.nextElementSibling.getElementsByTagName('span')[0].innerHTML = '';
                            check++
                        }
                        if (trailer.value.length == 0) {
                            trailer.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'Trailer không được bỏ trống!';
                        }
                        else if(trailer.value.indexOf('https') == -1)
                        	trailer.nextElementSibling.getElementsByTagName('span')[0].innerHTML = 'URL sai!';
                        else {
                            trailer.nextElementSibling.getElementsByTagName('span')[0].innerHTML = '';
                            check++
                        }
                        if(check == 3){
                        	document.getElementById('imageUpload').disabled = 'true';

                            let input = document.createElement("input");
                            input.style.display = 'none';

                            let cat_active = document.querySelectorAll('.category.container-box > .container-item.item_checked');
                            cat_input = getInput(cat_active.length == 0 ? [] : cat_active);
                            document.getElementById('category').value = cat_input;
                            console.log(document.getElementById('category'))
                            
                            let staff_active = document.querySelectorAll('.staff.container-box-suggest > div > .container-item');
                            staff_input = getInput(staff_active.length == 0 ? [] : staff_active);
                            document.getElementById('addedStaff').value = staff_input;
                            
                            let studio_active = document.querySelectorAll('.studio.container-box-suggest > div > .container-item');
                            staff_input = getInput(staff_active.length == 0 ? [] : staff_active);
                            document.getElementById('addedStudio').value = staff_input;
                            
                            let char_active = document.querySelectorAll('.char.container-box-suggest > div > .container-item');
                            char_input = getInput(char_active.length == 0 ? [] : char_active);
                            document.getElementById('addedChar').value = char_input;
                            
                            let tag_active = document.querySelectorAll('.tag.container-box-suggest > div > .container-item');
                            tag_input = getInput(tag_active.length == 0 ? [] : tag_active);
                            document.getElementById('addedTag').value = tag_input;
                            
                            return true;
                        }
                        return false;
                    }
					
                    function handleInputChange(e){
                    	 e.nextElementSibling.getElementsByTagName('span')[0].innerHTML = null;
                    }
                    
                    function getInput(value = []) {
                        let vals = '';
                        for (let i = 0; i < value.length; ++i) {
                            vals += value[i].dataset.id;
                            if (i < value.length - 1) {
                                vals += ',';
                            }
                        }
                        return vals;
                    }
                </script>
			</section>


			<style>
.item_checked {
	background: bisque !important;
}

.section {
	border: 1px solid gray;
	background-color: white;
	margin: 10px 0;
}

.route-action {
	
}

.route-action .route-item {
	border: 1px solid gray;
	padding: 5px;
	border-radius: 5px;
	background-color: white;
}

.login form {
	width: unset;
}

.login form>div>label {
	display: block;
	font-size: 20px;
}

.login form input[type='text'], input[type='password'], input[type='email'],
	input[type='number'], textarea {
	width: 95%;
}

.login .hasError {
	margin-left: 0;
}

.container-box {
	position: relative;
	margin-top: 10px;
}

.container-box .container-item {
	display: inline-block;
	background: gainsboro;
	padding: 5px;
	border-radius: 4px;
	margin: 2px 0;
	cursor: pointer;
}
</style>

		</div>
	</div>

	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>

