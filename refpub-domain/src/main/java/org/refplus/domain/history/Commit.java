package org.refplus.domain.history;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Commit {

	private boolean committed = false;
	private LocalDateTime dateTime;
}
