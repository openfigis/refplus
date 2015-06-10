package org.refplus.domain.core;

import java.util.List;
import java.util.Optional;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class SingleCoded {

	/**
	 * The code for this object. It is not foreseen to have multiple codes.
	 */
	protected Code conceptCode;

	/**
	 * The names in different languages for this object.
	 */
	protected Optional<List<MultiLingualString>> name;
}
