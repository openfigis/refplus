package org.refplus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Erik van Ingen, Thomas Berger
 *
 *         A bucket is an arbitrary group of reference objects (Ro's) at the top-level (i.e. visibility like a Concept)
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Bucket extends Concept {

}
