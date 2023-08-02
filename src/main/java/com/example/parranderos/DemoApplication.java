package com.example.parranderos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.parranderos.modelo.Bar;
import com.example.parranderos.modelo.Bebedor;
import com.example.parranderos.modelo.Bebida;
import com.example.parranderos.repositorio.BarRepository;
import com.example.parranderos.repositorio.BebedorRepository;
import com.example.parranderos.repositorio.BebedorRepository.Respuesta;
import com.example.parranderos.repositorio.BebedorRepository.RespuestaBebedoryBares;
import com.example.parranderos.repositorio.BebidaRepository.RespuestaInformacionBebidas;
import com.example.parranderos.repositorio.BebidaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Collection;

@SpringBootApplication
public class DemoApplication  {



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/** 
	 * 
	 * 
	@Override
	public void run(String... args) {
		System.out.println("Hello World from Application Runner");
		Collection<Respuesta> respuesta = bebedorrepository.dar_bebedores_por_presupuesto_y_ciudad();
		Collection<Bebida> bebidas1 = bebidaRepository.darBebidasPorHorarioYPresupuesto("diurno", "alto");
		Collection<RespuestaInformacionBebidas> respuesta2 = bebidaRepository.darInformacionBebidas();
		Collection<Bebida> bebidas3 = bebidaRepository.darBebidasConElMayorGradoAlcohol();
		Collection<Bebida> bebidas4 = bebidaRepository.darBebidasConElMenorGradoAlcohol();

		for (Bebida b : bebidas1) {
			System.out.println(b.toString());
		}

		for (RespuestaInformacionBebidas b : respuesta2) {
			System.out.println(b.getTOTAL_BEBIDAS());
			System.out.println(b.getPROMEDIO_GRADO());
			System.out.println(b.getMAYOR_GRADO());
			System.out.println(b.getMENOR_GRADO());
		}

		for (Bebida b : bebidas3) {
			System.out.println(b.toString());
		}

		for (Bebida b : bebidas4) {
			System.out.println(b.toString());
		}

		for (Respuesta b : respuesta) {
			System.out.println(b.getCiudad().toString());
			// System.out.println(b.getPresupuesto().toString());
			System.out.println(b.getNumeroDeBebedores());
		}

		Collection<RespuestaBebedoryBares> respuesta3 = bebedorrepository.darTodosLosBebedoresYLosBaresQueFrencuentan();
		Collection<Bebedor> respuesta4 = bebedorrepository.darBebedoresQueGustanDeBebidasConMayorGradoAlcohol();
		Collection<Bebedor> respuesta5 = bebedorrepository.darBebedoresQueGustanDeBebidasConMenorGradoAlcohol();

		for (RespuestaBebedoryBares b : respuesta3) {
			System.out.println(b.getId());
			System.out.println(b.getNombre());
			System.out.println(b.getCiudad());
			System.out.println(b.getPresupuesto());
			System.out.println(b.getIdBar());
		}

		for (Bebedor b : respuesta4) {
			System.out.println(b.toString());
		}

		for (Bebedor b : respuesta5) {
			System.out.println(b.toString());
		}

		Collection<Bar> respuesta6 = barrepository.darBaresPorCiudadYTipoBebida("Bogotá", "Cerveza");
		Collection<Bar> respuesta7 = barrepository.darBaresQueSirvenBebidasConMayorGradoAlcohol();
		Collection<Bar> respuesta8 = barrepository.darBaresQueSirvenBebidasConMenorGradoAlcohol();
		Collection<BarRepository.RespuestaBaresPorCiudad> respuesta9 = barrepository.darBaresPorCiudad();

		for (Bar b : respuesta6) {
			System.out.println(b.toString());
		}

		for (Bar b : respuesta7) {
			System.out.println(b.toString());
		}

		for (Bar b : respuesta8) {
			System.out.println(b.toString());
		}

		for (BarRepository.RespuestaBaresPorCiudad b : respuesta9) {
			System.out.println(b.getCiudad());
			System.out.println(b.getNumeroBares());
			System.out.println(b.getCantidadSedes());
		}

	}
	*/
	/**
	 * @Bean
	 *       public CommandLineRunner demo(BebedorRepository bebedorrepository,
	 *       BebidaRepository bebidarepository, BarRepository barrepository,
	 *       Tipo_bebidaRepository tipobebidarepository, GustanRepository
	 *       gustanrepository, SirvenRepository sirvenrepository,
	 *       FrecuentanRepository frecuentanrepository) {
	 *       return (args) -> {
	 * 
	 *       Collection<Bebedor> c_bebedores = bebedorrepository.darBebedores();
	 * 
	 *       for(Bebedor b: c_bebedores)
	 *       {
	 *       System.out.println(b.toString());
	 *       }
	 * 
	 *       bebedorrepository.insertarBebedor("Pablo", "Bogotá", "Medio");
	 *       bebedorrepository.insertarBebedor("Nicolás", "Bogotá", "Medio");
	 *       bebedorrepository.actualizarBebedor(1, "Pedro", "Bogotá", "Medio");
	 *       bebedorrepository.eliminarBebedor(1);
	 * 
	 *       Collection<Bebedor> d_bebedores = bebedorrepository.darBebedores();
	 * 
	 *       for(Bebedor b: d_bebedores)
	 *       {
	 *       System.out.println(b.toString());
	 *       }
	 * 
	 *       tipobebidarepository.insertarTipo_bebida("Cerveza");
	 *       tipobebidarepository.insertarTipo_bebida("Vino");
	 * 
	 *       bebidarepository.insertarBebida("Aguila", 4, 3);
	 *       bebidarepository.insertarBebida("Poker", 4, 3);
	 *       bebidarepository.insertarBebida("Vino tinto", 12, 4);
	 * 
	 *       bebidarepository.actualizarBebida(5, "Aguila", 6, 3);
	 *       bebidarepository.eliminarBebida(6);
	 * 
	 *       Collection<Bebida> c_bebidas = bebidarepository.darBebidas();
	 * 
	 *       for(Bebida b: c_bebidas)
	 *       {
	 *       System.out.println(b.toString());
	 *       }
	 * 
	 *       barrepository.insertarBar("Bar 1", "Bogotá", "Alto", 1);
	 *       barrepository.insertarBar("Bar 2", "Bogotá", "Medio", 2);
	 *       barrepository.insertarBar("Bar 3", "Bogotá", "Bajo", 3);
	 * 
	 *       barrepository.actualizarBar(8, "Bar 1", "Bogotá", "Alto", 2);
	 *       barrepository.eliminarBar(9);
	 * 
	 *       Collection<Bar> c_bares = barrepository.darBares();
	 * 
	 *       for(Bar b: c_bares)
	 *       {
	 *       System.out.println(b.toString());
	 *       }
	 * 
	 *       Bar b1 = barrepository.darBar(8);
	 * 
	 *       System.out.println(b1.toString());
	 * 
	 *       gustanrepository.insertarGustan(2, 5);
	 * 
	 *       gustanrepository.insertarGustan(2, 7);
	 * 
	 *       gustanrepository.eliminarGustan(2, 7);
	 * 
	 *       gustanrepository.actualizarGustan(2, 5, 2, 7);
	 * 
	 *       Collection<Gustan> c_gustan = gustanrepository.darGustan();
	 * 
	 *       for(Gustan g: c_gustan)
	 *       {
	 *       System.out.println(g.toString());
	 *       }
	 * 
	 *       Gustan g1 = gustanrepository.darGustanPorId(2, 7);
	 * 
	 *       System.out.println(g1.toString());
	 * 
	 *       sirvenrepository.insertarSirven(8, 5, "diurno");
	 *       sirvenrepository.insertarSirven(8, 7, "nocturno");
	 *       sirvenrepository.insertarSirven(10, 5, "diurno");
	 *       sirvenrepository.insertarSirven(10, 7, "todos");
	 *       sirvenrepository.actualizarSirven(8, 5, "diurno", 10, 5, "nocturno");
	 *       sirvenrepository.eliminarSirven(10, 7, "todos");
	 * 
	 *       Collection<Sirven> c_sirven = sirvenrepository.darSirven();
	 * 
	 *       for(Sirven s: c_sirven)
	 *       {
	 *       System.out.println(s.toString());
	 * 
	 *       }
	 * 
	 *       Sirven s1 = sirvenrepository.darSirvenPorId(8, 7, "nocturno");
	 * 
	 *       System.out.println(s1.toString());
	 * 
	 *       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 *       java.util.Date fechaUtil = dateFormat.parse("2020-01-01");
	 *       Date fecha = new Date(fechaUtil.getTime());
	 * 
	 *       frecuentanrepository.insertarFrecuentan(2, 8, fecha, "diurno");
	 *       frecuentanrepository.insertarFrecuentan(2, 10, fecha, "nocturno");
	 *       frecuentanrepository.insertarFrecuentan(2, 8, fecha, "todos");
	 * 
	 *       frecuentanrepository.actualizarFrecuentan(2, 8, fecha, "diurno", 2, 8,
	 *       fecha, "nocturno");
	 * 
	 *       frecuentanrepository.eliminarFrecuentan(2, 8, fecha, "todos");
	 * 
	 *       Collection<Frecuentan> c_frecuentan =
	 *       frecuentanrepository.darFrecuentan();
	 * 
	 *       for(Frecuentan f: c_frecuentan)
	 *       {
	 *       System.out.println(f.toString());
	 * 
	 *       }
	 * 
	 *       Frecuentan f1 = frecuentanrepository.darFrecuentanPorId(2, 10, fecha,
	 *       "nocturno");
	 * 
	 *       System.out.println(f1.toString());
	 * 
	 *       };
	 * 
	 *       }
	 */
}