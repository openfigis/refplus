package org.refplus.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "code")
public class Code extends Attribute {

	private Long id;

	private final String code;

}
