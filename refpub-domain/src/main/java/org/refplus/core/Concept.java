package org.refplus.core;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Concept extends Ro {

	private List<Ro> roList;

}
