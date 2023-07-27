package com.example.parranderos.vista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.parranderos.modelo.Bebedores;
import com.example.parranderos.repositorio.BebedoresRepository;

import org.springframework.boot.CommandLineRunner;
import java.util.Collection;

@SpringBootApplication
@EntityScan("com.example.parranderos.modelo")
@ComponentScan("com.example.parranderos.modelo")
@EnableJpaRepositories("com.example.parranderos.repositorio")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


@Bean
public CommandLineRunner demo(BebedoresRepository repository) {
  return (args) -> {
	
	Collection<Bebedores> c_bebedores = repository.darBebedores();

	for(Bebedores b: c_bebedores)
	{
		System.out.println(b.toString());
	}
	
	repository.insertarBebedor("Pablo", "Bogotá", "Medio");
	repository.actualizarBebedor(1, "Pedro", "Bogotá", "Medio");
	repository.eliminarBebedor(1);
	
  };

}
}