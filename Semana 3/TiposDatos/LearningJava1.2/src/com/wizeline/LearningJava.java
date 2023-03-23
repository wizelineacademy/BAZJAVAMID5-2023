package com.wizeline;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpServer;
import com.wizeline.BO.BankAccountBO;
import com.wizeline.BO.BankAccountBOImpl;
import com.wizeline.BO.UserBO;
import com.wizeline.BO.UserBOImpl;
import com.wizeline.DTO.BankAccountDTO;
import com.wizeline.DTO.ResponseDTO;
import com.wizeline.DTO.UserDTO;

/**
 * @author Wizeline
 *
 */
public class LearningJava {
    
	private static final Logger LOGGER = Logger.getLogger(LearningJava.class.getName());
	private static String SUCCESS_CODE = "OK000";
	
	/**
	 * Descripcion: Metodo principal de del proyecto LearningJava
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		LOGGER.info("LearningJava - Iniciado servicio REST ...");
		/** This class implements a simple HTTP server  */
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/login", (exchange -> {
        	LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
        	ResponseDTO response = new ResponseDTO();
        	String responseText = "";
        	/** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
            	LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
            	UserDTO user =  new UserDTO();
            	user = user.getParameters(splitQuery(exchange.getRequestURI()));
            	response = login(user.getUser(), user.getPassword());
            	JSONObject json = new JSONObject(response);
                responseText = json.toString();
                exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
            } else {
            	/** 405 Method Not Allowed */
            	exchange.sendResponseHeaders(405, -1);
            }
            OutputStream output = exchange.getResponseBody();
            /** 
             * Always remember to close the resources you open. 
             * Avoid memory leaks 
             */
            LOGGER.info("LearningJava - Cerrando recursos ...");
            output.write(responseText.getBytes());
            output.flush();
            output.close();
            exchange.close();
        }));
        server.createContext("/api/createUser", (exchange -> {
        	LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
        	ResponseDTO response = new ResponseDTO();
        	String responseText = "";
        	/** Validates the type of http request  */
        	exchange.getRequestBody();
            if ("POST".equals(exchange.getRequestMethod())) {
            	LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
            	UserDTO user =  new UserDTO();
            	user = user.getParameters(splitQuery(exchange.getRequestURI()));
            	response = createUser(user.getUser(), user.getPassword());
            	JSONObject json = new JSONObject(response);
                responseText = json.toString();
                exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
            } else {
            	/** 405 Method Not Allowed */
            	exchange.sendResponseHeaders(405, -1);
            }
            OutputStream output = exchange.getResponseBody();
            /** 
             * Always remember to close the resources you open. 
             * Avoid memory leaks 
             */
            LOGGER.info("LearningJava - Cerrando recursos ...");
            output.write(responseText.getBytes());
            output.flush();
            output.close();
            exchange.close();
        }));
		// Consultar información de cuenta de un usuario
		server.createContext("/api/getUserAccount", (exchange -> {
			LOGGER.info("LearningJava - Inicia procesamiento de peticion ...");
			ResponseDTO response = new ResponseDTO();

			String responseText = "";
			/** Validates the type of http request  */
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				UserDTO user =  new UserDTO();
				user = user.getParameters(splitQuery(exchange.getRequestURI()));
				response = login(user.getUser(), user.getPassword());
				if (response.getCode().equals(SUCCESS_CODE)) {
					BankAccountDTO bankAccountDTO = getAccountDetails(user.getUser());
					JSONObject json = new JSONObject(bankAccountDTO);
					responseText = json.toString();
					exchange.getResponseHeaders().add("Content-type", "application/json");
					exchange.sendResponseHeaders(200, responseText.getBytes().length);
				}
			} else {
				/** 405 Method Not Allowed */
				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();
			/**
			 * Always remember to close the resources you open.
			 * Avoid memory leaks
			 */
			LOGGER.info("LearningJava - Cerrando recursos ...");
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));
        
        /** creates a default executor */
        server.setExecutor(null); 
        server.start();
        LOGGER.info("LearningJava - Server started on port 8080");
    }
	
	
	
	private static ResponseDTO login(String User, String password) {
		UserBO userBo = new UserBOImpl();
		return userBo.login(User, password);
	}
	
	private static ResponseDTO createUser(String User, String password) {
		UserBO userBo = new UserBOImpl();
		return userBo.createUser(User, password);
	}
	
	public static Map<String, String> splitQuery(URI uri) throws UnsupportedEncodingException {
	    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
	    String query = uri.getQuery();
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    return query_pairs;
	}

	private static BankAccountDTO getAccountDetails(String user) {
		BankAccountBO bankAccountBO = new BankAccountBOImpl();
		return bankAccountBO.getAccountDetails(user);
	}
}
