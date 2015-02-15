package co.kr.qbic.common.view.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CoNulltoEmptyStringSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException,	JsonProcessingException {
		arg1.writeString("");
	}
}
