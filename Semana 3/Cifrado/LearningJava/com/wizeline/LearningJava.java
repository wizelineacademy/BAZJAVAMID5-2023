package com.wizeline;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.logging.Logger;

import com.wizeline.utils.Utils;
import com.wizeline.utils.exceptions.ExcepcionGenerica;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpServer;
import com.wizeline.BO.BankAccountBO;
import com.wizeline.BO.BankAccountBOImpl;
import com.wizeline.BO.UserBO;
import com.wizeline.BO.UserBOImpl;
import com.wizeline.DTO.BankAccountDTO;
import com.wizeline.DTO.ResponseDTO;
import com.wizeline.DTO.UserDTO;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.wizeline.utils.Utils.isDateFormatValid;
import static com.wizeline.utils.Utils.isPasswordValid;

/**
 * @author Wizeline
 */
public class LearningJava extends Thread{

	private static final Logger LOGGER = Logger.getLogger(LearningJava.class.getName());
	private static String SUCCESS_CODE = "OK000";
    private static String responseTextThread = "";
    private ResponseDTO response;
    private static String textThread = "";

    /**
	 * Descripcion: Metodo principal de del proyecto LearningJava
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		LOGGER.info("LearningJava - Iniciado servicio REST ...");
		/** This class implements a simple HTTP server  */
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        String msgProcPeticion = "LearningJava - Inicia procesamiento de peticion ...";
        server.createContext("/api/login", (exchange -> {
            LOGGER.info(msgProcPeticion);
            ResponseDTO response = new ResponseDTO();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                UserDTO user = new UserDTO();
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
            LOGGER.info(msgProcPeticion);
            ResponseDTO response = new ResponseDTO();
            String responseText = "";
            /** Validates the type of http request  */
            exchange.getRequestBody();
            if ("POST".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                UserDTO user = new UserDTO();
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

        // Crear usuarios
        server.createContext("/api/createUsers", (exchange -> {
            LOGGER.info(msgProcPeticion);
            ResponseDTO response = new ResponseDTO();
            /** Validates the type of http request  */
            exchange.getRequestBody();
            if ("POST".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo POST");

                StringBuilder text = new StringBuilder();
                try (Scanner scanner = new Scanner(exchange.getRequestBody())) {
                    while(scanner.hasNext()) {
                        text.append(scanner.next());
                    }
                } catch (Exception e) {
                    LOGGER.severe(e.getMessage());
                    throw new ExcepcionGenerica("Fallo al obtener el request del body");
                }
                textThread = text.toString();

                LOGGER.info(textThread);
                LearningJava thread = new LearningJava();
                thread.start();

                while(thread.isAlive());

                exchange.getResponseHeaders().set("contentType", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, responseTextThread.getBytes().length);
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
            output.write(responseTextThread.getBytes());
            output.flush();
            output.close();
            exchange.close();
        }));

        // Consultar información de cuenta de un usuario
        server.createContext("/api/getUserAccount", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            ResponseDTO response = new ResponseDTO();

			String responseText = "";
			/** Validates the type of http request  */
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				UserDTO user =  new UserDTO();
				Map<String, String> params = splitQuery(exchange.getRequestURI());
				user = user.getParameters(params);
				// Valida formato del parametro fecha (date) [dd-mm-yyyy]
				String lastUsage = params.get("date");
				if (isDateFormatValid(lastUsage)) {
					// Valida el password del usuario (password)
					if (isPasswordValid(user.getPassword())) {
						response = login(user.getUser(), user.getPassword());
						if (response.getCode().equals(SUCCESS_CODE)) {
							BankAccountDTO bankAccountDTO = getAccountDetails(user.getUser(), lastUsage);
							JSONObject json = new JSONObject(bankAccountDTO);
							responseText = json.toString();
							exchange.getResponseHeaders().add("Content-type", "application/json");
							exchange.sendResponseHeaders(200, responseText.getBytes().length);
						}
					} else {
						responseText = "Password Incorrecto";
						exchange.getResponseHeaders().add("Content-type", "application/json");
						exchange.sendResponseHeaders(401, responseText.getBytes().length);
					}
				} else {
					responseText = "Formato de Fecha Incorrecto";
					exchange.getResponseHeaders().add("Content-type", "application/json");
					exchange.sendResponseHeaders(400, responseText.getBytes().length);
				}
			} else {
				/** 405 Method Not Allowed */
				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();
			Instant finalDeEjecucion = Instant.now();
			/**
			 * Always remember to close the resources you open.
			 * Avoid memory leaks
			 */
			LOGGER.info("LearningJava - Cerrando recursos ...");
			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
			LOGGER.info("Tiempo de respuesta: ".concat(total));
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));

        // Consultar información de todas las cuentas
		server.createContext("/api/getAccounts", (exchange -> {
			LOGGER.info(msgProcPeticion);
			Instant inicioDeEjecucion = Instant.now();
			BankAccountBO bankAccountBO = new BankAccountBOImpl();
			String responseText = "";
			/** Validates the type of http request  */
			if ("GET".equals(exchange.getRequestMethod())) {
				LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
				List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
				JSONArray json = new JSONArray(accounts);
				responseText = json.toString();
				exchange.getResponseHeaders().add("Content-type", "application/json");
				exchange.sendResponseHeaders(200, responseText.getBytes().length);
			} else {
				/** 405 Method Not Allowed */
				exchange.sendResponseHeaders(405, -1);
			}
			OutputStream output = exchange.getResponseBody();
			Instant finalDeEjecucion = Instant.now();
			/**
			 * Always remember to close the resources you open.
			 * Avoid memory leaks
			 */
			LOGGER.info("LearningJava - Cerrando recursos ...");
			String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
			LOGGER.info("Tiempo de respuesta: ".concat(total));
			output.write(responseText.getBytes());
			output.flush();
			output.close();
			exchange.close();
		}));
        // Consultar todas las cuentas y buscarla por nombre utilizando Optional por si no es encontrada
        server.createContext("/api/getAccountByName", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            BankAccountBO bankAccountBO = new BankAccountBOImpl();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
                // Aquí implementaremos nuestro código de filtrar las cuentas por nombre utilizando optional
                Map<String, String> params = splitQuery(exchange.getRequestURI());
                Optional<String> Optionalnombre = getParameterValue(params, "name");
                String nombre = Optionalnombre.get();
                List<BankAccountDTO> accountsFiltered = bankAccountBO.getAccounts();
                accountsFiltered.clear();
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getAccountName().indexOf(nombre) >= 0) {
                        accountsFiltered.add(accounts.get(i));
                        break;
                    }
                }
                JSONArray json = new JSONArray(accountsFiltered);
                responseText = json.toString();
                exchange.getResponseHeaders().add("Content-type", "application/json");
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
            } else {
                /** 405 Method Not Allowed */
                exchange.sendResponseHeaders(405, -1);
            }
            OutputStream output = exchange.getResponseBody();
            Instant finalDeEjecucion = Instant.now();
            /**
             * Always remember to close the resources you open.
             * Avoid memory leaks
             */
            LOGGER.info("LearningJava - Cerrando recursos ...");
            String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
            LOGGER.info("Tiempo de respuesta: ".concat(total));
            output.write(responseText.getBytes());
            output.flush();
            output.close();
            exchange.close();
        }));

        // Consultar todas las cuentas y filtrarlas por usuario
        server.createContext("/api/getAccountsByUser", (exchange -> {
            LOGGER.info(msgProcPeticion);
            Instant inicioDeEjecucion = Instant.now();
            BankAccountBO bankAccountBO = new BankAccountBOImpl();
            String responseText = "";
            /** Validates the type of http request  */
            if ("GET".equals(exchange.getRequestMethod())) {
                LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                List<BankAccountDTO> accounts = bankAccountBO.getAccounts();
                List<BankAccountDTO> accountsFiltered = bankAccountBO.getAccounts();
                accountsFiltered.clear();

                // Aquí implementaremos nuestro código de filtrar las cuentas por usuario
                Map<String, String> params = splitQuery(exchange.getRequestURI());
                Optional<Object> Optionaluser = getParameterValueObject(params, "user");
                Object user = Optionaluser.get();
               for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getUser().indexOf(user.toString()) >= 0) {
                        accountsFiltered.add(accounts.get(i));
                    }
                }
                JSONArray json = new JSONArray(accountsFiltered);
                responseText = json.toString();
                exchange.getResponseHeaders().add("Content-type", "application/json");
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
            } else {
                /** 405 Method Not Allowed */
                exchange.sendResponseHeaders(405, -1);
            }
            OutputStream output = exchange.getResponseBody();
            Instant finalDeEjecucion = Instant.now();
            /**
             * Always remember to close the resources you open.
             * Avoid memory leaks
             */
            LOGGER.info("LearningJava - Cerrando recursos ...");
            String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
            LOGGER.info("Tiempo de respuesta: ".concat(total));
            output.write(responseText.getBytes());
            output.flush();
            output.close();
            exchange.close();
        }));

        // Consultar todas las cuentas y regresarselas al usuario de manera cifrada
        server.createContext("/api/getEncryptedAccounts", (exchange -> {
                LOGGER.info(msgProcPeticion);
                Instant inicioDeEjecucion = Instant.now();
                BankAccountBO bankAccountBO = new BankAccountBOImpl();
                String responseText = "";
                /** Validates the type of http request  */
                if ("GET".equals(exchange.getRequestMethod())) {
                    LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
                    List<BankAccountDTO> accounts = bankAccountBO.getAccounts();

                    // Aquí implementaremos nuestro código de cifrar nuestras cuentas y regresarselas al usuario de manera cifrada
                    byte[] keyBytes = new byte[]{
                            0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef
                    };
                    byte[] ivBytes = new byte[]{
                            0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01
                    };
                    Security.addProvider(new BouncyCastleProvider());
                    SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
                    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
                    Cipher cipher = null;
                    try {
                        cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
                        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                            // Cifraremos solamente el nombre y el country (pueden cifrar todos los parámetros que gusten)
                        for (int i = 0; i < accounts.size(); i++) {
                            String accountName = accounts.get(i).getAccountName();
                            byte[] arrAccountName = accountName.getBytes();
                            byte [] accountNameCipher = new byte[cipher.getOutputSize(arrAccountName.length)];
                            int ctAccountNameLength = cipher.update(arrAccountName, 0, arrAccountName.length, accountNameCipher, 0);
                            ctAccountNameLength += cipher.doFinal(accountNameCipher, ctAccountNameLength);
                            accounts.get(i).setAccountName(accountNameCipher.toString());

                            String accountCountry = accounts.get(i).getCountry();
                            byte[] arrAccountCountry = accountCountry.getBytes();
                            byte[] accountCountryCipher = new byte[cipher.getOutputSize(arrAccountCountry.length)];
                            int ctAccountCountryLength = cipher.update(arrAccountCountry, 0, arrAccountCountry.length, accountCountryCipher, 0);
                            ctAccountNameLength += cipher.doFinal(accountCountryCipher, ctAccountCountryLength);
                            accounts.get(i).setCountry(accountCountryCipher.toString());

                        }
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchProviderException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidAlgorithmParameterException e) {
                        throw new RuntimeException(e);
                    } catch (ShortBufferException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalBlockSizeException e) {
                        throw new RuntimeException(e);
                    } catch (BadPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidKeyException e) {
                        throw new RuntimeException(e);
                    }


                    JSONArray json = new JSONArray(accounts);
                    responseText = json.toString();
                    exchange.getResponseHeaders().add("Content-type", "application/json");
                    exchange.sendResponseHeaders(200, responseText.getBytes().length);
                } else {
                    /** 405 Method Not Allowed */
                    exchange.sendResponseHeaders(405, -1);
                }
                OutputStream output = exchange.getResponseBody();
                Instant finalDeEjecucion = Instant.now();
            /**
             * Always remember to close the resources you open.
             * Avoid memory leaks
             */
            LOGGER.info("LearningJava - Cerrando recursos ...");
            String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
            LOGGER.info("Tiempo de respuesta: ".concat(total));
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

    public void run(){
        try {
            String user = "user";
            String pass = "password";
            JSONArray jsonArray = new JSONArray(textThread);
            JSONObject user1 = new JSONObject(jsonArray.get(0).toString());
            JSONObject user2 = new JSONObject(jsonArray.get(1).toString());
            JSONObject user3 = new JSONObject(jsonArray.get(2).toString());

            response = createUser(user1.getString(user), user1.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 1: " + responseTextThread);
            Thread.sleep(1000);

            response = createUser(user2.getString(user), user2.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 2: " + responseTextThread);
            Thread.sleep(1000);

            response = createUser(user3.getString(user), user3.getString(pass));
            responseTextThread = new JSONObject(response).toString();
            LOGGER.info("Usuario 3: " + responseTextThread);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ExcepcionGenerica(e.getMessage());
        }
    }

    private static ResponseDTO login(String User, String password) {
        UserBO userBo = new UserBOImpl();
        return userBo.login(User, password);
    }

    private static ResponseDTO createUser(String User, String password) {
        UserBO userBo = new UserBOImpl();
        return userBo.createUser(User, password);
    }

    public static Map<String, String> splitQuery(URI uri) {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = uri.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8), URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }
        return query_pairs;
    }

    private static BankAccountDTO getAccountDetails(String user, String lastUsage) {
        BankAccountBO bankAccountBO = new BankAccountBOImpl();
        return bankAccountBO.getAccountDetails(user, lastUsage);
    }

    private static Optional<String> getParameterValue(Map<String, String> param, String paramName) {
        String val = param.get(paramName);
        if (val != null && val != "") {
            return Optional.ofNullable(val);
        }
        return Optional.ofNullable("NA");
    }

    private static Optional<Object> getParameterValueObject(Map<String, String> param, String paramName) {
        String val = param.get(paramName);
        if (val != null && val != "") {
            return Optional.ofNullable(val);
        }
        return Optional.ofNullable("NA");
    }
}
