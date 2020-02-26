/**
 * 
 */

var dbMyClassStatus = [];

function logout(){
	let loginInfo = {p1:"logout"};
	
	$.ajax({
		url: "/zikao/service/login",
		data: loginInfo,
		type: "POST",
		dataType: "json",
		/* contentType: "application/json", */
		success: function(data){
			console.log("receive response.");
			console.log(data);
//			alert(JSON.stringify(data));
			if(data.header.status==200){
				//w.location.href="hi.html";
				location.reload();
			}else{
				let loginWarm = document.getElementById("loginWarm");
				loginWarm.style.display="block";
			}
		},
		error: function(e){
			console.log(e);
		}
	});
}

(function(w){
	console.log("hello");
	
	function queryClassDayRange(){
		let requestData = {};
		requestData.oper = "queryClassDayRange";
		
		$.ajax({
			url: "/zikao/service/class",
			data: JSON.stringify(requestData),
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log("receive response.");
				console.log(data);
//				alert(JSON.stringify(data));
				if(data.header.status==200){
					generateTimeDetails(data.data.StartDateStr, data.data.EndDateStr);
				}else{
					alert(data.header.message);
				}
				
			},
			error: function(e){
				console.log(e);
			}
		});
	}
	
	function queryUserInfo(){
		let requestData = {};
		requestData.oper = "queryCurrentUserInfo";
		
		$.ajax({
			url: "/zikao/service/manageuser",
			data: JSON.stringify(requestData),
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log("receive response.");
				console.log(data);
//				alert(JSON.stringify(data));
				if(data.header.status==200){
					let photos = document.querySelectorAll(".cls-user-photo");
					for(let i=0; i<photos.length; i++){
						let photo = photos[i];
						photo.setAttribute("src", PHOTO_ROOT_PATH+(data.data.photoPath==null ? "default.jpg" : data.data.photoPath));
					}
					
					document.getElementById("userName").innerHTML=data.data.name;
					document.getElementById("txtUserId").value=data.data.stuId;
					
					document.getElementById("loginLink").style.setProperty("display","none");
					document.getElementById("logoutLink").style.setProperty("display","inline");
					document.getElementById("userNameLink").style.setProperty("display","inline");
					document.getElementById("userPhotoLink").style.setProperty("display","inline");
					if(data.data.roleCode=="sysAdmin"){
						document.getElementById("systemparamLink").style.setProperty("display","inline");
					}else{
						document.getElementById("systemparamLink").style.setProperty("display","none");
					}
					
				}else{
//					alert(data.header.message);
					document.getElementById("loginLink").style.setProperty("display","inline");
					document.getElementById("logoutLink").style.setProperty("display","none");
					document.getElementById("userNameLink").style.setProperty("display","none");
					document.getElementById("userPhotoLink").style.setProperty("display","none");
					document.getElementById("systemparamLink").style.setProperty("display","none");
				}
				
			},
			error: function(e){
				console.log(e);
			}
		});
	}
	
	function queryCourseList(){
		let requestData = {};
		requestData.oper = "queryCourseList";
		
		$.ajax({
			url: "/zikao/service/class",
			data: JSON.stringify(requestData),
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log("receive response.");
				console.log(data);
//				alert(JSON.stringify(data));
				if(data.header.status==200){
					var courseListHtml = [];
					data.data.forEach(function(c){
						courseListHtml.push("<option value='"+c.courseCode+"'>"+c.courseName+"</option>");
					}, this);
					
					document.getElementById("courseSelector").innerHTML=courseListHtml.join("");
				}else{
					alert(data.header.message);
				}
				
			},
			error: function(e){
				console.log(e);
			}
		});
	}
	
	function queryClassStatus(){
		let requestData = {};
		requestData.oper = "queryClassStatus";
		requestData.startTimeStr = "2020021400000000";
		requestData.endTimeStr = "2020021700000000";
		
		$.ajax({
			url: "/zikao/service/class",
			data: JSON.stringify(requestData),
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log("receive response.");
				console.log(data);
//				alert(JSON.stringify(data));
				if(data.header.status==200){
					updateClassStatusInUI(data.data);
					addEvent();
				}else{
					alert(data.header.message);
				}
				
			},
			error: function(e){
				console.log(e);
			}
		});
	}
	
	function updateClassStatusInUI(classesStatus){
		let currDate = new Date();
		
		for(let i=0; i<classesStatus.length; i++){
			let classStatus = classesStatus[i];
			var classx = document.getElementById(classStatus.classId);
			
			if(!!classx){
				!!classStatus.studentId && classx.getAttribute(CLASS)==CLS_TIME_ENABLED 
					? dbMyClassStatus.push(classStatus):null;
				
				classx.setAttribute(CLASS, 
						(!!classStatus.studentId && classStatus.studentId==document.getElementById("txtUserId").value) ? 
							( new Date(classStatus.classId.substr(0,4)+"-"+classStatus.classId.substr(4,2)+"-"+classStatus.classId.substr(6,2)+" "+classStatus.classId.substr(8,2)+":"+classStatus.classId.substr(10,2)) <= currDate 
									? CLS_TIME_MYPASSEDSELECTED : CLS_TIME_MYSELECTED) 
							: 
							(classStatus.courseCode=="close" ? CLS_TIME_TEACHERCLOSED : CLS_TIME_OTHEROCCUPIED));
				classx.setAttribute("course", classStatus.courseCode);
				classx.innerHTML+="<br/>"+classStatus.courseName
								+"<br/>"+(classStatus.stuName==null?"":classStatus.stuName);
			}
		}
		
	}
	
	function selectClass(){
		console.log("Clicked2."+this+this.innerHTML + this.getAttribute("val1"));
		
		let selectCourse = document.getElementById("courseSelector");
		let courseCode = selectCourse.value;
		let courseName = selectCourse.selectedOptions[0].innerText;
		
		//let timex = e.target;
		let timex = this;
		if(CLS_TIME_ENABLED==timex.getAttribute(CLASS)){
			//Selecting course
			if(courseCode=="0"){
				console.log("请选择课程！");
				document.getElementById("courseAlert").style.display="block";
				return;
			}
			
			timex.setAttribute(CLASS, CLS_TIME_MYNEWSELECT);
			
			timex.setAttribute("course", courseCode);
			timex.innerHTML+="<br/>"+courseName;
		}else if(CLS_TIME_MYSELECTED==timex.getAttribute(CLASS)
				|| CLS_TIME_MYNEWSELECT==timex.getAttribute(CLASS)){
			//Diselecting course
			timex.setAttribute(CLASS, CLS_TIME_ENABLED);
			
			timex.removeAttribute("course");
			timex.innerHTML = getTimeFromclassId(timex.getAttribute("id"));
		}
		
		document.getElementById("courseAlert").style.display="none";
	}
	
	function submit(){
		var requestData = {};
		//requestData.userId = 1;//For debuging
		//requestData.pwd = "123";//For debuging
		requestData.oper = "bookClass";
//		requestData.paramsClassName = "com.dto.BookClassDto";	
		
		selectedCourses = [];
		
		var mySelected = document.querySelectorAll("."+CLS_TIME_MYNEWSELECT);
		mySelected.forEach(function(c){
				let classId = c.getAttribute("id");
				let courseCode = c.getAttribute("course");
				
				selectedCourses.push(
						{
							"classId": classId, 
							"courseCode": courseCode,
							"isCancel": "N"
						});
			}, this);
		
		//Merge with previous status
		//Step 1: set all to cancel
		dbMyClassStatus.forEach(function(cs){
			cs.isCancel="Y";
		}, this);
		
		//Step 2: remove the selected, no need to process again
		var mySelected = document.querySelectorAll("."+CLS_TIME_MYSELECTED);
		mySelected.forEach(function(c){
			let classId = c.getAttribute("id");
			let courseCode = c.getAttribute("course");
			
			for(let j=0; j<dbMyClassStatus.length; j++){
				let csj = dbMyClassStatus[j];
				if(classId==csj.classId && courseCode==csj.courseCode){
					dbMyClassStatus.splice(j,1);
				}
			}
		}, this);
		
		//Step 3: add delta (new + change)
		for(let i=0; i<selectedCourses.length; i++){
			let csi = selectedCourses[i];
			let isExistInDb = false;
			for(let j=0; j<dbMyClassStatus.length; j++){
				let csj = dbMyClassStatus[j];
				if(csi.classId==csj.classId){
					isExistInDb = true;
					if(csi.courseCode==csj.courseCode){
						csj.isCancel = "N";
					}else{
						dbMyClassStatus.push(csi);
					}
				}
			}
			
			//merge the non-exists item
			!isExistInDb ? dbMyClassStatus.push(csi) : null;
		}
		
		requestData.selectedCourses = dbMyClassStatus;
		
		console.log(requestData);
		console.log(JSON.stringify(requestData));
		
		$.ajax({
			url: "/zikao/service/class",
			data: JSON.stringify(requestData),
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			success: function(data){
				console.log("receive response.");
				console.log(data);
				alert(data.header.message);
				location.reload();
			},
			error: function(e){
				console.log(e);
			}
		});
	}
	
	function getTimeFromclassId(classId){
		let yyyy = classId.substr(0, 4);
		let mm = classId.substr(4, 2);
		let dd = classId.substr(6, 2);
		let hh_start = classId.substr(8, 2);
		let min_start = classId.substr(10, 2);
		let hh_end = classId.substr(12, 2);
		let min_end = classId.substr(14, 2);
		
		return hh_start+":"+min_start+"-"+hh_end+":"+min_end;
	}
	
	function addEvent(){
		let times = document.querySelectorAll("div#timeBox ul > li > div");
		times.forEach(function(t){
				
				if(CLS_TIME_ENABLED==t.getAttribute(CLASS) || CLS_TIME_MYSELECTED==t.getAttribute(CLASS)){
					t.addEventListener("click", selectClass);
				}
				
			}, this);
	}
	
	/*
	let sd = new Date(new Date().getTime()-2*24*60*60*1000);
	let ed = new Date(new Date().getTime()+4*24*60*60*1000);
	generateTimeDetails(sd.getFormatStr("YYYY-MM-DD"),ed.getFormatStr("YYYY-MM-DD"));
	*/
	queryClassDayRange();
	queryUserInfo();
	queryCourseList();
	queryClassStatus();
	
	/*let times = document.querySelectorAll("div#timeBox ul > li > div");
	times.forEach(function(t){
			
			if(CLS_TIME_ENABLED==t.getAttribute(CLASS) || CLS_TIME_MYSELECTED==t.getAttribute(CLASS)){
				t.addEventListener("click", selectClass);
			}
			
		}, this);*/
	//How to use promise to make the code better?
	
	document.getElementById("btnOk").addEventListener("click", submit);
	
})(window)

