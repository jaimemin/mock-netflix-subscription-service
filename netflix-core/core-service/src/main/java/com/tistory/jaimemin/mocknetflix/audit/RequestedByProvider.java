package com.tistory.jaimemin.mocknetflix.audit;

import java.util.Optional;

public interface RequestedByProvider {

	Optional<String> getRequestedBy();
}
