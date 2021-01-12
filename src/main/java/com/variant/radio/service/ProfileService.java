package com.variant.radio.service;

import com.variant.radio.domain.User;
import com.variant.radio.domain.UserSubscription;
import com.variant.radio.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public ProfileService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public User changeSubscription(User subscriber, User channel) {
        List<UserSubscription> subscriptions = channel.getSubscribers()
                .stream()
                .filter(subscription -> subscription.getSubscriber().equals(subscriber))
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            UserSubscription userSubscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(userSubscription);
        } else {
            channel.getSubscribers().removeAll(subscriptions);
        }
        return userDetailsRepository.save(channel);
    }
}
