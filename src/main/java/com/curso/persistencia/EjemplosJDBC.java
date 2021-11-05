package com.curso.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class EjemplosJDBC {

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

//			// 3. Preparar una llamada al procedimiento almacenado
//
//			CallableStatement call = con.prepareCall("{call MI_PROGRAMA(?,?,?)}");// 1,2,3
//
//			int id = 100;
//			String nombre, apellido;
//
//			// 4.Llamada
//			call.setInt(1, id);
//			call.registerOutParameter(2, Types.VARCHAR);
//			call.registerOutParameter(3, Types.VARCHAR);
//
//			// ejecuto
//			call.execute();
//
//			// recogemos datos salida
//			nombre = call.getNString(2);
//			apellido = call.getNString(3);
//
//			// 5.Mostrar resultado
//			System.out.printf("El empleado %d es %s %s", id, nombre, apellido);
//
//			// ----------------------------------------//
//
//			// STATEMENT--  modo malo
//			Statement st = con.createStatement();

//					st.execute(...);
//					st.executeQuery(...);
//					st.executeUpdate(...);

//					String instruccion = "INSERT INTO COFFEES"
//							+ " VALUES ('Colombian',101,7.99,0,0)";					
//					int n = st.executeUpdate(instruccion);
//					
//					
//					instruccion = "INSERT INTO COFFEES"
//							+ " VALUES ('Express',150,9.99,0,0)";
//					n = st.executeUpdate(instruccion);n++;
//					
//					
//					System.out.println("\nInserciones hechas: "+n);

//			// CONSULTAR LOS COFFEES
//			String consulta = "SELECT * FROM COFFEES";
//			ResultSet rs = st.executeQuery(consulta);
//
//			System.out.println("\n..Listar cafes..");
//			String nombreCafe;
//			int stock;
//			double precio;
//			while (rs.next()) {
//				nombreCafe = rs.getString(1);// rs.getString(COF_NAME)
//				stock = rs.getInt(2);
//				precio = rs.getDouble(3);
//
//				System.out.printf("El cafe %s tiene %d unidades " + "y vale %5.2f\n", nombreCafe, stock, precio);
//			}
//			// ----------------------------------------//
//			// lanzo a ciegas
//			String consultaAnonima = "SELECT COF_NAME FROM COFFEES";
//
//			boolean esRS = st.execute(consultaAnonima);
//			if (esRS) {
//				ResultSet rs2 = st.getResultSet();
//				// uso rs2 como antes y obtengo datos
//
//			} else {
//				int nReg = st.getUpdateCount();
//				
//			}
//
//			// ----------------------------------------//
//
//			Scanner sc = new Scanner(System.in);
//
//			System.out.println("Mostrar precio de un cafe");
//			System.out.println("Nombre del cafe:");
//
//			String cafe = sc.next();
//
//			Statement st2 = con.createStatement();
//
//			ResultSet respuesta = st2.executeQuery("SELECT PRICE " + "FROM COFFEES WHERE COF_NAME = '" + cafe + "'");
//
//			if (respuesta.next()) {
//				System.out.println("precio es " + respuesta.getDouble(1));
//			} else {
//				System.out.println("El cafe no existe");
//			}
//			
//			// ----------------------------------------//

//			// PREPAREDSTATEMENT-- modo bueno
//			System.out.println("..MEJORADO...");
//			System.out.println("Mostrar precio de un cafe");
//			System.out.println("Nombre del cafe:");
//
//			 cafe = sc.next();
//			 
//			PreparedStatement pst = con.prepareStatement("SELECT PRICE FROM COFFEES WHERE COF_NAME =?");
//			pst.setString(1, cafe);
//			
//			respuesta = pst.executeQuery();
//			
//			if (respuesta.next()) {
//				System.out.println("precio es " + respuesta.getDouble(1));
//			} else {
//				System.out.println("El cafe no existe");
//			}
//			// ----------------------------------------//

			// EJEMPLO TRANSACCION

			// con.setAutocommit()
			// con.commit()
			// con.rollback()

			// insert1 -> commit
			// insert2 -> commit

			String insert1 = "INSERT INTO COFFEES" + " VALUES ('cafe1',100,7.8,0,0)";
			String insert2 = "INSERT INTO COFFEES" + " VALUES ('cafe2',40,5.0,0,0)";

			con.setAutoCommit(false);
			try {
				Statement st3 = con.createStatement();
				st3.executeUpdate(insert1);
				st3.executeUpdate(insert2);
				// ok
				con.commit();
			} catch (Exception excepcion) {
				con.rollback();// si algo va mal , echa para atras todas las operaciones en la base de datos
				throw excepcion;
			}
			// Fin transaccion

		} catch (SQLException e) {
			System.out.println("fallo conexion" + e.getMessage());
			e.printStackTrace();
		}

	}// fin main

}
