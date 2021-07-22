package br.com.Piloto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.com.Piloto.dao.*;
import br.com.Piloto.pojo.*;


public class Exemplo {
	public static void main(String[] args) throws SQLException, IOException, InterruptedException  {
		PilotoDAO pilotoDao =  new PilotoDAO();
		
		Piloto pilotoExemplo = new Piloto("Lucas", 1, 406.4F, 5, 10000F);
	pilotoDao.inserePiloto(pilotoExemplo);
		
		List<Piloto> pilotos = pilotoDao.listaPilotos();
		
		for (Piloto piloto : pilotos) {
			
			System.out.println(piloto);
			
		}
		pilotoDao.removePiloto(1);
		
		pilotos = pilotoDao.listaPilotos();
		
		for (Piloto piloto : pilotos) {
			
		System.out.println(piloto);
		
		}
	
		
	}
}
