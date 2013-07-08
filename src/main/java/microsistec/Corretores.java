/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsistec;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import tools.Avocado.Statement;

/**
 *
 * @author
 * schleumer
 */
public class Corretores extends ArrayList<Corretor> {
	
	public Corretores() throws SQLException {
		Statement stmt = Main.DB.prepare("SELECT * FROM corretor");
		stmt.execute();
		ArrayList<HashMap<String, String>> rows = stmt.fetchAll();
		for (HashMap<String, String> row : rows) {
			Corretor corretor = new Corretor();
			corretor.setId(row.get("id"));
			corretor.setNome(row.get("nome"));
			corretor.setCreci(row.get("creci"));
			corretor.setEmail(row.get("email"));
			corretor.setTelefone(row.get("telefone"));
			corretor.setPassword(row.get("password"));
			corretor.setCreated_at(row.get("created_at"));
			corretor.setUpdated_at(row.get("update_at"));
			this.add(corretor);
		}
	}
}
