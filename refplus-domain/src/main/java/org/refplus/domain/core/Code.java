package org.refplus.domain.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "code")
@Entity
public class Code extends Attribute {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String code;

}
