package com.padma.parus.store.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.padma.parus.store.annotation.NullOrNotBlank;
import com.parus.store.audit.DateAudit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="category")
@Table
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends DateAudit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "categoryId")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String categoryId;
	
	@Column(name = "categoryName", unique = true)
	@NullOrNotBlank(message = "categoryName cannot be null")
	private String categoryName;
	
	@Column(name = "categoryDescription", unique = false)
	@NullOrNotBlank(message = "categoryDescription cannot be null")
	private String categoryDescription;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<ProductInfo> producInfos;

}
