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
	$(".autocompleteCategoria").select2({
		placeholder: "Selecione a categoria",
	});
	$(".autocompleteUsuario").select2({
		placeholder: "Digite os destinatários...",
	});
	$(".autocompleteAlunos").select2({
		placeholder: "Insira os alunos...",
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
function tabEnviados() {
	$('#tabEnviados').addClass('active');
	$('#tabPrincipal').removeClass('active');
	$('#tabLixeira').removeClass('active');
}
function tabPrincipal() {
	$('#tabEnviados').removeClass('active');
	$('#tabPrincipal').addClass('active');
	$('#tabLixeira').removeClass('active');
}
function tabLixeira() {
	$('#tabEnviados').removeClass('active');
	$('#tabPrincipal').removeClass('active');
	$('#tabLixeira').addClass('active');
}

function verificaTabEmail() {
	if ($('#tabPrincipal').hasClass('active')) {
		return 1;
	} else if ($('#tabEnviados').hasClass('active')) {
		return 2;
	} else if ($('#tabLixeira').hasClass('active')) {
		return 3;
	}
}
/*
$('.comentar').blur(function() {
	$('.comentar').attr('rows', '1').autosize();
	$('.comentar').css('height', 'auto').autosize();
	$('#btnComentar').css('display', 'none');
});*/



$(document).ready(function() {
	$('#timeline-centered').switcher();
	$('#timeline-centered').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
		var turn_on = $(this).is(':checked');
		if (turn_on) {
			$('.timeline').addClass('centered');
		} else {
			$('.timeline').removeClass('centered');
		}
	});
});


$(document).ready(function() {
	$('.tooltips label').tooltip();
});
$(document).ready(function() {
	$('.popovers button').popover();
});
