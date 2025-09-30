package integracion.crud.utils;

public class ComunidadExample implements CrudTestable {

	@Override
	public String getUrl() {
		return "/comunidades";
	}

	@Override
	public String getBody() {
		return "{\"nombre\":\"Puerto de Lumbreras 24\"}";
	}

	@Override
	public String getBodyWithId(String id) {
		return "{\"idComunidad\":\"" + id +  "\", \"nombre\":\"Puerto de Lumbreras 28\"}";
	}

	@Override
	public String getId() {
		return "idComunidad";
	}

	@Override
	public String getProperty() {
		return "nombre";
	}

	@Override
	public String getPropertyValueOriginal() {
		return "Puerto de Lumbreras 24";
	}

	@Override
	public String getPropertyValueModified() {
		return "Puerto de Lumbreras 28";
	}

}
