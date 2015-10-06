package org.refplus.domain.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class SingleCoded {

	/**
	 * The code for this object. It is not foreseen to have multiple codes. It is the string used to identify this
	 * object in the REST-API
	 */
	private String code;

	/**
	 * The names in different languages for this object.
	 */
	private MultiLingualString name;
}
