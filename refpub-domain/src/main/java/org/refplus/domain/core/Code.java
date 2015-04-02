package org.refplus.domain.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "code")
public class Code extends Attribute {

	private String code;

}
