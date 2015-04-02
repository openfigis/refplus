package org.refplus.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = "code")
public class Code extends Attribute {

	private String code;

}
