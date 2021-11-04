package com.curso.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class EjemplosJDBC {

	public static void main(String[] args) {
		
		
		//1. Cargar driver JDBC Oracle 18c
		try {
			
			//Class.forName("nombre completo de la clase ");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OK,cargo el driver");
		} catch (ClassNotFoundException e) {
			System.out.println("nocargo el driver");
			e.printStackTrace();
			return;
		}
		
		
		//2. Crear una conexion an la Base de datos (url, user y key)requerido
		String url = "jdbc:oracle:thin:@localhost:1521:xe";//buscar en oracle documentacion
		String user = "HR";
		String key ="hr";
		
		try (Connection con = DriverManager.getConnection(url,user,key)) {	
			System.out.println("conecto ok");
			
					//llamo al procedimiento
					CallableStatement call = con.prepareCall("{call MI_PROGRAMA(?,?,?)}");//1,2,3
					
					int id =100;
					String nombre,apellido;
					
					call.setInt(1, id);
					call.registerOutParameter(2, Types.VARCHAR);
					call.registerOutParameter(3, Types.VARCHAR);
					
					//ejecuto
					call.execute();
					
					//recogemos datos salida
					nombre = call.getNString(2);
					apellido = call.getNString(3);
					
					System.out.printf("El empleado %d es %s %s",id,nombre,apellido);//muestro

		} catch (SQLException e) {
			System.out.println("fallo conexion");
			e.printStackTrace();
		}
		
		
		//3. Preparar una llamada al procedimiento almacenado
		
		
		//4.Llamada
		//5.Mostrar resultado
		
		
		
	

	}//fin main

}
