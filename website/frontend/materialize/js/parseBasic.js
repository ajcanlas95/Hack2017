$.ajax({
	type: "GET",
	url: "../../website/backend/basic.row.php",
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
				txt += "<td><a class='btn-flat' href='edit.basic.php?basic_id="+JSONObject[key]["id_basic"] +"'><i class='material-icons center-align'>mode_edit</i></a><a class='btn-flat' href='remove.basic.php?basic_id="+JSONObject[key]["id_basic"] +"'><i class='material-icons center-align'>delete_forever</i></a></td>";
				txt += "</tr>";
			}
		}

		// Replace table’s tbody html with txt
		$("#table-row ").html(txt);
	}
});
$.ajax({
	type: "GET",
	url: "../../website/backend/basic.column.php",
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