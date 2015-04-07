package org.refplus.domain.history;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Repo {

	List<Branch> branchList;
	List<Tag> tagList;
}
