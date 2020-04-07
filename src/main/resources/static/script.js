$(function(){
	$("#parent-category").on("change", function(){
		$.ajax({
			url : "http://localhost:8080/select_parent_category",
			type : "POST",
			dataType : "json",
			data : {
				parentId : $("#parent-category").val()
			},
			async : true
		}).done(function(data){
			$('#child-category option').remove();
			$('#child-category').append(($('<option>')).html("- childCategory -").val(0));					
			console.log(data);
			console.dir(JSON.stringify(data));
			for(let childCategory of data.childCategoryList){
				$('#child-category').append(($('<option>')).html(childCategory.name).val(childCategory.id));					
			}
			
		}).fail(function(XMLHttpRequest, textStatus, errorThrown){
			alert("エラーが発生しました!");
			console.log("XMLHttpRequest:" + XMLHttpRequest.status);
			console.log("testStatus:" + textStatus);
			console.log("errorThrown:" + errorThrown.message);
		});
	});
	
	$("#child-category").on("change", function(){
		$.ajax({
			url : "http://localhost:8080/select_child_category",
			type : "POST",
			dataType : "json",
			data : {
				childId : $("#child-category").val()
			},
			async : true
		}).done(function(data){
			$('#grand-child-category option').remove();
			$('#grand-child-category').append(($('<option>')).html("- grandChildCategory -").val(0));	
			console.log(data);
			console.dir(JSON.stringify(data));	
			for(let grandChildCategory of data.grandChildCategoryList){
				$('#grand-child-category').append(($('<option>')).html(grandChildCategory.name).val(grandChildCategory.id));					
			}
			
		}).fail(function(XMLHttpRequest, textStatus, errorThrown){
			alert("エラーが発生しました!");
			console.log("XMLHttpRequest:" + XMLHttpRequest.status);
			console.log("testStatus:" + textStatus);
			console.log("errorThrown:" + errorThrown.message);
		});
	});
});