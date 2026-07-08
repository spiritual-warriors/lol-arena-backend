package com.spiritualwarriors.lol_arena.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CaffeineCacheManager cacheManager() {
		CaffeineCacheManager manager = new CaffeineCacheManager("builds", "champions", "augments");
		manager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.SECONDS));
		manager.setAllowNullValues(false);
		return manager;
	}
}