package org.acme;

import static org.acme.redis.DemoIndex.LM_INDEX;

import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.acme.redis.MyCache;

@ApplicationScoped
@Slf4j
public class StateManager {

  @Inject
  MyCache myCache;

  void onStart(@Observes final StartupEvent ev) {
    log.info("Initializing StateManager...");

    myCache.set(String.valueOf(LM_INDEX), "Value-LM_INDEX");

  }
}
