package com.curso.ecommerce.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@NonNull
	private Integer id;

	@NonNull
	private String nombre;

	@NonNull
	private String username;

	@NonNull
	private String email;

	@NonNull
	private String direccion;

	@NonNull
	private String telefono;

	@NonNull
	private String tipo;

	@NonNull
	private String password;

	@OneToMany(mappedBy = "usuario")
	private List<Producto> productos;

	@OneToMany(mappedBy = "usuario")
	private List<Orden> ordenes;

}