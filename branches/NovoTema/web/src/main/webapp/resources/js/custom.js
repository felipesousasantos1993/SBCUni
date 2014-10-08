// mask
$(document).ready(function() {
	mascaras();
	autocomplete();
	editorTexto();
	dataTable();
	tooltip();
	switcher();
});

$(document).ready(function() {
	$('.scroll').slimScroll({
		height : '',
		alwaysVisible : true,
		color : '#888',
		allowPageScroll : true
	});
});
$(document).ready(function() {
	$('.scrollNotificacao').slimScroll({
		height : 250,
	});
});



$(document).ready(function() {
	$('.scroll').slimScroll({ height: 250 });
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

function dataTable() {
	$('.dataTable').dataTable();
	$('.dataTable .table-caption').text('Some header text');
	$('.dataTable .dataTables_filter input').attr('placeholder', 'Procurar...');
}
function editorTexto() {
	$('.editorTexto').summernote({
		height : 200,
		tabsize : 2,
		codemirror : {
			theme : 'monokai'
		}
	});
}
function autocomplete() {
	$(".autocompleteCategoria").select2({
		placeholder: "Selecione a categoria",
	});
	$(".autocompleteUsuario").select2({
		placeholder: "Digite os destinatários...",
	});
	$(".autocompleteAlunos").select2({
		placeholder: "Insira os alunos...",
	});
}
function tooltip() {
	$('.tooltips button').tooltip();
	$('.tooltips label').tooltip();
	$('.tooltips img').tooltip();
	$('.tooltips a').tooltip();
	$('.popovers button').popover();
}

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
	
	$('#novaMsg').css('display', 'none');
	$('#menuMsg').css('display', '');
	$('#tituloCaixa').css('display', '');
}
function tabPrincipal() {
	$('#tabPrincipal').addClass('active');
	$('#tabEnviados').removeClass('active');
	$('#tabLixeira').removeClass('active');
	
	$('#novaMsg').css('display', 'none');
	$('#menuMsg').css('display', '');
	$('#tituloCaixa').css('display', '');
}
function tabLixeira() {
	$('#tabEnviados').removeClass('active');
	$('#tabPrincipal').removeClass('active');
	$('#tabLixeira').addClass('active');
	
	$('#novaMsg').css('display', 'none');
	$('#menuMsg').css('display', '');
	$('#tituloCaixa').css('display', '');
}
function novaMsg() {
	$('#tabEnviados').removeClass('active');
	$('#tabPrincipal').removeClass('active');
	$('#tabLixeira').removeClass('active');
	
	$('#msgRecebidas').css('display', 'none');
	$('#msgEnviadas').css('display', 'none');
	$('#msgExcluidas').css('display', 'none');
	$('#menuMsg').css('display', 'none');
	$('#tituloCaixa').css('display', 'none');
}
function verMsg() {
	$('#tabEnviados').removeClass('active');
	$('#tabPrincipal').removeClass('active');
	$('#tabLixeira').removeClass('active');
	
	$('#msgRecebidas').css('display', 'none');
	$('#msgEnviadas').css('display', 'none');
	$('#msgExcluidas').css('display', 'none');
	$('#novaMsg').css('display', 'none');
	$('#menuMsg').css('display', 'none');
	$('#tituloCaixa').css('display', 'none');
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
function switcher() {
	$('#timeline-centered').switcher();
	$('#timeline-centered').on($('html').hasClass('ie8') ? "propertychange" : "change", function () {
		var turn_on = $(this).is(':checked');
		if (turn_on) {
			$('.timeline').addClass('centered');
		} else {
			$('.timeline').removeClass('centered');
		}
	});
}
function exibirMensagemResponta() {
	$('#msgResposta').css('display', '');
}


function selecionarAvatar(avatar) {
	for (i = 0; i < 27; i++) {
		document.getElementById('avatares:'+i+':avatar').className = '';
		document.getElementById('avatares:'+i+':avatar').className = 'fotoNaoSelecionada';
		document.getElementById('avatares:'+i+':avatar').setAttribute('title', 'Selecionar');
	}
	document.getElementById(avatar.id).className = '';
	document.getElementById(avatar.id).className = 'fotoSelecionada';
	document.getElementById(avatar.id).setAttribute('title', 'Selecionada');
}

$(document).ready(function() {	
	$('.inplace').editable({
		type: 'text',
		name: 'username',
		title: 'Digite o nome do grupo',
		validate: function(value) {
			if($.trim(value) == '') return 'Campo obrigatório';
		}
	});
});

function abrirEditarGrupo() {
	$('#panelEditarNomeGrupo').css('display','block');
}
function fecharEditarGrupo() {
	$('#panelEditarNomeGrupo').css('display','none');
}

$(document).ready(function() {
	$("#fotoPerfil").dropzone({
		url: "//dummy.html",
		maxFiles: 1,
		paramName: "file", // The name that will be used to transfer the file
		maxFilesize: 0.5, // MB

		addRemoveLinks : true,
		dictResponseError: "Não pode esta foto!",
		autoProcessQueue: false,
		thumbnailWidth: 138,
		thumbnailHeight: 120,

		previewTemplate: '<div class="dz-preview dz-file-preview"><div class="dz-details"><div class="dz-filename"><span data-dz-name></span></div><div class="dz-size">File size: <span data-dz-size></span></div><div class="dz-thumbnail-wrapper"><div class="dz-thumbnail"><img data-dz-thumbnail><span class="dz-nopreview">No preview</span><div class="dz-success-mark"><i class="fa fa-check-circle-o"></i></div><div class="dz-error-mark"><i class="fa fa-times-circle-o"></i></div><div class="dz-error-message"><span data-dz-errormessage></span></div></div></div></div><div class="progress progress-striped active"><div class="progress-bar progress-bar-success" data-dz-uploadprogress></div></div></div>',

		resize: function(file) {
			var info = { srcX: 0, srcY: 0, srcWidth: file.width, srcHeight: file.height },
				srcRatio = file.width / file.height;
			if (file.height > this.options.thumbnailHeight || file.width > this.options.thumbnailWidth) {
				info.trgHeight = this.options.thumbnailHeight;
				info.trgWidth = info.trgHeight * srcRatio;
				if (info.trgWidth > this.options.thumbnailWidth) {
					info.trgWidth = this.options.thumbnailWidth;
					info.trgHeight = info.trgWidth / srcRatio;
				}
			} else {
				info.trgHeight = file.height;
				info.trgWidth = file.width;
			}
			return info;
		}
	});
});