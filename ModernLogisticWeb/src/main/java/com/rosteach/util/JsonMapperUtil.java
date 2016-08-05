package com.rosteach.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosteach.entities.SPROutcomeInvoice;

public class JsonMapperUtil {
	private static final ObjectMapper mapper = new ObjectMapper();
	public JsonMapperUtil(){
	}
	public static List<SPROutcomeInvoice> getInputInvoices(String request) throws JsonParseException, JsonMappingException, IOException{
		return mapper.readValue(request, new TypeReference<List<SPROutcomeInvoice>>(){});
	}
	public static String getDateFromOptions(String options) throws JsonProcessingException, IOException{
		JsonNode date = mapper.readTree(options).path("date");
		return date.asText();
	} 
}
