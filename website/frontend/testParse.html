<!DOCTYPE html>
<html>
<head>
	<title>test</title>
</head>
<body>
<table border="1" id="table-row">
	<thead>
		<tr></tr>
	</thead>
	<tbody>
		<tr></tr>
	</tbody>
</table>
</body>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$.ajax({
		type: "GET",
		url: "../backend/basic.row.php",
		dataType: "json",
		success: function(data) {

			var txt = "";
			
			for (var i in data) {
				var objectColumn = data[i]['column'];
				var objectRow = data[i]['row'];
					for (var keyRow in objectRow) {
						txt += "<tr>";
						for (var keyColumn in objectColumn){
							var column = objectColumn[keyColumn]['COLUMN_NAME'];
							txt += "<td>" + objectRow[keyRow][column]+ "</td>";
						}
						txt += "</tr>";
					}
				
				
			}

			// Replace table’s tbody html with txt
			$("#table-row tbody").html(txt);
		}
	});
	$.ajax({
		type: "GET",
		url: "../backend/basic.column.php",
		dataType: "json",
		success: function(JSONObject) {
			var txt = "";

			// Loop through Object and create txt
			for (var key in JSONObject) {
				if (JSONObject.hasOwnProperty(key)) {
					
					txt += "<th>" + JSONObject[key]["COLUMN_NAME"] + "</th>";
					
				}
			}

			// Replace table’s tbody html with txt
			$("#table-row thead").html(txt);
		}
	});
</script>
</html>