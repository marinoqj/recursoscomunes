function volverMenuPerceptor(){		
	document.formulario.action="verMenuPerceptor";
	document.formulario.submit();
}


function normalizarDecimales(objInput) {
    objInput.value = objInput.value.replace(',', '.');
}

$(document).ready(function(){
	$('#dataTable').DataTable(
			{
				ordering: false,
				lengthChange: false,
				info: false,
				pageLength: 15,
				pagingType: 'full_numbers',
			    language: {
			        search:         "Buscar:",
			        paginate: {
			            first:      "Primero",
			            previous:   "&laquo;",
			            next:       "&raquo;",
			            last:       "Último"
			        },
			    }
			}	
	);
});
