package org.acme.redis;


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
public class LMListener implements Consumer<Notification> {

  private final PubSubCommands<Notification> pub;
  private final PubSubCommands.RedisSubscriber subscriber;

  public LMListener(final RedisDataSource ds) {
    pub = ds.pubsub(Notification.class);
    subscriber = pub.subscribe(String.valueOf(LM_INDEX), this);
  }

  @Override
  public void accept(final Notification notification) {
   log.info("TUKA:" + String.valueOf(notification));
  }

  @PreDestroy
  public void terminate() {
    subscriber.unsubscribe(); // Unsubscribe from all subscribed channels
  }
}
