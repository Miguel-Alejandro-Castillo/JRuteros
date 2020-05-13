function jq(id) {
    return "#" + id.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1");
 
}

function refreshTable(idTable){
	var length = $( idTable + " tbody tr").first().children().length;
	if (length === 1) {
		$(idTable + " tbody").html("");
	}
}
function loadDataTables(idTable, onlyRefresh){
	idTable = jq(idTable);
	refreshTable(idTable);
	if(!onlyRefresh){
		$(idTable).DataTable({
	        "language": {
	            "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
	        }
	    } );
	}

}
