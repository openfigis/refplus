package org.refplus.domain.core;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "code")
public class Code extends Attribute {

	@Id
	private Long id;

	private String code;

}
