$(document).ready(function() {
	$("#listaMenu li a").click(function() {
		var index = $("#listaMenu li a").index(this);
		$("#listaMenu li").eq(index).children("ul").slideDown();
		$("#listaMenu li").eq(index).addClass("ativo");
		if ($(this).siblings('ul').size() > 0) {
			return false;
		}
	});
	$("#listaMenu li").mouseleave(function() {
		var index = $("#listaMenu li").index(this);
		$("#listaMenu li").eq(index).children("ul").slideUp();
		$("#listaMenu li").eq(index).removeClass("ativo");
	});
});
// Marca d'agua
$(document).ready(function() {
	$(".exemplo").attr("placeholder", "Insira o título aqui").blur();
	$("#exemplo").attr("placeholder", "Insira a descrição aqui").blur();
	
});

function dataTableSelectOneRadio(radio) {
	var radioId = radio.name.substring(radio.name.lastIndexOf(':'));

	for ( var i = 0; i < radio.form.elements.length; i++) {
		var element = radio.form.elements[i];

		if (element.name.substring(element.name.lastIndexOf(':')) == radioId) {
			element.checked = false;
		}
	}

	radio.checked = true;
}

$('#modalExcluir').modal({
	keyboard : false
})
