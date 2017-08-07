

package com.devramble.microsvc.user;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.graphite.GraphiteSender;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Vladimir Djurovic <vdjurovic@vektorsoft.com>
 */
@Configuration
@EnableMetrics(proxyTargetClass = true)
public class DropwizardMetricsConfig extends MetricsConfigurerAdapter {
    
    @Autowired
    private MetricRegistry registry;

    @Bean(destroyMethod = "stop")
    public GraphiteReporter graphiteReporter() {
        GraphiteSender sender = new Graphite("localhost", 2003);
        GraphiteReporter reporter = GraphiteReporter.forRegistry(registry).prefixedWith("user-service").convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build(sender);
        reporter.start(10, TimeUnit.SECONDS);
        return reporter;
    }
    
}
