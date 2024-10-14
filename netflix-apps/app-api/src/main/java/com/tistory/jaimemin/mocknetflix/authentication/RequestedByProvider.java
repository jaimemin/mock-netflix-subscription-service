package com.tistory.jaimemin.mocknetflix.authentication;

import java.util.Optional;

public interface RequestedByProvider {

	Optional<String> getRequestedBy();
}
