$(function() {
	$("#get_address_btn").on("click", function() {
		$.ajax({
			url: "http://zipcoda.net/api",
			dataType: "jsonp",
			data: {
				zipcode: $("#zipCode").val()
			},
			async: true
		}).done(function(data) {
			$("#address").val(data.items[0].pref + data.items[0].address);
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			alert("結果を得られませんでした");
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
		});
	});
});