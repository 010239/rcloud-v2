$(document).ready(function() {
	$(".try-button").click(function() {
		var address = $(this).attr("address");
		window.open(address);
	});
});