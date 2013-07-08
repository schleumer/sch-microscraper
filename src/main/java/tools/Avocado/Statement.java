/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.Avocado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author
 * schleumer
 */
public class Statement {

	private ArrayList<String> params = new ArrayList<String>();
	private PreparedStatement stmt;
	private ResultSet rs;

	public Statement(Connection connection, String sql, ArrayList<String> params) throws SQLException {
		this.params = params;
		stmt = connection.prepareStatement(sql);
	}

	public Statement(Connection connection, String sql) throws SQLException {
		this(connection, sql, new ArrayList<String>());
	}

	public void appendParam(String value) {
		params.add(value);
	}

	public void execute() throws SQLException {
		int i = 1;
		for (String param : this.params) {
			this.stmt.setString(i++, param);
		}
		rs = stmt.executeQuery();

	}

	public HashMap<String, String> fetch() throws SQLException {
		ResultSetMetaData rsMeta = rs.getMetaData();
		int columnCount = rsMeta.getColumnCount();

		rs.first();
		HashMap<String, String> col = new HashMap<String, String>();
		for (int _i = 1; _i < columnCount + 1; _i++) {
			col.put(rsMeta.getColumnName(_i), rs.getString(_i));
		}
		return col;
	}

	public ArrayList<HashMap<String, String>> fetchAll() throws SQLException {
		ArrayList<HashMap<String, String>> rowset = new ArrayList<HashMap<String, String>>();
		ResultSetMetaData rsMeta = rs.getMetaData();
		int columnCount = rsMeta.getColumnCount();

		while (rs.next()) {
			HashMap<String, String> col = new HashMap<String, String>();
			for (int _i = 1; _i < columnCount + 1; _i++) {
				col.put(rsMeta.getColumnName(_i), rs.getString(_i));
			}
			rowset.add(col);
		}
		return rowset;
	}
}
