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
		success: function(JSONObject) {
			var txt = "";

			// Loop through Object and create txt
			for (var key in JSONObject) {
				if (JSONObject.hasOwnProperty(key)) {
					txt += "<tr>";
					txt += "<td>" + JSONObject[key]["id_basic"] + "</td>";
					txt += "<td>" + JSONObject[key]["fn_name"] + "</td>";
					txt += "<td>" + JSONObject[key]["mn_name"] + "</td>";
					txt += "<td>" + JSONObject[key]["ln_name"] + "</td>";
					txt += "<td>" + JSONObject[key]["nn_name"] + "</td>";
					txt += "<td>" + JSONObject[key]["pof_name"] + "</td>";
					txt += "<td>" + JSONObject[key]["email"] + "</td>";
					txt += "<td>" + JSONObject[key]["contact_number"] + "</td>";
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