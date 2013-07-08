/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author
 * schleumer
 */
public class Configuracoes {

	public static HashMap<String, String> Database = new HashMap<String, String>() {
		{
			put("Driver", "com.mysql.jdbc.Driver");
			put("Servidor", "localhost");
			put("BancoDeDados", "crawler");
			put("Porta", "3306");
			put("Usuario", "root");
			put("Senha", "");
		}
	};
}
