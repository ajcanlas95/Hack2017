
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
					txt += "<td><a class='btn-flat' href='edit.basic.php?basic_id="+objectRow[keyRow]['id_basic'] +"'><i class='material-icons center-align'>mode_edit</i></a><a class='btn-flat' href='remove.basic.php?basic_id="+objectRow[keyRow]['id_basic'] +"'><i class='material-icons center-align'>delete_forever</i></a></td>";
					txt += "</tr>";
				}
		}
		// Replace table’s tbody html with txt
		$("#table-row").html(txt);
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
		txt += "<th><a class='waves-effect waves-light yellow darken-1 btn' href='#' >Add Column</a></th>";
		// Replace table’s tbody html with txt
		$("#table-column tr").html(txt);
	}
});
