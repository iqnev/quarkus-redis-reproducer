package org.acme.redis;


import static org.acme.redis.DemoIndex.DS_INDEX;
import static org.acme.redis.DemoIndex.LM_INDEX;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Startup
@Slf4j
public class DSListener implements Consumer<Notification> {

  private final PubSubCommands<Notification> pub;
  private final PubSubCommands.RedisSubscriber subscriber;

  public DSListener(final RedisDataSource ds) {
    pub = ds.pubsub(Notification.class);
    subscriber = pub.subscribe(String.valueOf(DS_INDEX), this);
  }

  @Override
  public void accept(final Notification notification) {
    log.info(notification.toString());
  }

  @PreDestroy
  public void terminate() {
    subscriber.unsubscribe(); // Unsubscribe from all subscribed channels
  }
}
