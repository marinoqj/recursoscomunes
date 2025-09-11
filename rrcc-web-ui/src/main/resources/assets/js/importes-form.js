	const arrayTabs = ["educacion-infantil", "educacion-primaria", "eso-primer-grado", "eso-segundo-grado", "programa-cualif"];

	function marcarSelected(idTabSelected) {
		let formActualizarImportes= document.forms['actualizarImportes'];
		let hiddenFormActualizarImportes = formActualizarImportes['selectedTab'];
		hiddenFormActualizarImportes.value = idTabSelected;

		let formActualizarImportesMasivo = document.forms['actualizarImportesMasivo'];
		let hiddenFormActualizarImportesMasivo = formActualizarImportesMasivo['selectedTab'];
		hiddenFormActualizarImportesMasivo.value = idTabSelected;
	}

	function realizarSubmitMasivo() {
		let formActualizarImportesMasivo = document.forms['actualizarImportesMasivo'];
		let hiddenFormActualizarImportesMasivo = formActualizarImportesMasivo['selectedTab'];
		hiddenFormActualizarImportesMasivo.value = 'todos-tab';
		document.actualizarImportesMasivo.submit();
	}

	function isPorcentajeValid(porcentaje) {
		let resultado = false;

		if(porcentaje.value) {
			let valor = parseFloat(porcentaje.value);
			resultado = !isNaN(valor) && valor >= 0 && valor <= 100;
		}

		return resultado;
	}

	function isfechaValida(fechaDesde) {
		let resultado = false;

		if (fechaDesde.value) {
			resultado =  /^\d{4}-\d{2}-\d{2}$/.test(fechaDesde.value);
		}

		return resultado;
	}

	function realizarSubmit(codNivel, codConcepto, importe) {
		let porcentaje = document.getElementById('porcentaje_' + codNivel + '_' + codConcepto);
		let fechaDesde = document.getElementById('fechaDesde_' + codNivel + '_' + codConcepto);

		let validacionCorrecta = validarDatos(porcentaje, fechaDesde);

		if (validacionCorrecta) {
			document.getElementById('codNivel').value = codNivel;
			document.getElementById('codConcepto').value = codConcepto;
			document.getElementById('importe').value = importe.replace(',', '.');

			document.getElementById('porcentaje').value = porcentaje.value;
			document.getElementById('fechaDesde').value = fechaDesde.value;
			document.actualizarImportes.submit();
		} else {
			if (!isPorcentajeValid(porcentaje)) {
				porcentaje.style.border = '1px solid red';
			} else {
				porcentaje.style.border = '';
			}

			if (!isfechaValida(fechaDesde)) {
				fechaDesde.style.border = '1px solid red';
			} else {
				fechaDesde.style.border = '';
			}

			$('#alertModal').modal('show');
		}
	}

	function validarDatos(porcentaje, fechaDesde) {
		return (isPorcentajeValid(porcentaje) && isfechaValida(fechaDesde));
	}

	/**
	 * Todos los objetos de formulario que aparezcan dentro de un elemento (ejem: una pestaña de boostrap con un id determinado)
	 * quedarán inhabilitados (disabled).
	 * @param idElementoPadre
	 */
	function setDisabled(idElementoPadre) {
		const div = document.getElementById(idElementoPadre);
		const elements = div.querySelectorAll('input, textarea, select, a');

		elements.forEach(element => {
			if (element.tagName === 'A') {
				element.style.pointerEvents = 'none';
				element.style.color = 'gray'; // Opcional: cambiar el color para indicar que está inhabilitado
			} else if (element.type !== 'radio') {
				element.setAttribute('disabled', true);
				element.style.border = '';
				element.value = '';
			}
		});
	}

	/**
	 * A partir del id de un elemento (por ejemplo una pestaña de bootstrap o un div) se buscan los objetos de formulario
	 * y se van deshabilitando.
	 * @param idElementodPadre
	 */
	function removeDisabled(idElementodPadre) {
		const div = document.getElementById(idElementodPadre);
		const elements = div.querySelectorAll('input, textarea, select, a');

		elements.forEach(element => {
			if (element.tagName === 'A') {
				element.style.pointerEvents = 'auto';
				element.style.color = ''; // Restaurar el color original
			} else if (element.type !== 'radio') {
				element.removeAttribute('disabled');
			}
		});
	}

	function disableAllEnableThisOne (idToBeEnabled) {
		for(var i=0; i<arrayTabs.length; i++) {
			setDisabled(arrayTabs[i]);
		}

		removeDisabled(idToBeEnabled);
	}