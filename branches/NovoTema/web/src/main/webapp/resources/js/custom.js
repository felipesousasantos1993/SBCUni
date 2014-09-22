// mask
$(document).ready(function() {
	mascaras();
});

function mascaras() {
	$(".data").mask("99/99/9999");
	$(".telefone").mask("(99) 9999-9999?9");
	$(".cpf").mask("999.999.999-99");
	$(".cnpj").mask("99.999.999/9999-99");
}

function showModalDelete(idModal) {
	$("#" + idModal).modal("show");
};

function descer() {
	window.scrollTo(100, 10000);
}

$(document).ready(function() {
	$('.dataTable').dataTable();
	$('.dataTable .table-caption').text('Some header text');
	$('.dataTable .dataTables_filter input').attr('placeholder', 'Search...');
});

$(document).ready(function() {
	$('.editorTexto').summernote({
		height : 200,
		tabsize : 2,
		codemirror : {
			theme : 'monokai'
		}
	});
});
$(document).ready(function() {
	$(".autocomplete").select2({
		placeholder: "Selecione a categoria",
	});
});

$(document).ready(function() {
	$('.tooltip button').tooltip();
});

function expandir() {
	$('.comentar').attr('rows', '3').autosize();
	$('#btnComentar').css('display', '');
}
function expandirPorId(id) {
	$('#'.concat(id)).attr('rows', '4').autosize();
}
function reduzirPorId(id) {
	if(document.getElementById(id).value == "") {
		$('#'.concat(id)).attr('rows', '1').autosize();
		$('#'.concat(id)).css('height', 'auto').autosize();
	}
}
/*
$('.comentar').blur(function() {
	$('.comentar').attr('rows', '1').autosize();
	$('.comentar').css('height', 'auto').autosize();
	$('#btnComentar').css('display', 'none');
});*/
	


