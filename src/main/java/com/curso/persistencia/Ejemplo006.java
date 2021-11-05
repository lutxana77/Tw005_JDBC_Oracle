package com.curso.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo006 {

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
			//por tiempo , entre las 6am y las 10pm			
			//EL TRIGGER
//			create or replace NONEDITIONABLE trigger TRG_EMPLOYEES_SALARY_CHECK
//			BEFORE UPDATE
//			ON EMPLOYEES
//			FOR EACH ROW
//			BEGIN
//			    IF :OLD.SALARY > :NEW.SALARY THEN
//			        RAISE_APPLICATION_ERROR(-20111, 'Sorry! Salary can
//			                                        not be decreased!');
//			    END IF;
//			END; 
			
			String update = "UPDATE  EMPLOYEES SET  first_name ='Paca' WHERE employee_id =202";
			

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
