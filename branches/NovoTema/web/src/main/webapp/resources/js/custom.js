// mask
$(document).ready(function() {
	mascaras();
	marcaDagua();
	$('.textarea').wysihtml5();
} );

function editorTexto() {
	$('.textarea').wysihtml5();
}

function mascaras() {
	$(".data").mask("99/99/9999");
	$(".telefone").mask("(99) 9999-9999?9");
	$(".cpf").mask("999.999.999-99");
	$(".cnpj").mask("99.999.999/9999-99");
}
function marcaDagua() {
	$(".exemplo").attr("placeholder", "Insira o título aqui").blur();
	$("#pesquisar").attr("placeholder", "Pesquisar...").blur();
	
	$("#matricula").attr("placeholder", "matrícula").blur();
	$("#senha").attr("placeholder", "senha").blur();
}

function showModalDelete(idModal){$("#"+idModal).modal("show");};


function descer() {
	window.scrollTo(100, 10000);
}