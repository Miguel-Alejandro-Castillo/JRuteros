package converters;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import model.Punto;


@FacesConverter("converters.PuntosConverter")
public class PuntosConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		ObjectMapper objectMapper = new ObjectMapper();
		Set<Punto> puntos;
		try {
			puntos = objectMapper.readValue(arg2, new TypeReference<Set<Punto>>(){});
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException(e);
		}
		return puntos;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		String jsonPuntos;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonPuntos = objectMapper.writeValueAsString(arg2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new ConverterException(e);
		}
		return jsonPuntos;
	}

}
