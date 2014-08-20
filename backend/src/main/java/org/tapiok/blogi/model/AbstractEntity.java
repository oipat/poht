package org.tapiok.blogi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.hateoas.Identifiable;

@MappedSuperclass
public abstract class AbstractEntity implements Identifiable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	protected AbstractEntity() {
		this.id = null;
	}
	
	public Long getId() {
		return this.id;
	}
	
}
