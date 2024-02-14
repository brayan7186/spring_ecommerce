package com.curso.ecommerce.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;
	  private String numero;
	  private Date fechaCreacion;
	  private Date fechaRecibida;
	  private  Double total;
	  
	@ManyToOne 
	  private Usuario usuario;
	  @OneToOne(mappedBy = "orden")
	   private DetalleOrden detalle;
}