function generateTimeDetails(startDate, endDate){
	/*
		startDate endDate format: string yyyy-mm-dd e.g. 2020-01-03
		The duration days should be 7, otherwise UI display unnormal.
	*/
	let sd = new Date(startDate);
	let ed = new Date(endDate);
	
	//Testing parameters
	//let sd = new Date('2020-02-20');
	//let ed = new Date('2020-02-26');
	
	let days = (ed-sd)/(24*60*60*1000)+1;
	
	let timeSlot = [
		"10:00-10:25",
		"10:30-10:55",
		"11:00-10:25",
		"11:30-11:55",
		/*"12:00-12:25",
		"12:30-12:55",
		"13:00-13:25",
		"13:30-13:55",*/
		"14:00-14:25",
		"14:30-14:55",
		"15:00-15:25",
		"15:30-15:55",
		"16:00-16:25",
		"16:30-16:55",
		"17:00-17:25",
		/*"17:30-17:55",
		"18:00-18:25",
		"18:30-18:55",
		"19:00-19:25",*/
		"19:30-19:55",
		"20:00-20:25",
		"20:30-20:55",
		"21:30-21:55"
	];
	
	var timeDetailList = [];
	/*
	 * n行，第一行为timeDetailList[0]为标题行
	 * 从第二行开始每行为同一时间段不同天。
	 * 所以有多少个timeSlot就有多少行。
	 */
	for(let i=0; i<=timeSlot.length; i++){
		timeDetailList[i]=[];
	}
	
	var weeks = new Array("日", "一", "二", "三", "四", "五", "六");
	
	let datex = sd;
	let currDate = new Date();//For disabling the passed time.
	for(let d=0; d<days; d++){
		let datexStr = (datex.getMonth()+1)+"月"+(datex.getDate())+"日";
		timeDetailList[0][d]="<li>星期"+weeks[datex.getDay()]+"<br/><span>"+datexStr+"</span></li>";
		
		for(let i=0; i<timeSlot.length; i++){
			timeDetailList[i+1][d]="<li><div id='"+datex.getFormatStr()+timeSlot[i].replaceAll(":","").replaceAll("-","")
				+"' class='"+ (new Date(datex.getFormatStr("YYYY-MM-DD ")+timeSlot[i].substr(0,5)) >= currDate ? "cls-time-enabled" : "cls-time-teacherclosed")+"'>"
				+timeSlot[i]+"</div></li>";
			//Passed time cannot be booked.
		}
		
		datex=new Date(datex.getTime()+1*24*60*60*1000);
	}
	
	let timeDetailListHtml = "";
	for(let i=0; i<timeDetailList.length; i++){
		timeDetailListHtml+="<ul>"+timeDetailList[i].join("")+"</ul>";
	}
	
	document.getElementById("timeDetail").innerHTML=timeDetailListHtml;
}
