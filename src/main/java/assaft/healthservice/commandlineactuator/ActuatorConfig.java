package assaft.healthservice.commandlineactuator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfig {

    @Bean
    public CommandLineArgsEndpoint commandLineArgsEndpoint(CommandLineArgumentsBean commandLineArgumentsBean) {
        return new CommandLineArgsEndpoint(commandLineArgumentsBean);
    }
}
