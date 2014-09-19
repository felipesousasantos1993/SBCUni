// mask
$(document).ready(function() {
	mascaras();
} );

function mascaras() {
	$(".data").mask("99/99/9999");
	$(".telefone").mask("(99) 9999-9999?9");
	$(".cpf").mask("999.999.999-99");
	$(".cnpj").mask("99.999.999/9999-99");
}

function showModalDelete(idModal){$("#"+idModal).modal("show");};


function descer() {
	window.scrollTo(100, 10000);
}

$(document).ready(function() {
	$('.dataTable').dataTable();
	$('.dataTable .table-caption').text('Some header text');
	$('.dataTable .dataTables_filter input').attr('placeholder', 'Search...');
});
