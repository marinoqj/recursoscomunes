package integracion.crud.utils;

public interface CrudTestable {
	
	String getUrl();

	String getBody();
	
	String getBodyWithId(String id);

	String getId();
	
	String getProperty();
	
	String getPropertyValueOriginal();
	
	String getPropertyValueModified();


}
