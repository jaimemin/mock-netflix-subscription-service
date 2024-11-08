package com.tistory.jaimemin.mocknetflix.subscription;

import java.util.Optional;

public interface FetchUserSubscriptionPort {

	Optional<UserSubscription> fetchByUserId(String userId);
}
