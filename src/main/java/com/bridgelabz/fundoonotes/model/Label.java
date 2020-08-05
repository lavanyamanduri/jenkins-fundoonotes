package com.bridgelabz.fundoonotes.model;

/*
 *  author : Lavanya Manduri
 */

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "label")
public class Label {
	
	/* Fields required for Label Creation */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelId;

	@Column(name = "labelName")
	private String labelName;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private UserDetails labelUser;

	@JsonIgnore
	@ManyToMany(mappedBy = "labels")
	private List<Notes> notes;

}