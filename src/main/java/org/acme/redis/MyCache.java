package org.acme.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyCache {
  private final ValueCommands<String, String> commands;
  private final PubSubCommands<Notification> pub;

  public MyCache(final RedisDataSource ds) {
    commands = ds.value(String.class);
    pub = ds.pubsub(Notification.class);
  }

  public String get(final String key) {
    return commands.get(key);
  }

  public void set(final String key, final String value) {
    commands.set(key, value);
  }
}
