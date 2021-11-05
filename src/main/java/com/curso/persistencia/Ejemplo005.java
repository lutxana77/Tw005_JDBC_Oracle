package com.curso.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo005 {

	public static void main(String[] args) {
		
		
		    // 1. Cargar driver JDBC Oracle 18c
			try {

				// Class.forName("nombre completo de la clase ");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("OK,cargo el driver");
			} catch (ClassNotFoundException e) {
				System.out.println("no cargo el driver");
				e.printStackTrace();
				return;
			}

			// 2. Crear una conexion an la Base de datos (url, user y key)requerido
			String url = "jdbc:oracle:thin:@localhost:1521:xe";// buscar en oracle documentacion
			String user = "HR";
			String key = "hr";

			try (Connection con = DriverManager.getConnection(url, user, key)) {
				System.out.println("conecto ok");
				
				//Actualizo y asi lanzo un trigger de la base de datos que impide la actualizacion
				// si el sueldo decrece
				//EL TRIGGER
//				create or replace NONEDITIONABLE trigger trg_employees_time_check
//				before update or insert or delete
//				on employees
//				for each row
//				begin
//				if to_char(sysdate,'hh24') < 6 or
//				to_char(sysdate,'hh24') > 10 then
//				raise_application_error(-20111,'Sorry! No change
//				can be made before 6 AM and after 10 PM');
//				end if;
//				end;
				
				String update = "UPDATE  EMPLOYEES SET  SALARY = 5000 WHERE SALARY >3000";
				

				con.setAutoCommit(false);
				try {
					Statement st3 = con.createStatement();
					st3.executeUpdate(update);
					
					// ok
					con.commit();
				} catch (Exception excepcion) {
					
					con.rollback();
					throw excepcion;
					
				}
				

			} catch (SQLException e) {
				System.out.println("fallo conexion" + e.getMessage());
			}
			
	}

}
