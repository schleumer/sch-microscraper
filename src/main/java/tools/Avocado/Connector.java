/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.Avocado;

import conf.Configuracoes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author
 * schleumer
 */
public class Connector {

	private Connection connection;

	public Connector(String servidor, String usuario, String senha, String bancoDeDados) throws SQLException, ClassNotFoundException {
		String driverName = "com.mysql.jdbc.Driver";

		Class.forName(driverName);

		String url = "jdbc:mysql://%s/%s?zeroDateTimeBehavior=convertToNull";
		url = String.format(url,
				servidor,
				bancoDeDados);
		this.connection = DriverManager.getConnection(url,
				usuario,
				senha);
	}

	public Connector(String usuario, String senha, String bancoDeDados) throws SQLException, ClassNotFoundException {
		this(Configuracoes.Database.get("Servidor"),
				bancoDeDados,
				usuario,
				senha);
	}

	public Connector(String bancoDeDados) throws SQLException, ClassNotFoundException {
		this(Configuracoes.Database.get("Servidor"),
				bancoDeDados,
				Configuracoes.Database.get("Usuario"),
				Configuracoes.Database.get("Senha"));
	}

	public Connector() throws SQLException, ClassNotFoundException {
		this(Configuracoes.Database.get("Servidor"),
				Configuracoes.Database.get("Usuario"),
				Configuracoes.Database.get("Senha"),
				Configuracoes.Database.get("BancoDeDados"));
	}

	public Statement prepare(String sql, ArrayList<String> params) throws SQLException {
		return new Statement(this.connection, sql, params);
	}

	public Statement prepare(String sql) throws SQLException {
		return this.prepare(sql, new ArrayList<String>());
	}
}
